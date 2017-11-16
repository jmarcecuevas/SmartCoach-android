package com.luckycode.smartcoach.ui.activity;

import com.luckycode.smartcoach.R;
import com.luckycode.smartcoach.common.LuckyActivity;

/**
 * Created by marcelocuevas on 16/11/17.
 */

public class AddPlayerActivity extends LuckyActivity {

    @Override
    protected void init() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_add_player;
    }

    @Override
    protected Class getFragmentToAdd() {
        return null;
    }

    @Override
    protected int getFragmentLayout() {
        return 0;
    }
}
