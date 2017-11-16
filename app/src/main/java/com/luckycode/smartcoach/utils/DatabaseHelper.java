package com.luckycode.smartcoach.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.field.types.IntegerObjectType;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.luckycode.smartcoach.model.Player;

import java.sql.SQLException;

/**
 * Created by marcelocuevas on 9/30/17.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "players.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<Player,Integer> daoPlayer;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource,Player.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        onCreate(database,connectionSource);
    }

    public Dao<Player,Integer> getDaoPlayer() throws SQLException{
        if(daoPlayer == null){
            daoPlayer=getDao(Player.class);
        }
        return daoPlayer;
    }

    @Override
    public void close() {
        super.close();
        daoPlayer=null;
    }
}
