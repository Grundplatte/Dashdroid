package com.hyperion.dashdroid.radio;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hyperion.dashdroid.R;
import com.hyperion.dashdroid.radio.data.RadioChannel;

import java.util.ArrayList;

/**
 * Created by Rainer on 12.05.2016.
 */
public class ChannelAdapter extends RecyclerView.Adapter<ChannelAdapter.ViewHolder> {

    private final int MAX_NAME_LENGTH = 30;

    private OnChannelItemClickListener listener;
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

    public ChannelAdapter(ArrayList<RadioChannel> radioChannels, OnChannelItemClickListener listener) {
        this.radioChannels = radioChannels;
        this.listener = listener;
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
                listener.onItemClick(radioChannels.get((int) v.getTag()));
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
                    listener.onUnFavorite(channel);
                } else {
                    v.setBackgroundResource(R.drawable.ic_star_black_48dp);
                    // add
                    channel.setFavorited(true);
                    listener.onFavorite(channel);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return radioChannels.size();
    }

    interface OnChannelItemClickListener {
        void onItemClick(RadioChannel channel);

        void onFavorite(RadioChannel channel);

        void onUnFavorite(RadioChannel channel);
    }
}
