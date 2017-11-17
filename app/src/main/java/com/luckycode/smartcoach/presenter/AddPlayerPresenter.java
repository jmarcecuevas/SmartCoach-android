package com.luckycode.smartcoach.presenter;

import com.luckycode.smartcoach.common.LuckyPresenter;
import com.luckycode.smartcoach.interactor.MainInteractor;
import com.luckycode.smartcoach.model.Player;
import com.luckycode.smartcoach.ui.viewModel.AddPlayerView;

/**
 * Created by marcelocuevas on 17/11/17.
 */

public class AddPlayerPresenter extends LuckyPresenter<AddPlayerView>{
    private MainInteractor interactor;

    public AddPlayerPresenter(AddPlayerView mView,MainInteractor interactor) {
        super(mView);
        this.interactor=interactor;
    }

    public void savePlayer(String name,String surname,String photo,String position,int level){
        if(name.isEmpty() || surname.isEmpty()) {
            if (name.isEmpty())
                getView().onEmptyNameError();
            if(surname.isEmpty())
                getView().onEmptySurnameError();
        }else{
            interactor.savePlayer(new Player(name,surname,photo,position,level));
        }
    }
}