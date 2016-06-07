package com.hyperion.dashdroid.settings;

/**
 * Created by infinity on 06-Jun-16.
 */
public enum SettingsConfigItemsEnum {

	NEWS_MODULE_CONFIG("News", "NEWS_MODULE_ENABLED", "NEWS_MODULE_DISPLAY_SIZE"),
	RADIO_MODULE_CONFIG("Radio", "RADIO_MODULE_ENABLED", "RADIO_MODULE_DISPLAY_SIZE"),
	BOOKS_MODULE_CONFIG("Books", "BOOKS_MODULE_ENABLED", "BOOKS_MODULE_DISPLAY_SIZE");

	private String title;
	private String sharedPrefEnabled;
	private String sharedPrefDisplaySize;

	SettingsConfigItemsEnum(String title, String sharedPrefEnabled, String sharedPrefDisplaySize) {
		this.title = title;
		this.sharedPrefEnabled = sharedPrefEnabled;
		this.sharedPrefDisplaySize = sharedPrefDisplaySize;
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
