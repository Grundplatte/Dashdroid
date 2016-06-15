package com.hyperion.dashdroid.radio.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hyperion.dashdroid.R;
import com.hyperion.dashdroid.radio.data.RadioChannel;
import com.hyperion.dashdroid.radio.listener.ChannelOnClickListener;
import com.hyperion.dashdroid.radio.listener.ChannelOnFavoriteListener;

import java.util.ArrayList;

/**
 * Created by Rainer on 12.05.2016.
 */
public class ChannelAdapter extends RecyclerView.Adapter<ChannelAdapter.ViewHolder> {
    private final int MAX_NAME_LENGTH = 30;

    private Context context;

    private ChannelOnClickListener click_listener;
    private ChannelOnFavoriteListener fav_listener;
    private ArrayList<RadioChannel> radioChannels;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView cardText;
        public View itemView;
        public ImageButton favButton;
        public boolean favorited;

        public ViewHolder(View itemView) {
            super(itemView);
            cardText = (TextView) itemView.findViewById(R.id.radioCardText);
            favButton = (ImageButton) itemView.findViewById(R.id.favButton);
            this.itemView = itemView;
            favorited = false;
        }

    }

    public ChannelAdapter(Context context, ArrayList<RadioChannel> radioChannels) {
        this.radioChannels = radioChannels;
        this.context = context;

        this.click_listener = new ChannelOnClickListener(context);
        this.fav_listener = new ChannelOnFavoriteListener(context);
    }

    public void setItems(ArrayList<RadioChannel> radioChannels) {
        this.radioChannels = radioChannels;
    }

    @Override
    public ChannelAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.radio_fragment_card, parent, false);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click_listener.onItemClick(radioChannels.get((int) v.getTag()));
            }
        });
        return new ViewHolder(card);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        String name = radioChannels.get(position).getName();
        if (name.length() >= MAX_NAME_LENGTH)
            holder.cardText.setText(name.substring(0, MAX_NAME_LENGTH));
        else
            holder.cardText.setText(name);
        holder.itemView.setTag(position);

        holder.favButton.setClickable(true);

        if (radioChannels.get(position).isFavorited()) {
            holder.favButton.setBackgroundResource(R.drawable.ic_star_black_48dp);
        }

        holder.favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioChannel channel = radioChannels.get((int) ((View) v.getParent().getParent()).getTag());
                if (channel.isFavorited()) {
                    v.setBackgroundResource(R.drawable.ic_star_border_black_48dp);
                    // delete
                    channel.setFavorited(false);
                    fav_listener.onUnFavorite(channel);
                } else {
                    v.setBackgroundResource(R.drawable.ic_star_black_48dp);
                    // add
                    channel.setFavorited(true);
                    fav_listener.onFavorite(channel);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return radioChannels.size();
    }

    public interface OnChannelItemClickListener {
        void onItemClick(RadioChannel channel);
    }

    public interface OnChannelFavoriteListener {
        void onFavorite(RadioChannel channel);

        void onUnFavorite(RadioChannel channel);
    }
}
