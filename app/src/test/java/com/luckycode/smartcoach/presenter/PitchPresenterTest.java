package com.luckycode.smartcoach.presenter;

import com.luckycode.smartcoach.interactor.MainInteractor;
import com.luckycode.smartcoach.model.Player;
import com.luckycode.smartcoach.model.Team;
import com.luckycode.smartcoach.ui.viewModel.PitchView;

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
public class PitchPresenterTest {
    private MainInteractor interactor;
    private PitchView view;
    private PitchPresenter presenter;

    @Before
    public void setUp(){
        interactor= Mockito.mock(MainInteractor.class);
        view=Mockito.mock(PitchView.class);
        presenter=new PitchPresenter(view,interactor);
    }

    @Test
    public void getTeamFromDBTest(){
        presenter.getTeamFromDB();
        verify(interactor).getGoalkeepers();
        verify(interactor).getDefenders();
        verify(interactor).getMidfielders();
        verify(interactor).getForwards();
        verify(view).onTeamLoaded(new Team());
    }
}
