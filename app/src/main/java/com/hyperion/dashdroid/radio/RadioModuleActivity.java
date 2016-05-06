package com.hyperion.dashdroid.radio;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.hyperion.dashdroid.R;
import com.hyperion.dashdroid.base.AbstractModuleActivity;
import com.hyperion.dashdroid.base.slidingmenu.BaseFragment;

/**
 * Created by infinity on 05-May-16.
 */
public class RadioModuleActivity extends AbstractModuleActivity {

	@Override
	public void addSpecificContent() {

		getSupportActionBar().setTitle(R.string.dashboard_radio);

		BaseFragment homeFragment = new RadioHomeFragment();
		homeFragment.setTitle(getResources().getString(R.string.radio_sliding_menu_item_1));

		fragments.add(homeFragment);
	}

	@Override
	public void addOtherOptionMenuItems(Menu menu) {

		// TODO - Check if in every module necessary.
		menu.add(0, 0, 0, "").setIcon(R.drawable.search).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		menu.add(0, 1, 1, "").setIcon(R.drawable.settings).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

	}

	@Override
	public void refresh() {

		Log.d(getClass().getSimpleName(), "refresh() method called...");

	}
}
