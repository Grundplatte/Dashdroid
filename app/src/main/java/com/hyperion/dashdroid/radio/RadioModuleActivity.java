package com.hyperion.dashdroid.radio;

import android.app.Fragment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.hyperion.dashdroid.R;
import com.hyperion.dashdroid.base.AbstractModuleActivity;
import com.hyperion.dashdroid.base.slidingmenu.SlidingMenuItem;

/**
 * Created by infinity on 05-May-16.
 */
public class RadioModuleActivity extends AbstractModuleActivity {

    public final int ID_MENU_SEARCH = 1;
    public final int ID_MENU_SETTINGS = 2;

    public RadioWhiteSearchView getSearchView() {
        return searchView;
    }

    private RadioWhiteSearchView searchView;

	@Override
	public void addSpecificContent() {

		getSupportActionBar().setTitle(R.string.dashboard_radio);
        searchView = new RadioWhiteSearchView(this);

		Fragment homeFragment = new RadioHomeFragment();

		slidingMenuItems.add(new SlidingMenuItem("Home", SlidingMenuItem.ItemType.ITEM, homeFragment));
	}

	@Override
	public void addOtherOptionMenuItems(Menu menu) {

        // TODO - Check if in every module necessary.
        menu.add(Menu.NONE, ID_MENU_SEARCH, Menu.NONE, "").setActionView(searchView).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        menu.add(Menu.NONE, ID_MENU_SETTINGS, Menu.NONE, "").setIcon(R.drawable.settings).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

	@Override
	public void refresh() {

		Log.d(getClass().getSimpleName(), "refresh() method called...");

	}

	@Override
	public void search() {

		Log.d(getClass().getSimpleName(), "refresh() method called...");

	}
}
