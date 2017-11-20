package com.luckycode.smartcoach.presenter;

import com.luckycode.smartcoach.interactor.MainInteractor;
import com.luckycode.smartcoach.model.Player;
import com.luckycode.smartcoach.ui.viewModel.PlayersView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.verify;

/**
 * Created by marcelocuevas on 11/20/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class PlayersPresenterTest {
    private MainInteractor interactor;
    private PlayersPresenter presenter;

    @Before
    public void setUp(){
        interactor= Mockito.mock(MainInteractor.class);
        PlayersView view=Mockito.mock(PlayersView.class);
        presenter=new PlayersPresenter(view,interactor);
    }

    @Test
    public void getPlayersTest(){
        presenter.getPlayers();
        verify(interactor).getPlayers();
    }
}
