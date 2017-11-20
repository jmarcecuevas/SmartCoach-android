package com.luckycode.smartcoach.presenter;

import com.luckycode.smartcoach.interactor.MainInteractor;
import com.luckycode.smartcoach.model.Player;
import com.luckycode.smartcoach.ui.viewModel.PlayerDetailView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

/**
 * Created by marcelocuevas on 11/20/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class PlayerDetailPresenterTest {
    private MainInteractor interactor;
    private PlayerDetailView view;
    private PlayerDetailPresenter presenter;
    private Player p1,p2,p3,p4,p5;

    @Before
    public void setUp(){
        interactor= Mockito.mock(MainInteractor.class);
        view=Mockito.mock(PlayerDetailView.class);
        presenter=new PlayerDetailPresenter(view,interactor);

        p1=new Player("Esteban","Mendez","photo","Delantero",5);
        p1.setId(1);
        p2=new Player("Adrian","Silva","adrian_silva","Mediocampista",5);
        p2.setId(1);
        p3=new Player("Cristian","Gomez","crisGomez","Delantero",4);
        p3.setId(3);
        p4=new Player("Esteban","Perez","estePerez","Delantero",9);
        p4.setId(4);
        p5=new Player("Ernesto","Villagra","ernVillagra","Arquero",8);
        p5.setId(5);

        p1.addIncompatible(p2.getId());
        p2.addIncompatible(p1.getId());
    }

    @Test
    public void getPlayer(){
        presenter.getPlayer(32);
        verify(interactor).getPlayer(32);

        presenter.getPlayer(12);
        verify(interactor).getPlayer(12);
    }

    @Test
    public void playerItemSelected(){
        //Mismo jugador(comparten ID)
        presenter.playerItemSelected(p1,p2);
        verify(view).showErrorTryingToAddIncompatiblePlayer("No se puede seleccionar como incompatible");

        //Ahora son distintos
        p2.setId(2);
        p1.addIncompatible(p2.getId());
        p2.addIncompatible(p1.getId());
        presenter.playerItemSelected(p1,p2);
        verify(view).showErrorTryingToAddIncompatiblePlayer("Este jugador ya existe en la lista de incompatibles");

        //Se puede agregar como incompatible
        presenter.playerItemSelected(p3,p5);
        verify(interactor).saveIncompatiblePlayer(p3,p5);
        verify(view).showSuccessIncompatiblePlayerAdded("Jugador agregado como incompatible");
    }

    @Test
    public void getPlayersNameTest(){
        List<Player> players=new ArrayList<>();
        List<String> names=presenter.getPlayersNames(players);
        assertEquals(names.size(),0);

        players.add(p1); players.add(p2); players.add(p3);
        players.add(p4); players.add(p5);
        names=presenter.getPlayersNames(players);
        assertEquals(names.size(),5);
        assertEquals(names.get(0),"Esteban Mendez");
        assertEquals(names.get(1),"Adrian Silva");
        assertEquals(names.get(2),"Cristian Gomez");
        assertEquals(names.get(3),"Esteban Perez");
        assertEquals(names.get(4),"Ernesto Villagra");
    }
}
