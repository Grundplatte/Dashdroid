package com.hyperion.dashdroid.settings;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.hyperion.dashdroid.R;

import java.util.List;

/**
 * Created by infinity on 06-Jun-16.
 */
class SettingsAdapter extends BaseAdapter {

	private List<SettingsItem> items;
	private LayoutInflater layoutInflater;

	public SettingsAdapter(Activity activity, List<SettingsItem> items) {
		this.items = items;
		layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int i) {
		return items.get(i);
	}

	@Override
	public long getItemId(int i) {
		return i;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final int pos = position;

		View listItem = convertView;
		if(listItem == null) {
			listItem = layoutInflater.inflate(R.layout.settings_fragment_list_item, null);
		}

		TextView txtTitle = (TextView) listItem.findViewById(R.id.title);
		Switch enabledSwitch = (Switch) listItem.findViewById(R.id.enabledSwitch);
		Switch sizeSwitch = (Switch) listItem.findViewById(R.id.sizeSwitch);

		txtTitle.setText(items.get(pos).getTitle());
		enabledSwitch.setChecked(items.get(pos).isEnabled());
		sizeSwitch.setChecked(items.get(pos).getDisplaySize() == 0 ? false : true);

		enabledSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
				items.get(pos).setEnabled(isChecked);
			}
		});

		sizeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
				items.get(pos).setDisplaySize(isChecked ? 1 : 0);
			}
		});

		return listItem;

	}
}
