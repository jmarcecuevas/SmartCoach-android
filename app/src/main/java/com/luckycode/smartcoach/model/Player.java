package com.luckycode.smartcoach.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcelocuevas on 10/26/17.
 */

@DatabaseTable (tableName = "players")
public class Player implements Serializable,Comparable<Player>{
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String name;
    @DatabaseField
    private String surname;
    @DatabaseField
    private String photo;
    @DatabaseField
    private String position;
    @DatabaseField
    private int level;
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private SerializedList<Integer> incompatibles;
    private List<Player> incompatiblesPlayers=new ArrayList<>();

    public Player(){}

    public Player(String name,String surname,String photo,String position,int level){
        this.name=name;
        this.surname=surname;
        this.photo=photo;
        this.position=position;
        this.level=level;
        incompatibles=new SerializedList<>();
    }

    public boolean isCompatibleWithTeam(List<Player> players){
        for(Player player:players)
            if(player.getIncompatiblesPlayers().contains(this))
                return false;
        return true;
    }

    public List<Player> getIncompatiblesPlayers() {
        return incompatiblesPlayers;
    }

    public SerializedList<Integer> getIncompatibles() {
        return incompatibles;
    }

    public void addIncompatiblePlayer(Player incompatiblePlayer){
        incompatiblesPlayers.add(incompatiblePlayer);
    }

    public void addIncompatible(int id){
        incompatibles.add(id);
    }


    public int getLevel() {
        return level;
    }

    public String getSurname() {
        return surname;
    }

    public String getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public String getPhoto() {
        return photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setIncompatiblesPlayers(List<Player> incompatiblesPlayers) {
        this.incompatiblesPlayers = incompatiblesPlayers;
    }

    @Override
    public int compareTo(Player o) {
        return o.level-level;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Player){
            Player player=(Player)o;
            if(player.getId()==this.id)
                return true;
            else
                return false;
        }else
            return false;
    }
}

