package com.hyperion.dashdroid.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hyperion.dashdroid.R;
import com.hyperion.dashdroid.settings.SettingsConfigItemsEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public final class MainMenuFragment extends Fragment {

	private static MainMenuFragment instance = null;
	private List<SettingsConfigItemsEnum> moduleItems;
	private View gridView;
	private SharedPreferences configData;

	public static synchronized MainMenuFragment getInstance() {
		if(instance == null) {
			instance = new MainMenuFragment();
		}
		return instance;
	}

	@Override
	public void onResume() {
		super.onResume();

		readConfigData();

		GridLayout gridLayout = (GridLayout) gridView.findViewById(R.id.gridLayout);
		gridLayout.removeAllViews();
		gridLayout.setColumnCount(2);
		gridLayout.setRowCount(moduleItems.size());

		Point displaySize = new Point();
		Display display = getActivity().getWindowManager().getDefaultDisplay();
		display.getSize(displaySize);

		LayoutInflater layoutInflater = getActivity().getLayoutInflater();

		for(int i = 0; i < moduleItems.size(); i++) {
			SettingsConfigItemsEnum item = moduleItems.get(i);

			View view = layoutInflater.inflate(R.layout.main_fragment_card, null);
			RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.tileLayout);
			relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(0, 0));
			relativeLayout.getLayoutParams().width = configData.getInt(item.getSharedPrefDisplaySize(), 1) == 0 ? displaySize.x / 2 : displaySize.x;
			relativeLayout.getLayoutParams().height = displaySize.x / 2;

			TextView textView = (TextView) view.findViewById(R.id.person_name);
			textView.setText(item.getTitle());

			// FIXME: Text size should be appropriate to text length and tile width/height
			textView.setTextSize(relativeLayout.getLayoutParams().width / 25);
			textView.setTextColor(0xFF000000);

			ImageView imageView = (ImageView) view.findViewById(R.id.person_photo);
			imageView.setImageResource(item.getIcon());

			// encode tile id into view tag
			view.setTag(i);

			CardView card = (CardView) view.findViewById(R.id.cv);
			card.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					onClickTile(v);
				}
			});

			if(configData.getInt(item.getSharedPrefDisplaySize(), 1) == 0) {
				gridLayout.addView(view);
			} else if(configData.getInt(item.getSharedPrefDisplaySize(), 1) == 1) {
				gridLayout.addView(view, new GridLayout.LayoutParams(GridLayout.spec(GridLayout.UNDEFINED, 1), GridLayout.spec(GridLayout.UNDEFINED, 2)));
			}

		}
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		gridView = inflater.inflate(R.layout.main_fragment, container, false);

		return gridView;
	}

	private void onClickTile(View v) {
		int tag = (int) ((View) v.getParent()).getTag();

		Intent intent = new Intent(getContext(), moduleItems.get(tag).getActivityClass());
		startActivity(intent);
	}

	private void readConfigData() {

		moduleItems = new ArrayList<SettingsConfigItemsEnum>();
		moduleItems.addAll(Arrays.asList(SettingsConfigItemsEnum.values()));

		configData = getActivity().getSharedPreferences(getString(R.string.settings_config_file), Context.MODE_PRIVATE);

		Iterator<SettingsConfigItemsEnum> iterator = moduleItems.iterator();
		while(iterator.hasNext()) {

			SettingsConfigItemsEnum item = iterator.next();

			if(!configData.getBoolean(item.getSharedPrefEnabled(), true)) {
				iterator.remove();
			}
		}

	}
}