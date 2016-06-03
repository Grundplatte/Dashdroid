package com.hyperion.dashdroid.books;

import android.app.Fragment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.hyperion.dashdroid.R;
import com.hyperion.dashdroid.base.AbstractModuleActivity;
import com.hyperion.dashdroid.base.FragmentTagEnum;
import com.hyperion.dashdroid.base.slidingmenu.SlidingMenuItem;

/**
 * Created by infinity on 05-May-16.
 */
public class BooksModuleActivity extends AbstractModuleActivity {

	@Override
	public void addSpecificContent() {

		getSupportActionBar().setTitle(R.string.dashboard_books);
		Fragment homeFragment = new BooksHomeFragment();
		slidingMenuItems.add(new SlidingMenuItem("Home", SlidingMenuItem.ItemType.ITEM, homeFragment, FragmentTagEnum.TV_HOME.getTag()));
	}

	@Override
	public void addOptionMenuItems(Menu menu) {

		// TODO - Check if in every module necessary.
		menu.add(0, 0, 0, "").setIcon(R.drawable.search).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		menu.add(0, 1, 1, "").setIcon(R.drawable.settings).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

	}

	@Override
	public void refresh() {

		Log.d(getClass().getSimpleName(), "refresh() method called...");

	}
}
