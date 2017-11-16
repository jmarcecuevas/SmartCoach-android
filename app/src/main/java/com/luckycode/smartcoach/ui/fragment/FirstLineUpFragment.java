package com.luckycode.smartcoach.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.luckycode.smartcoach.R;
import com.luckycode.smartcoach.common.LuckyFragment;
import com.luckycode.smartcoach.interactor.MainInteractor;
import com.luckycode.smartcoach.model.Player;
import com.luckycode.smartcoach.model.Team;
import com.luckycode.smartcoach.presenter.LineUpPresenter;
import com.luckycode.smartcoach.ui.activity.MainActivity;
import com.luckycode.smartcoach.ui.viewModel.LineUpView;

import java.util.List;

import butterknife.BindViews;

/**
 * Created by marcelocuevas on 11/11/17.
 */

public class FirstLineUpFragment extends LuckyFragment implements LineUpView{
    @BindViews({R.id.first,R.id.second,R.id.third,R.id.fourth,R.id.fifth,R.id.sixth,
                R.id.seventh,R.id.eighth,R.id.nineth,
                R.id.tenth,R.id.eleventh}) List<ImageView> playersViews;
    private LineUpPresenter presenter;
    private int layout,cantDef,cantMid,cantFor;
    private Team team;

    public static FirstLineUpFragment newInstance(Team team,int cantDef,int cantMid,int cantFor,int layout) {
        FirstLineUpFragment myFragment = new FirstLineUpFragment();
        Bundle args = new Bundle();
        args.putInt("layout", layout);
        args.putSerializable("team",team);
        args.putInt("cantDef",cantDef);
        args.putInt("cantMid",cantMid);
        args.putInt("cantFor",cantFor);
        myFragment.setArguments(args);
        return myFragment;
    }

    @Override
    protected void init() {
        MainInteractor interactor=new MainInteractor(getActivity(),((MainActivity)getActivity()).getHelper());
        presenter=new LineUpPresenter(this,interactor);
        presenter.getTitularTeam(team,cantDef,cantMid,cantFor);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layout=getArguments().getInt("layout",R.layout.fragment_first_lineup);
        team= (Team) getArguments().getSerializable("team");
        cantDef=getArguments().getInt("cantDef",4);
        cantMid=getArguments().getInt("cantMid",3);
        cantFor=getArguments().getInt("cantFor",3);
    }

    @Override
    protected int layout() {
        return layout;
    }

    @Override
    public void onPlayerSelected(Player player) {

    }

    @Override
    public void showPlayerAvatar(String name, String photo, int position) {
//        int id=getResources().getIdentifier(photo,"drawable",getContext().getPackageName());
//        playersViews.get(position).setImageResource(id);
    }

}
