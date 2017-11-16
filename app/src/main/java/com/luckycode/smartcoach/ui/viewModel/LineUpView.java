package com.luckycode.smartcoach.ui.viewModel;

import com.luckycode.smartcoach.model.Player;

import java.util.List;

/**
 * Created by marcelocuevas on 11/11/17.
 */

public interface LineUpView {
    void onPlayerSelected(Player player);
    void showPlayerAvatar(String name,String photo,int position);
}
