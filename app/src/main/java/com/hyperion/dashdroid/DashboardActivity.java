package com.hyperion.dashdroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.hyperion.dashdroid.home.MainMenuFragment;
import com.hyperion.dashdroid.settings.SettingsActivity;

public class DashboardActivity extends AppCompatActivity {

	private final int MENU_SETTINGS_BUTTON_ID = 101;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard_activity);

		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

		fragmentTransaction.replace(R.id.mainFrameLayout, MainMenuFragment.getInstance()).commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		menu.add(0, MENU_SETTINGS_BUTTON_ID, MENU_SETTINGS_BUTTON_ID, "").setIcon(R.drawable.settings).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch(item.getItemId()) {

			case MENU_SETTINGS_BUTTON_ID:
				showSettings();
				break;

		}

		return super.onOptionsItemSelected(item);
	}

	private void showSettings() {

		try {
			Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
			startActivity(intent);
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

	public int getMENU_SETTINGS_BUTTON_ID() {
		return MENU_SETTINGS_BUTTON_ID;
	}
}
