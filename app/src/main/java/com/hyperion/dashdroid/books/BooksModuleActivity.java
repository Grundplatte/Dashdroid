package com.hyperion.dashdroid.books;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.hyperion.dashdroid.R;
import com.hyperion.dashdroid.base.AbstractModuleActivity;
import com.hyperion.dashdroid.base.slidingmenu.SlidingMenuItem;
import com.hyperion.dashdroid.books.recommendations.BookCategoriesEnum;
import com.hyperion.dashdroid.books.recommendations.BooksFragment;

/**
 * Created by infinity on 05-May-16.
 */
public class BooksModuleActivity extends AbstractModuleActivity {

	@Override
	public void addSpecificContent() {

		getSupportActionBar().setTitle(R.string.dashboard_books);

		BookCategoriesEnum[] categories = BookCategoriesEnum.values();
		for(BookCategoriesEnum category : categories) {
			BooksFragment bookFragment = new BooksFragment();
			bookFragment.setBookCategory(category);

			slidingMenuItems.add(new SlidingMenuItem(bookFragment.getBookCategory().getName(), SlidingMenuItem.ItemType.ITEM, bookFragment, bookFragment.getBookCategory().getUrlPart()));
		}


	}

	@Override
	public void addOptionMenuItems(Menu menu) {

		menu.add(0, 0, 0, "").setIcon(R.drawable.search).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

	}

	@Override
	public void refresh() {

		Log.d(getClass().getSimpleName(), "refresh() method called...");

	}
}
