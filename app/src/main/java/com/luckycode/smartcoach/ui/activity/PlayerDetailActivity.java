package com.luckycode.smartcoach.ui.activity;

import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.luckycode.smartcoach.R;
import com.luckycode.smartcoach.common.LuckyActivity;
import com.luckycode.smartcoach.interactor.MainInteractor;
import com.luckycode.smartcoach.model.Player;
import com.luckycode.smartcoach.presenter.AddPlayerPresenter;
import com.luckycode.smartcoach.presenter.PlayerDetailPresenter;
import com.luckycode.smartcoach.ui.adapter.PlayerListAdapter;
import com.luckycode.smartcoach.ui.itemDecoration.SpaceItemDecoration;
import com.luckycode.smartcoach.ui.view.HeaderView;
import com.luckycode.smartcoach.ui.viewModel.PlayerDetailView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlayerDetailActivity extends LuckyActivity implements AppBarLayout.OnOffsetChangedListener,PlayerListAdapter.PlayerListener,
        PlayerDetailView,AdapterView.OnItemSelectedListener{

    @BindView(R.id.toolbar_header_view) HeaderView toolbarHeaderView;
    @BindView(R.id.float_header_view) HeaderView floatHeaderView;
    @BindView(R.id.photo)ImageView photo;
    @BindView(R.id.appbar) AppBarLayout appBarLayout;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.recycler)RecyclerView recyclerView;
    @BindView(R.id.levelTextview)TextView levelTextview;
    @BindView(R.id.spinner)Spinner spinner;
    private PlayerDetailPresenter presenter;
    private PlayerListAdapter adapter;
    private boolean isHideToolbarView = false;
    private Player player;
    private boolean spinnerFirstTime=true;
    private int playerID;
    private List<Player> players;

    @Override
    protected void init() {
        MainInteractor interactor=new MainInteractor(this,getHelper());
        presenter=new PlayerDetailPresenter(this,interactor);
        Bundle bundle= getIntent().getExtras();
        playerID=bundle.getInt("ID");
        player=presenter.getPlayer(playerID);
        players= (List<Player>) bundle.getSerializable("PLAYERS");
        levelTextview.setText("Nivel de juego: "+player.getLevel());

        setActionBar();
        initUi();
        setRecyclerViewUp();
        setSpinnerView();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_player_detail;
    }

    @Override
    protected Class getFragmentToAdd() {
        return null;
    }

    @Override
    protected int getFragmentLayout() {
        return 0;
    }

    private void setActionBar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(null);
    }

    private void setSpinnerView(){
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this,R.layout.players_spinner_item,presenter.getPlayersNames(players));
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(this);
    }

    private void initUi() {
        appBarLayout.addOnOffsetChangedListener(this);
        toolbarHeaderView.bindTo(player.getName()+" "+player.getSurname(), player.getPosition());
        floatHeaderView.bindTo(player.getName()+" "+player.getSurname(), player.getPosition());
        int id = getResources().getIdentifier(player.getPhoto(), "drawable", getPackageName());
        photo.setImageResource(id);
    }

    public void setRecyclerViewUp(){
        adapter=new PlayerListAdapter(this,player.getIncompatiblesPlayers(),this);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        SpaceItemDecoration itemDecoration=new SpaceItemDecoration(8,true);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(offset) / (float) maxScroll;

        if (percentage == 1f && isHideToolbarView) {
            toolbarHeaderView.setVisibility(View.VISIBLE);
            isHideToolbarView = !isHideToolbarView;

        } else if (percentage < 1f && !isHideToolbarView) {
            toolbarHeaderView.setVisibility(View.GONE);
            isHideToolbarView = !isHideToolbarView;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPlayerSelected(Player player) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(!spinnerFirstTime)
            presenter.playerItemSelected(player,players.get(position));
        spinnerFirstTime=false;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void showErrorTryingToAddIncompatiblePlayer(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccessIncompatiblePlayerAdded(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
        Player myplayer=presenter.getPlayer(player.getId());
        adapter.update(myplayer.getIncompatiblesPlayers());

    }
}

