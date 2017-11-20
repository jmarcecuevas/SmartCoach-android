package com.luckycode.smartcoach.presenter;

import com.luckycode.smartcoach.common.LuckyPresenter;
import com.luckycode.smartcoach.interactor.MainInteractor;
import com.luckycode.smartcoach.model.Player;
import com.luckycode.smartcoach.model.Team;
import com.luckycode.smartcoach.utils.TeamSolver;
import com.luckycode.smartcoach.model.TitularTeam;
import com.luckycode.smartcoach.ui.viewModel.LineUpView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcelocuevas on 11/11/17.
 */

public class LineUpPresenter extends LuckyPresenter<LineUpView>{

    public LineUpPresenter(LineUpView mView) {
        super(mView);
    }

    public void getTitularTeam(Team team,int cantDef,int cantMid,int cantFor){
        TitularTeam myTeam=TeamSolver.getTitularTeam(team,cantDef,cantMid,cantFor);
        List<Player> players=myTeam.getPlayers();
        if(players.size()!=11)
            getView().showTeamError("No se puede armar un equipo con estas condiciones");
        else {
            for (int i = 0; i < players.size(); i++) {
                getView().showPlayerAvatar(players.get(i).getName(), players.get(i)
                        .getPhoto(), i);
            }
            getView().titularTeamReady(myTeam);
        }
    }
}