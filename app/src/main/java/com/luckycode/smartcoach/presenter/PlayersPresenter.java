package com.luckycode.smartcoach.presenter;

import com.luckycode.smartcoach.common.LuckyPresenter;
import com.luckycode.smartcoach.interactor.MainInteractor;
import com.luckycode.smartcoach.model.Player;
import com.luckycode.smartcoach.ui.viewModel.PlayersView;

import java.util.List;

/**
 * Created by marcelocuevas on 11/19/17.
 */

public class PlayersPresenter extends LuckyPresenter<PlayersView> {
    private MainInteractor interactor;

    public PlayersPresenter(PlayersView mView,MainInteractor interactor) {
        super(mView);
        this.interactor=interactor;
    }

    public List<Player> getPlayers(){
        return interactor.getPlayers();
    }
}
