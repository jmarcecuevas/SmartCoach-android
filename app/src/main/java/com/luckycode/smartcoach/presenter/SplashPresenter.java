package com.luckycode.smartcoach.presenter;

import com.luckycode.smartcoach.common.LuckyPresenter;
import com.luckycode.smartcoach.interactor.MainInteractor;
import com.luckycode.smartcoach.model.Player;
import com.luckycode.smartcoach.ui.viewModel.SplashView;

import java.util.List;
import java.util.Set;

/**
 * Created by marcelocuevas on 10/14/17.
 */

public class SplashPresenter extends LuckyPresenter<SplashView> implements MainInteractor.InteractorListener{
    private MainInteractor interactor;

    public SplashPresenter(SplashView mView, MainInteractor interactor) {
        super(mView);
        this.interactor=interactor;
        this.interactor.setListener(this);
    }

    public void loadData() {
        interactor.loadDatabaseData();
    }

    @Override
    public void onLocalJSONStored(List<Player> players) {
        getView().onDataReady(players);
    }

    @Override
    public void onDataLoaded(List<Player> players) {
        getView().onDataReady(players);
    }
}
