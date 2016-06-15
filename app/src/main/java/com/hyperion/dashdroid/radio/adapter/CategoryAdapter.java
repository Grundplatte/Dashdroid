package com.hyperion.dashdroid.radio.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hyperion.dashdroid.R;
import com.hyperion.dashdroid.radio.data.RadioCategory;
import com.hyperion.dashdroid.radio.listener.CategoryOnClickListener;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private Context context;

    private CategoryOnClickListener listener;
    private ArrayList<RadioCategory> radioChannelCategories;

    public CategoryAdapter(Context context, ArrayList<RadioCategory> radioChannelCategories) {
        this.radioChannelCategories = radioChannelCategories;
        this.context = context;

        this.listener = new CategoryOnClickListener(context);
    }

    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.radio_fragment_card, parent, false);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(radioChannelCategories.get((int) v.getTag()));
            }
        });

        ImageButton favButton = (ImageButton) card.findViewById(R.id.favButton);
        if (favButton != null)
            favButton.setVisibility(View.INVISIBLE);

        return new ViewHolder(card);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.cardText.setText(radioChannelCategories.get(position).getTitle());
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return radioChannelCategories.size();
    }

    public interface OnCategoryItemClickListener {
        void onItemClick(RadioCategory category);
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
