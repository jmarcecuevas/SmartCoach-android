package com.luckycode.smartcoach.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.luckycode.smartcoach.R;
import com.luckycode.smartcoach.common.LuckyActivity;
import com.luckycode.smartcoach.interactor.MainInteractor;
import com.luckycode.smartcoach.model.Player;
import com.luckycode.smartcoach.presenter.SplashPresenter;
import com.luckycode.smartcoach.ui.viewModel.SplashView;

import java.io.Serializable;
import java.util.List;

public class SplashActivity extends LuckyActivity implements SplashView {
    private SplashPresenter mPresenter;

    @Override
    protected void init() {
        MainInteractor interactor=new MainInteractor(this,getHelper());
        mPresenter=new SplashPresenter(this,interactor);
        mPresenter.loadData();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected Class getFragmentToAdd() {
        return null;
    }

    @Override
    protected int getFragmentLayout() {
        return R.id.main_container;
    }

    @Override
    public void onDataReady(List<Player> players) {
//        Bundle bundle=new Bundle();
//        bundle.putSerializable("PLAYERS",(Serializable)players);
        goToMainActivity();
    }

    private void goToMainActivity(){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
        finish();
    }
}
