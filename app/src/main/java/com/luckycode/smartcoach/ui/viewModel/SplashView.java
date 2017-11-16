package com.luckycode.smartcoach.ui.viewModel;

import com.luckycode.smartcoach.model.Player;

import java.util.List;

/**
 * Created by marcelocuevas on 10/14/17.
 */

public interface SplashView {
    void onDataReady(List<Player> players);
}
