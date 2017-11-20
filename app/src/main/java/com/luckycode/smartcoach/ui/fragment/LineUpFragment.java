package com.luckycode.smartcoach.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.luckycode.smartcoach.R;
import com.luckycode.smartcoach.common.LuckyFragment;
import com.luckycode.smartcoach.interactor.MainInteractor;
import com.luckycode.smartcoach.interactor.listener.LineUpListener;
import com.luckycode.smartcoach.model.Team;
import com.luckycode.smartcoach.model.TitularTeam;
import com.luckycode.smartcoach.presenter.LineUpPresenter;
import com.luckycode.smartcoach.ui.activity.MainActivity;
import com.luckycode.smartcoach.ui.viewModel.LineUpView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindViews;

/**
 * Created by marcelocuevas on 11/11/17.
 */

public class LineUpFragment extends LuckyFragment implements LineUpView{
    @BindViews({R.id.first,R.id.second,R.id.third,R.id.fourth,R.id.fifth,R.id.sixth,
                R.id.seventh,R.id.eighth,R.id.nineth,
                R.id.tenth,R.id.eleventh}) List<ImageView> playersViews;
    private LineUpPresenter presenter;
    private int layout,cantDef,cantMid,cantFor;
    private LineUpListener listener;
    private Team team;
    private TitularTeam titularTeam;

    public static LineUpFragment newInstance(Team team, int cantDef, int cantMid, int cantFor, int layout, LineUpListener listener) {
        LineUpFragment myFragment = new LineUpFragment();
        Bundle args = new Bundle();
        args.putInt("layout", layout);
        args.putSerializable("team",team);
        args.putInt("cantDef",cantDef);
        args.putInt("cantMid",cantMid);
        args.putInt("cantFor",cantFor);
        args.putParcelable("listener",listener);
        myFragment.setArguments(args);
        return myFragment;
    }

    @Override
    protected void init() {
        presenter=new LineUpPresenter(this);
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
        listener= (LineUpListener) getArguments().getParcelable("listener");
    }

    @Override
    protected int layout() {
        return layout;
    }

    public void showPlayers(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Mejor equipo");
        ListView playersList = new ListView(getContext());
        ArrayAdapter<String> modeAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,
                android.R.id.text1, presenter.getPlayersNames(titularTeam.getPlayers()));
        playersList.setAdapter(modeAdapter);
        builder.setView(playersList);
        final Dialog dialog = builder.create();
        dialog.setCancelable(true);
        dialog.show();
    }

    @Override
    public void titularTeamReady(TitularTeam team) {
        titularTeam=team;
        listener.onTitularTeamCalculated(team);
    }

    @Override
    public void showPlayerAvatar(String name, String photo, int position) {
        int id=getResources().getIdentifier(photo,"drawable",getContext().getPackageName());
        Picasso.with(getContext()).load(id).centerCrop().resize(58,58).into(playersViews.get(position));
    }

    @Override
    public void showTeamError(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

}
