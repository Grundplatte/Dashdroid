package com.hyperion.dashdroid.books;

import android.view.Menu;
import android.view.MenuItem;

import com.hyperion.dashdroid.R;
import com.hyperion.dashdroid.base.AbstractModuleActivity;
import com.hyperion.dashdroid.base.BaseSearchView;
import com.hyperion.dashdroid.base.slidingmenu.SlidingMenuItem;
import com.hyperion.dashdroid.books.recommendations.BookCategoriesEnum;
import com.hyperion.dashdroid.books.recommendations.BooksFragment;

/**
 * Created by infinity on 05-May-16.
 */
public class BooksModuleActivity extends AbstractModuleActivity {

	private BaseSearchView searchView;

	@Override
	public void addSpecificContent() {

		getSupportActionBar().setTitle(R.string.dashboard_books);

		BookCategoriesEnum[] categories = BookCategoriesEnum.values();
		for(BookCategoriesEnum category : categories) {
			BooksFragment bookFragment = new BooksFragment();
			bookFragment.setBookCategory(category);

			slidingMenuItems.add(new SlidingMenuItem(bookFragment.getBookCategory().getName(), SlidingMenuItem.ItemType.ITEM, bookFragment, bookFragment.getBookCategory().getUrlPart()));
			searchView = new BaseSearchView(this, BooksSearchTask.class);
		}
	}

	@Override
	public void addOptionMenuItems(Menu menu) {
		menu.add(Menu.NONE, MENU_SEARCH_BUTTON_ID, Menu.NONE, "").setActionView(searchView).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
	}

	@Override
	protected void refresh() {
		super.refresh();
	}

	public static SlidingMenuItem getCurrentSelectedItem() {
		return currentSelectedItem;
	}
}
