package com.luckycode.smartcoach.helper;

import com.luckycode.smartcoach.model.Player;
import com.luckycode.smartcoach.model.Team;
import com.luckycode.smartcoach.model.TitularTeam;
import com.luckycode.smartcoach.utils.TeamSolver;

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
public class TeamSolverTest {
    private Player p1,p2,p3,p4,p5;
    private List<Player> myPlayers;
    private Team team;

    @Before
    public void setUp(){
        p1=new Player("Marcelo","Cuevas","marce","Delantero",8);
        p1.setId(1);
        p2=new Player("Adrian","Benedetto","adrianBened","Defensor",5);
        p2.setId(2);
        p3=new Player("Fernando","Passet","fernandoPasset","Defensor",9);
        p3.setId(3);
        p4=new Player("Brian","Gomez","brianGomez","Delantero",10);
        p4.setId(4);
        p5=new Player("Rodrigo","Perez","rodriPerez","Mediocampista",6);
        p5.setId(5);
        //Add incompatibles
        p1.addIncompatiblePlayer(p2);
        p2.addIncompatiblePlayer(p1);

        myPlayers=new ArrayList<>();
        myPlayers.add(p1);
        myPlayers.add(p2);
        myPlayers.add(p3);
        myPlayers.add(p4);
        myPlayers.add(p5);

        initTeam();
    }

    public void initTeam(){
        Player p1=new Player(); p1.setId(1); p1.setLevel(6);
        Player p2=new Player(); p2.setId(2); p2.setLevel(4);
        Player p3=new Player(); p3.setId(3); p3.setLevel(7);
        Player p4=new Player(); p4.setId(4); p4.setLevel(7);
        Player p5=new Player(); p5.setId(5); p5.setLevel(8);
        Player p6=new Player(); p6.setId(6); p6.setLevel(5);
        Player p7=new Player(); p7.setId(7); p7.setLevel(7);
        Player p8=new Player(); p8.setId(8); p8.setLevel(6);
        Player p9=new Player(); p9.setId(9); p9.setLevel(7);
        Player p10=new Player(); p10.setId(10); p10.setLevel(6);
        Player p11=new Player(); p11.setId(11); p11.setLevel(5);
        Player p12=new Player(); p12.setId(12); p12.setLevel(8);
        Player p13=new Player(); p13.setId(13); p13.setLevel(7);
        Player p14=new Player(); p14.setId(14); p14.setLevel(6);
        Player p15=new Player(); p15.setId(15); p15.setLevel(6);
        Player p16=new Player(); p16.setId(16); p16.setLevel(6);
        Player p17=new Player(); p17.setId(17); p17.setLevel(7);
        Player p18=new Player(); p18.setId(18); p18.setLevel(7);
        Player p19=new Player(); p19.setId(19); p19.setLevel(7);
        Player p20=new Player(); p20.setId(20); p20.setLevel(7);
        Player p21=new Player(); p21.setId(21); p21.setLevel(7);
        Player p22=new Player(); p22.setId(22); p22.setLevel(7);
        Player p23=new Player(); p23.setId(23); p23.setLevel(9);
        Player p24=new Player(); p24.setId(24); p24.setLevel(10);
        Player p25=new Player(); p25.setId(25); p25.setLevel(6);
        Player p26=new Player(); p26.setId(26); p26.setLevel(8);
        Player p27=new Player(); p27.setId(27); p27.setLevel(3);
        Player p28=new Player(); p28.setId(28); p28.setLevel(8);
        Player p29=new Player(); p29.setId(29); p29.setLevel(8);

        p1.addIncompatiblePlayer(p2); p1.addIncompatiblePlayer(p4); p1.addIncompatiblePlayer(p28);
        p2.addIncompatiblePlayer(p1);
        p4.addIncompatiblePlayer(p1); p4.addIncompatiblePlayer(p6); p4.addIncompatiblePlayer(p5);
        p5.addIncompatiblePlayer(p4);
        p6.addIncompatiblePlayer(p4);
        p8.addIncompatiblePlayer(p9);
        p9.addIncompatiblePlayer(p8);
        p11.addIncompatiblePlayer(p23);
        p12.addIncompatiblePlayer(p24);
        p15.addIncompatiblePlayer(p17); p15.addIncompatiblePlayer(p18);
        p17.addIncompatiblePlayer(p15);
        p18.addIncompatiblePlayer(p15);
        p19.addIncompatiblePlayer(p20); p19.addIncompatiblePlayer(p21); p19.addIncompatiblePlayer(p22);
        p20.addIncompatiblePlayer(p19);
        p21.addIncompatiblePlayer(p19);
        p22.addIncompatiblePlayer(p19);
        p23.addIncompatiblePlayer(p11);
        p24.addIncompatiblePlayer(p12); p24.addIncompatiblePlayer(p26); p24.addIncompatiblePlayer(p29);
        p26.addIncompatiblePlayer(p24);
        p28.addIncompatiblePlayer(p1);
        p29.addIncompatiblePlayer(p24);

        List<Player> goalkeepers=new ArrayList<>();
        goalkeepers.add(p1); goalkeepers.add(p2); goalkeepers.add(p3);

        List<Player> defenders=new ArrayList<>();
        defenders.add(p4); defenders.add(p5); defenders.add(p6); defenders.add(p14); defenders.add(p16);

        List<Player> midfielders=new ArrayList<>();
        midfielders.add(p7); midfielders.add(p8); midfielders.add(p9); midfielders.add(p10); midfielders.add(p15);
        midfielders.add(p18); midfielders.add(p19); midfielders.add(p20); midfielders.add(p21); midfielders.add(p22);
        midfielders.add(p25); midfielders.add(p28);

        List<Player> forwards=new ArrayList<>();
        forwards.add(p11); forwards.add(p12); forwards.add(p13); forwards.add(p17); forwards.add(p23); forwards.add(p24);
        forwards.add(p26); forwards.add(p27); forwards.add(p29);

        team=new Team(goalkeepers,defenders,midfielders,forwards);
    }

    @Test
    public void getBestPlayersTest(){
        List<Player> titularTeam=new ArrayList<>();
        List<Player> solution= TeamSolver.getBestPlayers(3,myPlayers,titularTeam);

        assertEquals(solution.size(),3);
        assertEquals(solution.get(0).getId(),4);
        assertEquals(solution.get(1).getId(),3);
        assertEquals(solution.get(2).getId(),1);
        assertEquals(titularTeam.size(),3);

        p1.addIncompatiblePlayer(p3);
        p3.addIncompatiblePlayer(p1);
        titularTeam=new ArrayList<>();
        solution= TeamSolver.getBestPlayers(3,myPlayers,titularTeam);
        assertEquals(solution.size(),3);
        assertEquals(solution.get(0).getId(),4);
        assertEquals(solution.get(1).getId(),3);
        assertEquals(solution.get(2).getId(),5);
        assertEquals(titularTeam.size(),3);

        titularTeam=new ArrayList<>();
        solution= TeamSolver.getBestPlayers(9,myPlayers,titularTeam);
        assertEquals(solution.size(),4);
        assertEquals(titularTeam.size(),4);

        titularTeam=new ArrayList<>();
        solution= TeamSolver.getBestPlayers(1,myPlayers,titularTeam);
        assertEquals(solution.size(),1);
        assertEquals(solution.get(0).getId(),4);
        assertEquals(titularTeam.size(),1);

        solution= TeamSolver.getBestPlayers(2,myPlayers,titularTeam);
        assertEquals(solution.size(),2);
        assertEquals(titularTeam.size(),3);
    }

    @Test
    public void getTitularTeamTest(){
        TitularTeam solutionTeam=TeamSolver.getTitularTeam(team,4,3,3);
        assertEquals(solutionTeam.getPlayers().size(),11);
        assertEquals(solutionTeam.getPlayers().get(0).getId(),3);
        assertEquals(solutionTeam.getPlayers().get(1).getId(),5);
        assertEquals(solutionTeam.getPlayers().get(2).getId(),14);
        assertEquals(solutionTeam.getPlayers().get(3).getId(),16);
        assertEquals(solutionTeam.getPlayers().get(4).getId(),6);
        assertEquals(solutionTeam.getPlayers().get(5).getId(),28);
        assertEquals(solutionTeam.getPlayers().get(6).getId(),7);
        assertEquals(solutionTeam.getPlayers().get(7).getId(),9);
        assertEquals(solutionTeam.getPlayers().get(8).getId(),24);
        assertEquals(solutionTeam.getPlayers().get(9).getId(),23);
        assertEquals(solutionTeam.getPlayers().get(10).getId(),13);

        /////////////////////////
        team.getDefenders().get(0).addIncompatiblePlayer(team.getDefenders().get(1));
        team.getDefenders().get(1).addIncompatiblePlayer(team.getDefenders().get(0));
        solutionTeam=TeamSolver.getTitularTeam(team,5,4,1);
        assertEquals(solutionTeam.getPlayers().size(),10);
    }

    @Test
    public void sortTest(){
        List<Player> players=new ArrayList<>();
        TeamSolver.sort(players);
        assertEquals(players.size(),0);

        TeamSolver.sort(myPlayers);
        assertEquals(myPlayers.get(0).getLevel(),10);
        assertEquals(myPlayers.get(1).getLevel(),9);
        assertEquals(myPlayers.get(2).getLevel(),8);
        assertEquals(myPlayers.get(3).getLevel(),6);
        assertEquals(myPlayers.get(4).getLevel(),5);
    }

}
