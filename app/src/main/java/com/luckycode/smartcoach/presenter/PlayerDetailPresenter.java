package com.luckycode.smartcoach.presenter;

import com.luckycode.smartcoach.common.LuckyPresenter;
import com.luckycode.smartcoach.interactor.MainInteractor;
import com.luckycode.smartcoach.model.Player;
import com.luckycode.smartcoach.ui.viewModel.PlayerDetailView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcelocuevas on 11/18/17.
 */

public class PlayerDetailPresenter extends LuckyPresenter<PlayerDetailView>{
    private MainInteractor interactor;

    public PlayerDetailPresenter(PlayerDetailView mView,MainInteractor interactor) {
        super(mView);
        this.interactor=interactor;
    }

    public void playerItemSelected(Player myPlayer,Player playerSelected) {
        if (myPlayer.equals(playerSelected))
            getView().showErrorTryingToAddIncompatiblePlayer("No se puede seleccionar como incompatible");
        else if (myPlayer.getIncompatibles().contains(playerSelected.getId()))
            getView().showErrorTryingToAddIncompatiblePlayer("Este jugador ya existe en la lista de incompatibles");
        else {
            interactor.saveIncompatiblePlayer(myPlayer,playerSelected);
            getView().showSuccessIncompatiblePlayerAdded("Jugador agregado como incompatible");
        }
    }

    public Player getPlayer(int id){
        return interactor.getPlayer(id);
    }
}