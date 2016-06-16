package com.hyperion.dashdroid.radio.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hyperion.dashdroid.R;
import com.hyperion.dashdroid.radio.data.RadioCountry;
import com.hyperion.dashdroid.radio.listener.CountryOnClickListener;

import java.util.ArrayList;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {

    private CountryOnClickListener listener;
    private ArrayList<RadioCountry> radioCountries;

    public CountryAdapter(Context context, ArrayList<RadioCountry> radioCountries) {
        this.radioCountries = radioCountries;
        this.listener = new CountryOnClickListener(context);
    }

    @Override
    public CountryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.radio_fragment_card, parent, false);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(radioCountries.get((int) v.getTag()));
            }
        });

        ImageButton favButton = (ImageButton) card.findViewById(R.id.favButton);
        if (favButton != null)
            favButton.setVisibility(View.INVISIBLE);

        return new ViewHolder(card);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.cardText.setText(radioCountries.get(position).getName());
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return radioCountries.size();
    }

    public interface OnCountryItemClickListener {
        void onItemClick(RadioCountry country);
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
