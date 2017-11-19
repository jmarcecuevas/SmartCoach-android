package com.luckycode.smartcoach.ui.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.luckycode.smartcoach.R;
import com.luckycode.smartcoach.common.LuckyActivity;
import com.luckycode.smartcoach.interactor.MainInteractor;
import com.luckycode.smartcoach.model.Player;
import com.luckycode.smartcoach.presenter.LineUpPresenter;
import com.luckycode.smartcoach.ui.adapter.PagerAdapter;
import com.luckycode.smartcoach.ui.fragment.PitchFragment;
import com.luckycode.smartcoach.ui.viewModel.LineUpView;

import java.util.List;

import butterknife.BindView;

public class MainActivity extends LuckyActivity{
    @BindView(R.id.toolbar)Toolbar toolbar;
    @BindView(R.id.container)ViewPager viewPager;
    @BindView(R.id.tabs)TabLayout tabs;

    @Override
    protected void init() {
        setSupportActionBar(toolbar);

        PagerAdapter mPagerAdapter = new PagerAdapter(getSupportFragmentManager(),2);
        viewPager.setAdapter(mPagerAdapter);

        tabs.setupWithViewPager(viewPager);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main2;
    }

    @Override
    protected Class getFragmentToAdd() {
        return null;
    }

    @Override
    protected int getFragmentLayout() {
        return R.id.fragment_container;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add) {
            Intent intent=new Intent(this,AddPlayerActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
