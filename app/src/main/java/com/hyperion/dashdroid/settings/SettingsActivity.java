package com.hyperion.dashdroid.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.hyperion.dashdroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by infinity on 06-Jun-16.
 */
public class SettingsActivity extends AppCompatActivity {

	private SharedPreferences configData;
	private ListView listView;
	private SettingsAdapter listAdapter;
	private SettingsConfigItemsEnum[] settingsConfigItems;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		setTitle(getResources().getString(R.string.action_settings));

		settingsConfigItems = SettingsConfigItemsEnum.values();

		readConfigData();
	}

	private void readConfigData() {

		configData = getSharedPreferences(getString(R.string.settings_config_file), Context.MODE_PRIVATE);

		List<SettingsItem> listItems = new ArrayList<>();

		for(int i = 0; i < settingsConfigItems.length; i++) {

			SettingsItem item = new SettingsItem();
			item.setConfigEnum(settingsConfigItems[i]);
			item.setTitle(settingsConfigItems[i].getTitle());
			item.setEnabled(configData.getBoolean(settingsConfigItems[i].getSharedPrefEnabled(), true));
			item.setDisplaySize(configData.getInt(settingsConfigItems[i].getSharedPrefDisplaySize(), 1));

			listItems.add(item);

		}

		listAdapter = new SettingsAdapter(this, listItems);

		listView = (ListView) findViewById(R.id.modulesListView);
		listView.setAdapter(listAdapter);

	}

	private void writeConfigData() {

		SharedPreferences.Editor editor = configData.edit();

		for(int i = 0; i < listAdapter.getCount(); i++) {

			SettingsItem item = (SettingsItem) listAdapter.getItem(i);

			editor.putBoolean(item.getConfigEnum().getSharedPrefEnabled(), item.isEnabled());
			editor.putInt(item.getConfigEnum().getSharedPrefDisplaySize(), item.getDisplaySize());
		}

		editor.commit();

	}

	@Override
	protected void onPause() {
		super.onPause();

		writeConfigData();
	}
}
