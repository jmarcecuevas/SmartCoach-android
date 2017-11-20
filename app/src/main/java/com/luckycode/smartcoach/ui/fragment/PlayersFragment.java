package com.luckycode.smartcoach.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.luckycode.smartcoach.R;
import com.luckycode.smartcoach.common.LuckyFragment;
import com.luckycode.smartcoach.interactor.MainInteractor;
import com.luckycode.smartcoach.model.Player;
import com.luckycode.smartcoach.presenter.PlayersPresenter;
import com.luckycode.smartcoach.ui.activity.MainActivity;
import com.luckycode.smartcoach.ui.activity.PlayerDetailActivity;
import com.luckycode.smartcoach.ui.adapter.PlayerListAdapter;
import com.luckycode.smartcoach.ui.itemDecoration.SpaceItemDecoration;
import com.luckycode.smartcoach.ui.viewModel.PlayersView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by marcelocuevas on 10/26/17.
 */

public class PlayersFragment extends LuckyFragment implements PlayerListAdapter.PlayerListener,PlayersView {
    @BindView(R.id.recycler)RecyclerView recyclerView;
    private List<Player> players;
    private PlayersPresenter presenter;

    @Override
    protected void init() {
        MainInteractor interactor=new MainInteractor(getContext(),((MainActivity)getActivity()).getHelper());
        presenter=new PlayersPresenter(this,interactor);
        SpaceItemDecoration itemDecoration=new SpaceItemDecoration(8,true);
        recyclerView.addItemDecoration(itemDecoration);
    }

    @Override
    public void onResume() {
        super.onResume();
        players=presenter.getPlayers();
        setRecyclerViewUp();
    }

    public void setRecyclerViewUp(){
        PlayerListAdapter adapter=new PlayerListAdapter(getContext(),players,this);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected int layout() {
        return R.layout.fragment_players;
    }


    @Override
    public void onPlayerSelected(Player player) {
        Intent intent=new Intent(getActivity(), PlayerDetailActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("ID",player.getId());
        bundle.putSerializable("PLAYERS", (Serializable) players);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
