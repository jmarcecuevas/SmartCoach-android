package com.luckycode.smartcoach.common;

import com.luckycode.smartcoach.model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcelocuevas on 9/30/17.
 */

public class LuckyPresenter<T> {

    private T mView;

    public LuckyPresenter(T mView) {
        this.mView = mView;
    }

    protected T getView() {
        return mView;
    }

    public void detachView() {
        mView = null;
    }

    public List<String> getPlayersNames(List<Player> players){
        List<String> names=new ArrayList<>();
        for(Player player:players){
            names.add(player.getName()+" "+player.getSurname());
        }
        return names;
    }
}