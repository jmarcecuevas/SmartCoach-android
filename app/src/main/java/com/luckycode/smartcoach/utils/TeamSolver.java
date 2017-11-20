package com.luckycode.smartcoach.utils;

import com.luckycode.smartcoach.model.Player;
import com.luckycode.smartcoach.model.Team;
import com.luckycode.smartcoach.model.TitularTeam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by marcelocuevas on 11/11/17.
 */

/** Implememtacion con
 *  algoritmo goloso
 */

public class TeamSolver {

    public static TitularTeam getTitularTeam(Team team, int cantDef, int cantMid, int cantForw){
        List<Player> titularTeam=new ArrayList<>();

        getBestPlayers(1,team.getGoalkeepers(),titularTeam);
        getBestPlayers(cantDef,team.getDefenders(),titularTeam);
        getBestPlayers(cantMid,team.getMidfielders(),titularTeam);
        getBestPlayers(cantForw,team.getForwards(),titularTeam);

        return new TitularTeam(titularTeam);
    }

    public static List<Player> getBestPlayers(int amount,List<Player> players,List<Player> titularTeam){
        List<Player> solution=new ArrayList<>();
        sort(players);

        for(Player player:players){
            if (solution.size()==amount)
                return solution;
            if (player.isCompatibleWithTeam(titularTeam)) {
                solution.add(player);
                titularTeam.add(player);
            }
        }
        return solution;
    }

    public static void sort(List<Player> players){
        Collections.sort(players);
    }


}
