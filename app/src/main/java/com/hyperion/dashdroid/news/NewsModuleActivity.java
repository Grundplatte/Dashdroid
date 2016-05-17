package com.hyperion.dashdroid.news;

import android.app.Fragment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.hyperion.dashdroid.R;
import com.hyperion.dashdroid.base.AbstractModuleActivity;
import com.hyperion.dashdroid.base.slidingmenu.SlidingMenuItem;

/**
 * Created by infinity on 29-Apr-16.
 */
public class NewsModuleActivity extends AbstractModuleActivity {

	@Override
	public void addSpecificContent() {

		getSupportActionBar().setTitle(R.string.dashboard_news);

		Fragment homeFragment = new NewsHomeFragment();

		slidingMenuItems.add(new SlidingMenuItem("News", SlidingMenuItem.ItemType.CATEGORY, null));
		slidingMenuItems.add(new SlidingMenuItem("Home", SlidingMenuItem.ItemType.ITEM, homeFragment));
	}

	@Override
	public void addOtherOptionMenuItems(Menu menu) {

		// TODO - Check if in every module necessary.
		menu.add(0, MENU_SEARCH_BUTTON_ID, MENU_SEARCH_BUTTON_ID, "").setIcon(R.drawable.search).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		menu.add(0, MENU_SETTINGS_BUTTON_ID, MENU_SETTINGS_BUTTON_ID, "").setIcon(R.drawable.settings).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

	}

	@Override
	public void refresh() {

		Log.d(getClass().getSimpleName(), "refresh() method called...");

	}
}
