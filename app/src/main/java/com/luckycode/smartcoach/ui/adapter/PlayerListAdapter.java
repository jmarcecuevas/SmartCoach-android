package com.luckycode.smartcoach.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.luckycode.smartcoach.R;
import com.luckycode.smartcoach.model.Player;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by marcelocuevas on 10/26/17.
 */

public class PlayerListAdapter extends RecyclerView.Adapter<PlayerListAdapter.PlayerViewHolder>{
    private Context context;
    private List<Player> players;
    private PlayerListener listener;

    public PlayerListAdapter(Context context,List<Player> players,PlayerListener listener){
        this.context=context;
        this.players=players;
        this.listener=listener;
    }

    @Override
    public PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.player_item_holder,parent,false);
        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlayerViewHolder holder, int position) {
        holder.bind(players.get(position));
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public void update(List<Player> players){
        this.players=players;
        notifyDataSetChanged();
    }

    class PlayerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView name;
        private TextView surname;
        private TextView position;
        private CircleImageView photo;
        private TextView level;
        private TextView dorsal;

        public PlayerViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            name=itemView.findViewById(R.id.name);
            surname=itemView.findViewById(R.id.surname);
            photo=itemView.findViewById(R.id.photo);
            position=itemView.findViewById(R.id.position);
        }

        public void bind(Player player){
            name.setText(player.getName());
            surname.setText(player.getSurname());
            position.setText(player.getPosition());
            int id = context.getResources().getIdentifier(player.getPhoto(), "drawable", context.getPackageName());
            photo.setImageResource(id);
        }

        @Override
        public void onClick(View v) {
            listener.onPlayerSelected(players.get(getPosition()));
        }
    }

    public interface PlayerListener{
        void onPlayerSelected(Player player);
    }
}