package com.hyperion.dashdroid.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.hyperion.dashdroid.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by infinity on 06-Jun-16.
 */
public class SettingsActivity extends AppCompatActivity {

	private SharedPreferences configData;
	private List<String> modules;
	private ListView listView;
	private SettingsAdapter listAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);

		setTitle(getResources().getString(R.string.action_settings));

		readConfigData();

		for(String s : modules) {
			Log.d(getClass().getSimpleName(), "s = " + s);
		}
	}

	private void readConfigData() {

		configData = getSharedPreferences(getString(R.string.settings_config_file), Context.MODE_PRIVATE);

		String[] availableModules = getResources().getStringArray(R.array.settings_config_available_modules);

		List<SettingsItem> listItems = new ArrayList<>();
		for(String moduleStr : availableModules) {

			SettingsItem item = new SettingsItem();
			item.setTitle(moduleStr);
			item.setEnabled(false);
			item.setDisplaySize(1);

			listItems.add(item);
		}

		listAdapter = new SettingsAdapter(this, listItems);

		listView = (ListView) findViewById(R.id.modulesListView);
		listView.setAdapter(listAdapter);


		modules = new ArrayList<>();
		modules.addAll(Arrays.asList(availableModules));

	}

}
