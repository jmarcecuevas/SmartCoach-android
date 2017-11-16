package com.luckycode.smartcoach.ui.activity;

import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.luckycode.smartcoach.R;
import com.luckycode.smartcoach.model.Player;
import com.luckycode.smartcoach.ui.adapter.PlayerListAdapter;
import com.luckycode.smartcoach.ui.itemDecoration.SpaceItemDecoration;
import com.luckycode.smartcoach.ui.view.HeaderView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlayerDetailActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener,PlayerListAdapter.PlayerListener {
    @BindView(R.id.toolbar_header_view) HeaderView toolbarHeaderView;
    @BindView(R.id.float_header_view) HeaderView floatHeaderView;
    @BindView(R.id.photo)ImageView photo;
    @BindView(R.id.appbar) AppBarLayout appBarLayout;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.recycler)RecyclerView recyclerView;
    private boolean isHideToolbarView = false;
    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_detail);
        Bundle bundle= getIntent().getExtras();
        player= (Player) bundle.getSerializable("PLAYER");
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(null);

        initUi();
        setRecyclerViewUp();
    }

    private void initUi() {
        appBarLayout.addOnOffsetChangedListener(this);
        toolbarHeaderView.bindTo(player.getName()+" "+player.getSurname(), player.getPosition());
        floatHeaderView.bindTo(player.getName()+" "+player.getSurname(), player.getPosition());
        int id = getResources().getIdentifier(player.getPhoto(), "drawable", getPackageName());
        photo.setImageResource(id);
    }

    public void setRecyclerViewUp(){
        PlayerListAdapter adapter=new PlayerListAdapter(this,player.getIncompatiblesPlayers(),this);
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
}

