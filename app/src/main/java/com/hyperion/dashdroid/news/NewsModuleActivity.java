package com.hyperion.dashdroid.news;

import android.view.Menu;
import android.view.MenuItem;

import com.hyperion.dashdroid.R;
import com.hyperion.dashdroid.base.AbstractModuleActivity;
import com.hyperion.dashdroid.base.BaseFragment;
import com.hyperion.dashdroid.base.BaseSearchView;
import com.hyperion.dashdroid.base.FragmentTagEnum;
import com.hyperion.dashdroid.base.slidingmenu.SlidingMenuItem;
import com.hyperion.dashdroid.news.rss.RSSFragment;
import com.hyperion.dashdroid.news.rss.RssFeedUrlEnum;

/**
 * Created by infinity on 29-Apr-16.
 */
public class NewsModuleActivity extends AbstractModuleActivity {

	private BaseSearchView searchView;

	@Override
	public void addSpecificContent() {

		getSupportActionBar().setTitle(R.string.dashboard_news);

		RSSFragment homeFragment = new RSSFragment();
		homeFragment.setRssFeedUrl(RssFeedUrlEnum.NEWS_HOME);

		slidingMenuItems.add(new SlidingMenuItem("News", SlidingMenuItem.ItemType.CATEGORY, null, null));
		slidingMenuItems.add(new SlidingMenuItem("Home", SlidingMenuItem.ItemType.ITEM, homeFragment, FragmentTagEnum.RSS_NEWS_HOME.getTag()));

		RSSFragment sportsInternational = new RSSFragment();
		RSSFragment sportsFootball = new RSSFragment();
		RSSFragment sportsBasketball = new RSSFragment();
		RSSFragment sportsTennis = new RSSFragment();
		RSSFragment sportsGolf = new RSSFragment();
		sportsInternational.setRssFeedUrl(RssFeedUrlEnum.SPORTS_INTERNATIONAL);
		sportsFootball.setRssFeedUrl(RssFeedUrlEnum.SPORTS_FOOTBALL);
		sportsBasketball.setRssFeedUrl(RssFeedUrlEnum.SPORTS_BASKETBALL);
		sportsTennis.setRssFeedUrl(RssFeedUrlEnum.SPORTS_TENNIS);
		sportsGolf.setRssFeedUrl(RssFeedUrlEnum.SPORTS_GOLF);

		slidingMenuItems.add(new SlidingMenuItem("Sports", SlidingMenuItem.ItemType.CATEGORY, null, null));
		slidingMenuItems.add(new SlidingMenuItem("International", SlidingMenuItem.ItemType.ITEM, sportsInternational, FragmentTagEnum.RSS_SPORTS_INTERNATIONAL.getTag()));
		slidingMenuItems.add(new SlidingMenuItem("Football", SlidingMenuItem.ItemType.ITEM, sportsFootball, FragmentTagEnum.RSS_SPORTS_FOOTBALL.getTag()));
		slidingMenuItems.add(new SlidingMenuItem("Basketball", SlidingMenuItem.ItemType.ITEM, sportsBasketball, FragmentTagEnum.RSS_SPORTS_BASKETBALL.getTag()));
		slidingMenuItems.add(new SlidingMenuItem("Tennis", SlidingMenuItem.ItemType.ITEM, sportsTennis, FragmentTagEnum.RSS_SPORTS_TENNIS.getTag()));
		slidingMenuItems.add(new SlidingMenuItem("Golf", SlidingMenuItem.ItemType.ITEM, sportsGolf, FragmentTagEnum.RSS_SPORTS_GOLF.getTag()));

		searchView = new BaseSearchView(this);
	}

	@Override
	public void addOptionMenuItems(Menu menu) {

		menu.add(0, MENU_REFRESH_BUTTON_ID, MENU_REFRESH_BUTTON_ID, "").setIcon(R.drawable.refresh).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		//menu.add(0, MENU_SEARCH_BUTTON_ID, MENU_SEARCH_BUTTON_ID, "").setIcon(R.drawable.search).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		menu.add(Menu.NONE, MENU_SEARCH_BUTTON_ID, Menu.NONE, "").setActionView(searchView).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		//menu.add(0, MENU_SETTINGS_BUTTON_ID, MENU_SETTINGS_BUTTON_ID, "").setIcon(R.drawable.settings).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

	}

	@Override
	public void refresh() {

		if(currentSelectedItem.getFragment() != null && currentSelectedItem.getFragment() instanceof BaseFragment) {

			((BaseFragment)currentSelectedItem.getFragment()).refresh();

		}
	}
}
