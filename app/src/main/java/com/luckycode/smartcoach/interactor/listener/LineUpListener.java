package com.luckycode.smartcoach.interactor.listener;

import com.luckycode.smartcoach.model.TitularTeam;

import java.io.Serializable;

/**
 * Created by marcelocuevas on 11/19/17.
 */

public interface LineUpListener extends Serializable {
    void onTitularTeamCalculated(TitularTeam titularTeam);
}