package com.luckycode.smartcoach.model;

import java.util.List;

/**
 * Created by marcelocuevas on 11/14/17.
 */

public class TitularTeam {
    private List<Player> players;
    private int level;

    public TitularTeam(List<Player> players){
        this.players=players;
        this.level=calculateLevel();
    }

    public int calculateLevel(){
        int level=0;
        for(Player player:players){
            level+=player.getLevel();
        }
        return level;
    }

    public int getLevel() {
        return level;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
