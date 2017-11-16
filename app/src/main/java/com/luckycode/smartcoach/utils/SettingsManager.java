package com.luckycode.smartcoach.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by marcelocuevas on 10/16/17.
 */

public class SettingsManager {
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private static final String PREF_NAME="MyPreferences";
    private static final String FIRST_TIME="firstTime";

    public SettingsManager(Context context){
        prefs=context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        editor=prefs.edit();
    }

    public void setNoFirstTime(){
        editor.putBoolean(FIRST_TIME,false);
        editor.commit();
    }

    public boolean isFirstTime(){
        return prefs.getBoolean(FIRST_TIME,true);
    }

}
