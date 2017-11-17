package com.luckycode.smartcoach.interactor;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.j256.ormlite.dao.Dao;
import com.luckycode.smartcoach.common.LuckyInteractor;
import com.luckycode.smartcoach.model.Player;
import com.luckycode.smartcoach.presenter.SplashPresenter;
import com.luckycode.smartcoach.utils.DatabaseHelper;
import com.luckycode.smartcoach.utils.SettingsManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by marcelocuevas on 10/14/17.
 */

public class MainInteractor extends LuckyInteractor<SplashPresenter> {
    private static final String PLAYERS_JSON_FILE_NAME="players.json";
    private Context context;
    private DatabaseHelper dbHelper;
    private SettingsManager settingsManager;
    private InteractorListener listener;

    public MainInteractor(Context context, DatabaseHelper dbHelper){
        this.dbHelper=dbHelper;
        this.context=context;
        settingsManager=new SettingsManager(context);
    }

    public void setListener(InteractorListener listener) {
        this.listener = listener;
    }

    public void loadDatabaseData() {
        if(settingsManager.isFirstTime()){
            storeLocalJSONIntoDatabase();
            settingsManager.setNoFirstTime();
        }else{
            List<Player> players=getPlayers();
            listener.onDataLoaded(players);
        }
    }

    private void storeLocalJSONIntoDatabase(){
        Player[] players=loadTownsFromJSON();
        try {
            Dao dao=dbHelper.getDaoPlayer();
            for(Player player:players){
                dao.create(player);
            }
            listener.onLocalJSONStored(Arrays.asList(players));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Player[] loadTownsFromJSON(){
        Gson gson=new Gson();
        AssetManager assetManager=context.getAssets();
        try {
            InputStream ims=assetManager.open(PLAYERS_JSON_FILE_NAME);
            Reader reader=new InputStreamReader(ims);
            return gson.fromJson(reader,Player[].class);
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Player> getPlayers(){
        try {
            Dao dao=dbHelper.getDaoPlayer();
            List<Player> players=dao.queryForAll();
            setIncompatiblesFromDB(players);
            return players;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Player> getGoalkeepers(){
        try{
            Dao dao=dbHelper.getDaoPlayer();
            List<Player> goalkeepers= dao.queryForEq("position","Arquero");
            setIncompatiblesFromDB(goalkeepers);
            return goalkeepers;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Player> getDefenders(){
        try{
            Dao dao=dbHelper.getDaoPlayer();
            List<Player> defenders= dao.queryForEq("position","Defensor");
            setIncompatiblesFromDB(defenders);
            return defenders;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Player> getMidfielders(){
        try{
            Dao dao=dbHelper.getDaoPlayer();
            List<Player> midfielders= dao.queryForEq("position","Mediocampista");
            setIncompatiblesFromDB(midfielders);
            return midfielders;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Player> getForwards() {
        try {
            Dao dao = dbHelper.getDaoPlayer();
            List<Player> forwards= dao.queryForEq("position", "Delantero");
            setIncompatiblesFromDB(forwards);
            return forwards;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setIncompatiblesFromDB(List<Player> players){
        try {
            Dao dao=dbHelper.getDaoPlayer();
            for(Player player:players){
                for(Integer incomID:player.getIncompatibles()){
                    player.addIncompatible((Player) dao.queryForEq("id",incomID).get(0));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void savePlayer(Player player){
        try{
            Dao dao=dbHelper.getDaoPlayer();
            dao.create(player);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public interface InteractorListener{
        void onLocalJSONStored(List<Player> players);
        void onDataLoaded(List<Player> players);
    }
}
