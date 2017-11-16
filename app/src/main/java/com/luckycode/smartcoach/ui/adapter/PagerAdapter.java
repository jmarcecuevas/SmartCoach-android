package com.luckycode.smartcoach.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.luckycode.smartcoach.common.LuckyFragment;
import com.luckycode.smartcoach.ui.fragment.IncompatiblesFragment;
import com.luckycode.smartcoach.ui.fragment.PitchFragment;
import com.luckycode.smartcoach.ui.fragment.PlayersFragment;

/**
 * Created by marcelocuevas on 10/26/17.
 */

public class PagerAdapter extends FragmentStatePagerAdapter{
    private int numTabs;
    private String[] names={"Jugadores","Alineación"};

    public PagerAdapter(FragmentManager fm, int numTabs) {
        super(fm);
        this.numTabs=numTabs;
    }

    @Override
    public Fragment getItem(int position) {
        String.valueOf(position);
        switch (position){
            case 0:
                return new PlayersFragment();
            case 1:
                return new PitchFragment();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return names[position];
    }

    @Override
    public int getCount() {
        return numTabs;
    }
}

