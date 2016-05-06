package com.hyperion.dashdroid.base.slidingmenu;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hyperion.dashdroid.R;

import java.util.ArrayList;

/**
 * Created by infinity on 05-May-16.
 */
public class SlidingMenuListAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<SlidingMenuItem> slidingMenuItems;

	public SlidingMenuListAdapter(Context context, ArrayList<SlidingMenuItem> slidingMenuItems) {
		this.context = context;
		this.slidingMenuItems = slidingMenuItems;
	}

	@Override
	public int getCount() {
		return slidingMenuItems.size();
	}

	@Override
	public Object getItem(int position) {
		return slidingMenuItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if(convertView == null) {
			LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(R.layout.drawer_list_item, null);
		}

		TextView txtTitle = (TextView) convertView.findViewById(R.id.list_item_title);
		txtTitle.setText(slidingMenuItems.get(position).getTitle());
		return convertView;
	}

}
