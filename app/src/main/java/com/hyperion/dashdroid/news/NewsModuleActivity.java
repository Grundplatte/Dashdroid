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

		RSSFragment newsHome = new RSSFragment();
		RSSFragment newsWorld = new RSSFragment();
		RSSFragment newsPolitics = new RSSFragment();
		newsHome.setRssFeedUrl(RssFeedUrlEnum.NEWS_HOME);
		newsWorld.setRssFeedUrl(RssFeedUrlEnum.NEWS_WORLD);
		newsPolitics.setRssFeedUrl(RssFeedUrlEnum.NEWS_POLITICS);

		slidingMenuItems.add(new SlidingMenuItem("News", SlidingMenuItem.ItemType.CATEGORY, null, null));
		slidingMenuItems.add(new SlidingMenuItem("Home", SlidingMenuItem.ItemType.ITEM, newsHome, FragmentTagEnum.RSS_NEWS_HOME.getTag()));
		slidingMenuItems.add(new SlidingMenuItem("World", SlidingMenuItem.ItemType.ITEM, newsWorld, FragmentTagEnum.RSS_NEWS_WORLD.getTag()));
		slidingMenuItems.add(new SlidingMenuItem("Politics", SlidingMenuItem.ItemType.ITEM, newsPolitics, FragmentTagEnum.RSS_NEWS_POLITICS.getTag()));

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

		RSSFragment cultureArts = new RSSFragment();
		RSSFragment cultureBooks = new RSSFragment();
		RSSFragment cultureDance = new RSSFragment();
		RSSFragment cultureMovies = new RSSFragment();
		RSSFragment cultureMusic = new RSSFragment();
		RSSFragment cultureTv = new RSSFragment();
		RSSFragment cultureTheater = new RSSFragment();
		cultureArts.setRssFeedUrl(RssFeedUrlEnum.CULTURE_ARTS);
		cultureBooks.setRssFeedUrl(RssFeedUrlEnum.CULTURE_BOOKS);
		cultureDance.setRssFeedUrl(RssFeedUrlEnum.CULTURE_DANCE);
		cultureMovies.setRssFeedUrl(RssFeedUrlEnum.CULTURE_MOVIES);
		cultureMusic.setRssFeedUrl(RssFeedUrlEnum.CULTURE_MUSIC);
		cultureTv.setRssFeedUrl(RssFeedUrlEnum.CULTURE_TELEVISION);
		cultureTheater.setRssFeedUrl(RssFeedUrlEnum.CULTURE_THEATER);

		slidingMenuItems.add(new SlidingMenuItem("Culture & Lifestyle", SlidingMenuItem.ItemType.CATEGORY, null, null));
		slidingMenuItems.add(new SlidingMenuItem("Arts", SlidingMenuItem.ItemType.ITEM, cultureArts, FragmentTagEnum.RSS_CULTURE_ARTS.getTag()));
		slidingMenuItems.add(new SlidingMenuItem("Books", SlidingMenuItem.ItemType.ITEM, cultureBooks, FragmentTagEnum.RSS_CULTURE_BOOKS.getTag()));
		slidingMenuItems.add(new SlidingMenuItem("Dance", SlidingMenuItem.ItemType.ITEM, cultureDance, FragmentTagEnum.RSS_CULTURE_DANCE.getTag()));
		slidingMenuItems.add(new SlidingMenuItem("Movies", SlidingMenuItem.ItemType.ITEM, cultureMovies, FragmentTagEnum.RSS_CULTURE_MOVIES.getTag()));
		slidingMenuItems.add(new SlidingMenuItem("Music", SlidingMenuItem.ItemType.ITEM, cultureMusic, FragmentTagEnum.RSS_CULTURE_MUSIC.getTag()));
		slidingMenuItems.add(new SlidingMenuItem("Television", SlidingMenuItem.ItemType.ITEM, cultureTv, FragmentTagEnum.RSS_CULTURE_TV.getTag()));
		slidingMenuItems.add(new SlidingMenuItem("Theater", SlidingMenuItem.ItemType.ITEM, cultureTheater, FragmentTagEnum.RSS_CULTURE_THEATER.getTag()));

		RSSFragment otherAuto = new RSSFragment();
		RSSFragment otherCommercial = new RSSFragment();
		RSSFragment otherJobs = new RSSFragment();
		RSSFragment otherRealEstate = new RSSFragment();
		otherAuto.setRssFeedUrl(RssFeedUrlEnum.OTHER_AUTOS);
		otherCommercial.setRssFeedUrl(RssFeedUrlEnum.OTHER_COMMERCIAL);
		otherJobs.setRssFeedUrl(RssFeedUrlEnum.OTHER_JOBS);
		otherRealEstate.setRssFeedUrl(RssFeedUrlEnum.OTHER_REAL_ESTATE);

		slidingMenuItems.add(new SlidingMenuItem("Other", SlidingMenuItem.ItemType.CATEGORY, null, null));
		slidingMenuItems.add(new SlidingMenuItem("Automobile", SlidingMenuItem.ItemType.ITEM, otherAuto, FragmentTagEnum.RSS_OTHER_AUTO.getTag()));
		slidingMenuItems.add(new SlidingMenuItem("Commercial", SlidingMenuItem.ItemType.ITEM, otherCommercial, FragmentTagEnum.RSS_OTHER_COMMERCIAL.getTag()));
		slidingMenuItems.add(new SlidingMenuItem("Jobs", SlidingMenuItem.ItemType.ITEM, otherJobs, FragmentTagEnum.RSS_OTHER_JOBS.getTag()));
		slidingMenuItems.add(new SlidingMenuItem("Real Estate", SlidingMenuItem.ItemType.ITEM, otherRealEstate, FragmentTagEnum.RSS_OTHER_REAL_ESTATE.getTag()));

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
