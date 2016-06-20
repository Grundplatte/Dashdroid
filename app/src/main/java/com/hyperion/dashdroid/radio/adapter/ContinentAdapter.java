package com.hyperion.dashdroid.radio.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hyperion.dashdroid.R;
import com.hyperion.dashdroid.radio.data.RadioContinent;
import com.hyperion.dashdroid.radio.listener.ContinentOnClickListener;

import java.util.ArrayList;


public class ContinentAdapter extends RecyclerView.Adapter<ContinentAdapter.ViewHolder> {

    private ContinentOnClickListener listener;
    private ArrayList<RadioContinent> radioContinents;

    public ContinentAdapter(Context context, ArrayList<RadioContinent> radioContinents) {
        this.radioContinents = radioContinents;
        this.listener = new ContinentOnClickListener(context);
    }

    @Override
    public ContinentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.radio_fragment_card, parent, false);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(radioContinents.get((int) v.getTag()));
            }
        });

        ImageButton favButton = (ImageButton) card.findViewById(R.id.favButton);
        if (favButton != null)
            favButton.setVisibility(View.INVISIBLE);

        return new ViewHolder(card);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.cardText.setText(radioContinents.get(position).getName());
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return radioContinents.size();
    }

    public interface OnContinentItemClickListener {
        void onItemClick(RadioContinent continent);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView cardText;

        public View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            cardText = (TextView) itemView.findViewById(R.id.radioCardText);
            this.itemView = itemView;
        }

    }
}
