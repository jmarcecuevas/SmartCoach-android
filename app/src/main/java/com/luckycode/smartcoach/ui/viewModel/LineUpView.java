package com.luckycode.smartcoach.ui.viewModel;

import com.luckycode.smartcoach.model.Player;
import com.luckycode.smartcoach.model.TitularTeam;

import java.util.List;

/**
 * Created by marcelocuevas on 11/11/17.
 */

public interface LineUpView {
    void titularTeamReady(TitularTeam team);
    void showPlayerAvatar(String name,String photo,int position);
    void showTeamError(String message);
}
