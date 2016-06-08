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
	public int getItemViewType(int position) {

		if(slidingMenuItems.get(position).getType() == SlidingMenuItem.ItemType.CATEGORY) {
			return 0;
		} else {
			return 1;
		}

	}

	@Override
	public int getViewTypeCount() {
		return SlidingMenuItem.ItemType.values().length;
	}

	@Override
	public boolean isEnabled(int position) {

		if(slidingMenuItems.get(position).getType() == SlidingMenuItem.ItemType.CATEGORY) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		int viewType = this.getItemViewType(position);

		switch(viewType) {

			case 0:

				View view = convertView;
				CategoryViewHolder categoryViewHolder;

				if(view == null) {

					LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

					view = mInflater.inflate(R.layout.drawer_category_item, null);

					categoryViewHolder = new CategoryViewHolder();
					categoryViewHolder.textView = (TextView) view.findViewById(R.id.list_category_title);
					view.setTag(categoryViewHolder);

				} else {

					categoryViewHolder = (CategoryViewHolder) view.getTag();

				}

				categoryViewHolder.textView.setText(slidingMenuItems.get(position).getTitle());

				return view;

			case 1:

				View view2 = convertView;
				ItemViewHolder itemViewHolder;

				if(view2 == null) {

					LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

					view2 = mInflater.inflate(R.layout.news_fragment_detail, null);

					itemViewHolder = new ItemViewHolder();
					itemViewHolder.textView = (TextView) view2.findViewById(R.id.list_item_title);
					view2.setTag(itemViewHolder);

				} else {

					itemViewHolder = (ItemViewHolder) view2.getTag();

				}

				itemViewHolder.textView.setText(slidingMenuItems.get(position).getTitle());

				return view2;

		}

		return convertView;
	}

	private class CategoryViewHolder {
		TextView textView;
	}

	private class ItemViewHolder {
		TextView textView;
	}

}
