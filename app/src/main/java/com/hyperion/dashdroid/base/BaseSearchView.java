package com.hyperion.dashdroid.base;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.SearchView;

import com.hyperion.dashdroid.R;
import com.hyperion.dashdroid.radio.DirbleAsyncTask;

/**
 * Created by Rainer on 13.05.2016.
 */
public class BaseSearchView extends SearchView implements SearchView.OnQueryTextListener {

	private RecyclerView recyclerView;

	public BaseSearchView(Context context) {
		super(context);
		this.recyclerView = null;

		setBackgroundColor(Color.WHITE);

		int search_button_id = getResources().getIdentifier("android:id/search_button", null, null);
		ImageView search_button_view = (ImageView) findViewById(search_button_id);
		search_button_view.setImageResource(R.drawable.search);
		search_button_view.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

		setOnQueryTextListener(this);
	}

	public void setRecyclerView(RecyclerView recyclerView) {
		this.recyclerView = recyclerView;
	}

	@Override
	public boolean onQueryTextSubmit(String query) {

		if(recyclerView != null) {
			new DirbleAsyncTask(recyclerView).execute(DirbleAsyncTask.JobType.SEARCH, query);
		}
		return true;
	}

	@Override
	public boolean onQueryTextChange(String newText) {
		return true;
	}
}
