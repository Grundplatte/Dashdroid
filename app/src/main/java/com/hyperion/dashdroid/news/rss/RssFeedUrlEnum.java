package com.hyperion.dashdroid.news.rss;

/**
 * Created by infinity on 19-May-16.
 */
public enum RssFeedUrlEnum {

	NEWS_HOME("http://rss.nytimes.com/services/xml/rss/nyt/InternationalHome.xml"),

	SPORTS_INTERNATIONAL("http://rss.nytimes.com/services/xml/rss/nyt/InternationalSports.xml"),
	SPORTS_FOOTBALL("http://rss.nytimes.com/services/xml/rss/nyt/Soccer.xml"),
	SPORTS_BASKETBALL("http://rss.nytimes.com/services/xml/rss/nyt/ProBasketball.xml"),
	SPORTS_TENNIS("http://rss.nytimes.com/services/xml/rss/nyt/Tennis.xml"),
	SPORTS_GOLF("http://rss.nytimes.com/services/xml/rss/nyt/Golf.xml");

	private String url;

	RssFeedUrlEnum(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}
}
