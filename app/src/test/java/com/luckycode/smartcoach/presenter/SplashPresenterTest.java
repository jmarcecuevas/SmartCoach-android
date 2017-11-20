package com.luckycode.smartcoach.presenter;

import com.luckycode.smartcoach.interactor.MainInteractor;
import com.luckycode.smartcoach.model.Player;
import com.luckycode.smartcoach.ui.viewModel.SplashView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;

/**
 * Created by marcelocuevas on 11/20/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class SplashPresenterTest {
    private SplashView view;
    private MainInteractor interactor;
    private SplashPresenter presenter;

    @Before
    public void setUp(){
        view=Mockito.mock(SplashView.class);
        interactor=Mockito.mock(MainInteractor.class);
        presenter= new SplashPresenter(view,interactor);
    }

    @Test
    public void loadDataTest() {
        presenter.loadData();
        verify(interactor).loadDatabaseData();
    }


    @Test
    public void onLocalJSONStoredTest(){
        List<Player> players=new ArrayList<>();
        presenter.onLocalJSONStored(players);
        verify(view).onDataReady(players);
    }

    @Test
    public void onDateLoaded(){
        List<Player> players=new ArrayList<>();
        presenter.onDataLoaded(players);
        verify(view).onDataReady(players);

    }

}
