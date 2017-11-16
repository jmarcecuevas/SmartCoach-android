package com.luckycode.smartcoach.presenter;

import com.luckycode.smartcoach.common.LuckyPresenter;
import com.luckycode.smartcoach.interactor.MainInteractor;
import com.luckycode.smartcoach.model.Player;
import com.luckycode.smartcoach.model.Team;
import com.luckycode.smartcoach.ui.fragment.PitchFragment;
import com.luckycode.smartcoach.ui.viewModel.PitchView;

import java.util.List;

/**
 * Created by marcelocuevas on 11/14/17.
 */

public class PitchPresenter extends LuckyPresenter<PitchView>{
    private MainInteractor interactor;

    public PitchPresenter(PitchView mView,MainInteractor interactor) {
        super(mView);
        this.interactor=interactor;
    }

    public void getTeamFromDB(){
        List<Player> goalkeepers=interactor.getGoalkeepers();
        List<Player> defenders=interactor.getDefenders();
        List<Player> midfielders=interactor.getMidfielders();
        List<Player> forwards=interactor.getForwards();
        Team team=new Team(goalkeepers,defenders,midfielders,forwards);
        getView().onTeamLoaded(team);
    }
}