package com.luckycode.smartcoach.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by marcelocuevas on 11/19/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class TitularTeamTest {
    private TitularTeam titularTeam;

    @Before
    public void setUp(){
        Player p1=new Player("Marcelo","Cuevas","marceCuevas","Mediocampista",6);
        Player p2=new Player("Esteban","Camaño","estebanCamanio","Delantero",8);
        Player p3=new Player("Nicolás","Méndez","nicomendez","Defensor",6);
        List<Player> players=new ArrayList<>();
        players.add(p1); players.add(p2); players.add(p3);
        titularTeam=new TitularTeam(players);
    }

    @Test
    public void calculateLevel(){
        assertEquals(titularTeam.calculateLevel(),20);
    }

}
