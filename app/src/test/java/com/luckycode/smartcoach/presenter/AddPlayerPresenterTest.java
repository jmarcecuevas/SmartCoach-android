package com.luckycode.smartcoach.presenter;

import com.luckycode.smartcoach.interactor.MainInteractor;
import com.luckycode.smartcoach.model.Player;
import com.luckycode.smartcoach.ui.viewModel.AddPlayerView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * Created by marcelocuevas on 11/20/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class AddPlayerPresenterTest {
    private MainInteractor interactor;
    private AddPlayerView view;
    private AddPlayerPresenter presenter;

    @Before
    public void setUp(){
        interactor= Mockito.mock(MainInteractor.class);
        view=Mockito.mock(AddPlayerView.class);
        presenter=new AddPlayerPresenter(view,interactor);
    }

    @Test
    public void savePlayerTest(){
        String name="";
        String surname="";
        presenter.savePlayer(name,surname,"photo","Delantero",7);
        verify(view).onEmptyNameError();
        verify(view).onEmptySurnameError();

        String name2="Esteban";
        String surname2="Mendez";
        presenter.savePlayer(name2,surname2,"myPhoto","Defensor",5);
        verify(view).onPlayerStored();
    }
}
