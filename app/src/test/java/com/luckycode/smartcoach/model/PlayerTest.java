package com.luckycode.smartcoach.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by marcelocuevas on 11/19/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class PlayerTest {
    private Player player,otherPlayer;

    @Before
    public void setUp(){
        player=new Player("Lionel","Messi","lionel_messi","Delantero",10);
        player.setId(1);
        otherPlayer=new Player("Marcelo","Cuevas","marceCuevas","MedioCampista",6);
        otherPlayer.setId(23);
        initIncompatibles();
    }

    public void initIncompatibles(){
        Player p1=new Player();
        p1.setId(1);
        Player p2=new Player();
        p2.setId(2);
        Player p3=new Player();
        p3.setId(3);

        p1.addIncompatiblePlayer(p2);
        p2.addIncompatiblePlayer(p1);

        p3.addIncompatiblePlayer(p1);
        p1.addIncompatiblePlayer(p3);

        List<Player> players=new ArrayList<>();
        players.add(p1); players.add(p2); players.add(p3);

        player.setIncompatiblesPlayers(players);
    }

    @Test
    public void equalsTest(){
        String object="";
        assertFalse(player.equals(object));

        Player other=new Player("Lionel","Messi","messiPhoto","Delantero",10);
        other.setId(2);
        assertFalse(player.equals(other));

        assertFalse(player.equals(otherPlayer));
        assertFalse(otherPlayer.equals(player));

        other.setId(1);
        assertTrue(player.equals(other));
        assertTrue(other.equals(player));
    }

    @Test
    public void compareToTest(){
        assertEquals(player.compareTo(otherPlayer),-4);
        assertEquals(otherPlayer.compareTo(player),4);

        player.setLevel(6);
        assertEquals(player.compareTo(otherPlayer),0);
        assertEquals(otherPlayer.compareTo(player),0);
    }

    @Test
    public void isCompatibleWithTeamTest(){
        Player p1=new Player();
        p1.setId(1);
        Player p2=new Player();
        p2.setId(2);
        Player p3=new Player();
        p3.setId(3);

        p1.addIncompatiblePlayer(p2);
        p2.addIncompatiblePlayer(p1);

        p3.addIncompatiblePlayer(p1);
        p1.addIncompatiblePlayer(p3);

        List<Player> players=new ArrayList<>();
        players.add(p1); players.add(p2); players.add(p3);

        assertFalse(player.isCompatibleWithTeam(players));

        assertTrue(otherPlayer.isCompatibleWithTeam(players));
    }

    @Test
    public void addIncompatibleTest(){
        Player myNewPlayer= new Player("Esteban","Barrera","esteban_barrera","Delantero",5);
        player.addIncompatiblePlayer(myNewPlayer);

        assertEquals(player.getIncompatiblesPlayers().size(),4);
    }

}
