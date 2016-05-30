package com.hyperion.dashdroid.radio;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hyperion.dashdroid.R;
import com.hyperion.dashdroid.radio.data.RadioCategory;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> implements RecyclerView.OnClickListener{

    private RecyclerView parentView;
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

    public CategoryAdapter(RecyclerView parentView, ArrayList<RadioCategory> radioChannelCategories) {
        this.parentView = parentView;
        this.radioChannelCategories = radioChannelCategories;
        this.rootCategory = -1;
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
        else
        {
            return radioChannelCategories.get(rootCategory).getSubCategories().size();
        }
    }

    // TODO: maybe we should start a new fragment
    @Override
    public void onClick(View v) {
        Log.d(getClass().getSimpleName(),"root: " + rootCategory +  " onClick: " + v.getTag());
        if(rootCategory == -1){
            rootCategory = (int)v.getTag();
            notifyDataSetChanged();
        }
        else{
            DirbleAsyncTask dirbleAsyncTask = new DirbleAsyncTask(parentView);
            dirbleAsyncTask.setJobType(DirbleAsyncTask.JobType.GET_CHANNELS_FOR_CATEGORY);
            dirbleAsyncTask.execute(radioChannelCategories.get(rootCategory).getSubCategories().get((int)v.getTag()).getID());
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        Log.e(getClass().getSimpleName(), "onAttachedToRecyclerView: ");
        super.onAttachedToRecyclerView(recyclerView);
    }
}
