package com.luckycode.smartcoach.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by marcelocuevas on 11/11/17.
 */

public class Team implements Serializable{
    private List<Player> goalkeepers;
    private List<Player> defenders;
    private List<Player> midfielders;
    private List<Player> forwards;

    public Team(List<Player> goalkeepers,List<Player> defenders,List<Player> midfielders,List<Player> forwards){
        this.goalkeepers=goalkeepers;
        this.defenders=defenders;
        this.midfielders=midfielders;
        this.forwards=forwards;
    }

    public Team(){}

    public List<Player> getDefenders() {
        return defenders;
    }

    public List<Player> getForwards() {
        return forwards;
    }

    public List<Player> getGoalkeepers() {
        return goalkeepers;
    }

    public List<Player> getMidfielders() {
        return midfielders;
    }

}