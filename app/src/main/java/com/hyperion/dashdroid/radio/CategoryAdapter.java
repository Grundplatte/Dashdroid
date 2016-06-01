package com.hyperion.dashdroid.radio;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hyperion.dashdroid.R;
import com.hyperion.dashdroid.radio.data.RadioCategory;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{

    private CategoryItemClickedListener listener;
    private ArrayList<RadioCategory> radioChannelCategories;
    private int rootCategory;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView cardText;

        public View itemView;
        public ViewHolder(View itemView) {
            super(itemView);
            cardText = (TextView) itemView.findViewById(R.id.radioCardText);
            this.itemView = itemView;
        }

    }

    public int getRootCategory() {
        return rootCategory;
    }

    public CategoryAdapter(ArrayList<RadioCategory> radioChannelCategories, CategoryItemClickedListener listener) {
        this.radioChannelCategories = radioChannelCategories;
        this.rootCategory = -1;
        this.listener = listener;
    }

    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.radio_fragment_card, parent, false);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rootCategory == -1){
                    rootCategory = (int)v.getTag();
                    notifyDataSetChanged();
                }
                else {
                    listener.onItemClicked(radioChannelCategories.get(rootCategory).getSubCategories().get((int)v.getTag()));
                }
            }
        });
        return new ViewHolder(card);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(rootCategory == -1) {
            holder.cardText.setText(radioChannelCategories.get(position).getTitle());
        }
        else {
            holder.cardText.setText(radioChannelCategories.get(rootCategory).getSubCategories().get(position).getTitle());
        }
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        if(rootCategory == -1) {
            return radioChannelCategories.size();
        }
        return radioChannelCategories.get(rootCategory).getSubCategories().size();
    }

    interface CategoryItemClickedListener {
        void onItemClicked(RadioCategory category);
    }
}
