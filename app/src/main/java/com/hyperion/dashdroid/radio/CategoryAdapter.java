package com.hyperion.dashdroid.radio;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hyperion.dashdroid.R;

import java.util.ArrayList;

/**
 * Created by Rainer on 12.05.2016.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> implements RecyclerView.OnClickListener{

    private ArrayList<RadioChannelCategory> radioChannelCategories;
    private int rootCategory;
    private RecyclerView parent;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView cardText;
        public View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            cardText = (TextView) itemView.findViewById(R.id.radioCardText);
            this.itemView = itemView;
        }

    }
    public CategoryAdapter(RecyclerView parent, ArrayList<RadioChannelCategory> radioChannelCategories) {
        this.radioChannelCategories = radioChannelCategories;
        this.rootCategory = -1;
        this.parent = parent;
    }

    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.radio_fragment_card, parent, false);
        card.setOnClickListener(this);
        return new ViewHolder(card);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(rootCategory == -1) {
            holder.cardText.setText(radioChannelCategories.get(position).getTitle());
            holder.itemView.setTag(position);
        }
        else
        {
            holder.cardText.setText(radioChannelCategories.get(rootCategory).getSubCategories().get(position).getTitle());
            holder.itemView.setTag(position);
        }
    }

    @Override
    public int getItemCount() {
        if(rootCategory == -1) {
            return radioChannelCategories.size();
        }
        else
        {
            return radioChannelCategories.get(rootCategory).getSubCategories().size();
        }
    }

    @Override
    public void onClick(View v) {
        Log.d(getClass().getSimpleName(),"root: " + rootCategory +  " onClick: " + v.getTag());
        if(rootCategory == -1){
            rootCategory = (int)v.getTag();
            notifyDataSetChanged();
        }
        else{
            new DirbleAsyncTask(parent).execute(DirbleAsyncTask.JobType.GET_CHANNELS_FOR_CATEGORY,
                    radioChannelCategories.get(rootCategory).getSubCategories().get((int)v.getTag()).getID());
        }
    }
}
