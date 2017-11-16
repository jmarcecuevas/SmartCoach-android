package com.luckycode.smartcoach.model;

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

    public static TitularTeam getTitularTeam(Team team,int cantDef,int cantMid,int cantForw){
        List<Player> titularTeam=new ArrayList<>();

        getBestPlayers(1,team.getGoalkeepers(),titularTeam);
        getBestPlayers(cantDef,team.getDefenders(),titularTeam);
        getBestPlayers(cantMid,team.getMidfielders(),titularTeam);
        getBestPlayers(cantForw,team.getForwards(),titularTeam);

        return new TitularTeam(titularTeam);
    }

    private static List<Player> getBestPlayers(int amount,List<Player> players,List<Player> titularTeam){
        List<Player> solution=new ArrayList<>();
        order(players);

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

    private static void order(List<Player> players){
        Collections.sort(players);
    }


}
