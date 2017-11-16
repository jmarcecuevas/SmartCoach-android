package com.luckycode.smartcoach.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.luckycode.smartcoach.R;
import com.luckycode.smartcoach.common.LuckyFragment;
import com.luckycode.smartcoach.interactor.MainInteractor;
import com.luckycode.smartcoach.model.Player;
import com.luckycode.smartcoach.model.Team;
import com.luckycode.smartcoach.presenter.LineUpPresenter;
import com.luckycode.smartcoach.presenter.PitchPresenter;
import com.luckycode.smartcoach.ui.activity.MainActivity;
import com.luckycode.smartcoach.ui.adapter.SpinnerAdapter;
import com.luckycode.smartcoach.ui.viewModel.LineUpView;
import com.luckycode.smartcoach.ui.viewModel.PitchView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by marcelocuevas on 10/26/17.
 */

public class PitchFragment extends LuckyFragment implements AdapterView.OnItemSelectedListener,PitchView {
    @BindView(R.id.spinner)
    Spinner spinner;
    private PitchPresenter presenter;
    private Team team;

    @Override
    protected int layout() {
        return R.layout.fragment_pitch;
    }

    @Override
    protected void init() {
        MainInteractor interactor = new MainInteractor(getActivity(), ((MainActivity) getActivity()).getHelper());
        presenter = new PitchPresenter(this, interactor);
        presenter.getTeamFromDB();

        spinner.setOnItemSelectedListener(this);
        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(getActivity(), getResources().getStringArray(R.array.linesUp));
        spinner.setAdapter(spinnerAdapter);
    }

    @Override
    public void onTeamLoaded(Team team) {
        this.team=team;
        showInitialFragment(team,4,3,3,R.layout.fragment_first_lineup);
    }

    private void showInitialFragment(Team team,int cantDef,int cantMid,int cantFor,int layout){
        Fragment myNewFragment = FirstLineUpFragment.newInstance(team,cantDef,cantMid,cantFor,layout);
        String newFragment = myNewFragment.getClass().getName();
        FragmentTransaction t = getActivity().
                getSupportFragmentManager().
                beginTransaction();
        t.replace(R.id.pitch_container, myNewFragment, newFragment);
        t.commit();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                showInitialFragment(team,4,3,3,R.layout.fragment_first_lineup);
                break;
            case 1:
                showInitialFragment(team,4,4,2,R.layout.fragment_four_four_two);
                break;
            case 2:
                showInitialFragment(team,4,5,1,R.layout.fragment_four_five_one);
                break;
            case 3:
                showInitialFragment(team,3,4,3,R.layout.fragment_three_four_three);
                break;
            case 4:
                showInitialFragment(team,3,5,2,R.layout.fragment_three_five_two);
                break;
            case 5:
                showInitialFragment(team,5,4,1,R.layout.fragment_five_four_one);
                break;
            case 6:
                showInitialFragment(team,5,3,2,R.layout.fragment_five_three_two);
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

