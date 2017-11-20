package com.luckycode.smartcoach.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.luckycode.smartcoach.R;
import com.luckycode.smartcoach.common.LuckyActivity;
import com.luckycode.smartcoach.interactor.MainInteractor;
import com.luckycode.smartcoach.presenter.AddPlayerPresenter;
import com.luckycode.smartcoach.ui.viewModel.AddPlayerView;

import butterknife.BindView;
import butterknife.OnClick;
import info.hoang8f.android.segmented.SegmentedGroup;

/**
 * Created by marcelocuevas on 16/11/17.
 */

public class AddPlayerActivity extends LuckyActivity implements AddPlayerView,RadioGroup.OnCheckedChangeListener,
        SeekBar.OnSeekBarChangeListener{

    @BindView(R.id.photo)ImageView photo;
    @BindView(R.id.name)TextView name;
    @BindView(R.id.surname)TextView surname;
    @BindView(R.id.leveltextview)TextView levelTextview;
    @BindView(R.id.segmentedGroup)SegmentedGroup segmentedGroup;
    @BindView(R.id.levelSeekbar)SeekBar levelSeekbar;
    private AddPlayerPresenter presenter;
    private String position;
    private int level;

    @Override
    protected void init() {
        position="Arquero";
        level=1;
        segmentedGroup.setOnCheckedChangeListener(this);
        levelSeekbar.setOnSeekBarChangeListener(this);
        MainInteractor interactor=new MainInteractor(this,getHelper());
        presenter=new AddPlayerPresenter(this,interactor);
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

    @OnClick(R.id.save)
    public void onClick(View view){
        presenter.savePlayer(name.getText().toString(),surname.getText().toString(),
                "bandera",position,level);
    }

    @Override
    public void onEmptyNameError() {
        Toast.makeText(this,"Ingrese un nombre",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEmptySurnameError() {
        Toast.makeText(this,"Ingrese un apellido",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPlayerStored() {
        Toast.makeText(this,"Jugador agregado.",Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        levelTextview.setText("Nivel de juego: "+String.valueOf(progress+1));
        level=progress+1;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.goalkeeperRB:
                position="Arquero";
                break;
            case R.id.defenderRB:
                position="Defensor";
                break;
            case R.id.midfielderRB:
                position="Mediocampista";
                break;
            case R.id.forwardRB:
                position="Delantero";
                break;
        }
    }
}
