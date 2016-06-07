package com.hyperion.dashdroid.settings;

import com.hyperion.dashdroid.R;
import com.hyperion.dashdroid.books.BooksModuleActivity;
import com.hyperion.dashdroid.news.NewsModuleActivity;
import com.hyperion.dashdroid.radio.RadioModuleActivity;

/**
 * Created by infinity on 06-Jun-16.
 */
public enum SettingsConfigItemsEnum {

	NEWS_MODULE_CONFIG(NewsModuleActivity.class, "News", R.drawable.ic_today_black_48dp, "NEWS_MODULE_ENABLED", "NEWS_MODULE_DISPLAY_SIZE"),
	RADIO_MODULE_CONFIG(RadioModuleActivity.class, "Radio", R.drawable.ic_radio_black_48dp, "RADIO_MODULE_ENABLED", "RADIO_MODULE_DISPLAY_SIZE"),
	BOOKS_MODULE_CONFIG(BooksModuleActivity.class, "Books", R.drawable.ic_books_black_48dp, "BOOKS_MODULE_ENABLED", "BOOKS_MODULE_DISPLAY_SIZE");

	private String title;
	private int icon;
	private Class activityClass;
	private String sharedPrefEnabled;
	private String sharedPrefDisplaySize;

	SettingsConfigItemsEnum(Class activityClass, String title, int icon, String sharedPrefEnabled, String sharedPrefDisplaySize) {
		this.activityClass = activityClass;
		this.title = title;
		this.icon = icon;
		this.sharedPrefEnabled = sharedPrefEnabled;
		this.sharedPrefDisplaySize = sharedPrefDisplaySize;
	}

	public int getIcon() {
		return icon;
	}

	public Class getActivityClass() {
		return activityClass;
	}

	public String getTitle() {
		return title;
	}

	public String getSharedPrefEnabled() {
		return sharedPrefEnabled;
	}

	public String getSharedPrefDisplaySize() {
		return sharedPrefDisplaySize;
	}
}
