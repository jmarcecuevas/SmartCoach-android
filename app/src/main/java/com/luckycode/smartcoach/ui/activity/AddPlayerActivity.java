package com.luckycode.smartcoach.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.luckycode.smartcoach.R;
import com.luckycode.smartcoach.common.LuckyActivity;
import com.luckycode.smartcoach.interactor.MainInteractor;
import com.luckycode.smartcoach.presenter.AddPlayerPresenter;
import com.luckycode.smartcoach.ui.viewModel.AddPlayerView;
import com.luckycode.smartcoach.utils.ImagePicker;

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
    @BindView(R.id.segmentedGroup)SegmentedGroup segmentedGroup;
    private static final int PICK_IMAGE_ID = 234;
    private AddPlayerPresenter presenter;
    private String position="Arquero";
    private int level=1;

    @Override
    protected void init() {
        segmentedGroup.setOnCheckedChangeListener(this);
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

    @OnClick(R.id.save)
    public void onClick(View view){
        presenter.savePlayer(name.getText().toString(),surname.getText().toString(),
                "sdsd",position,level);
    }

    @OnClick(R.id.photo)
    public void onPhotoClick(View view){
        //Intent chooseImageIntent = ImagePicker.getPickImageIntent(getActivity());
        //startActivityForResult(chooseImageIntent, PICK_IMAGE_ID);
    }

    @Override
    public void onEmptyNameError() {
        Toast.makeText(this,"Ingrese un nombre",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onEmptySurnameError() {
        Toast.makeText(this,"Ingrese un apellido",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPlayerStored() {
        Toast.makeText(this,"Jugador agregado.",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        level=progress;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_ID && resultCode == RESULT_OK){
            //photo= ImagePicker.getImageFromResult(this,resultCode,data);
            //presenter.uploadPhoto(ImagePicker.getMediaPath());
        }
    }

}
