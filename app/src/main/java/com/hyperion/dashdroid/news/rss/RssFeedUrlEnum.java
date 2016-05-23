package com.hyperion.dashdroid.news.rss;

/**
 * Created by infinity on 19-May-16.
 */
public enum RssFeedUrlEnum {

	NEWS_HOME("http://rss.nytimes.com/services/xml/rss/nyt/InternationalHome.xml"),
	NEWS_WORLD("http://rss.nytimes.com/services/xml/rss/nyt/World.xml"),
	NEWS_POLITICS("http://rss.nytimes.com/services/xml/rss/nyt/Politics.xml"),

	SPORTS_INTERNATIONAL("http://rss.nytimes.com/services/xml/rss/nyt/InternationalSports.xml"),
	SPORTS_FOOTBALL("http://rss.nytimes.com/services/xml/rss/nyt/Soccer.xml"),
	SPORTS_BASKETBALL("http://rss.nytimes.com/services/xml/rss/nyt/ProBasketball.xml"),
	SPORTS_TENNIS("http://rss.nytimes.com/services/xml/rss/nyt/Tennis.xml"),
	SPORTS_GOLF("http://rss.nytimes.com/services/xml/rss/nyt/Golf.xml"),

	CULTURE_ARTS("http://www.nytimes.com/services/xml/rss/nyt/InternationalArts.xml"),
	CULTURE_BOOKS("http://rss.nytimes.com/services/xml/rss/nyt/Books.xml"),
	CULTURE_DANCE("http://rss.nytimes.com/services/xml/rss/nyt/Dance.xml"),
	CULTURE_MOVIES("http://rss.nytimes.com/services/xml/rss/nyt/Movies.xml"),
	CULTURE_MUSIC("http://rss.nytimes.com/services/xml/rss/nyt/Music.xml"),
	CULTURE_TELEVISION("http://rss.nytimes.com/services/xml/rss/nyt/Television.xml"),
	CULTURE_THEATER("http://rss.nytimes.com/services/xml/rss/nyt/Television.xml"),

	OTHER_AUTOS("http://rss.nytimes.com/services/xml/rss/nyt/Automobiles.xml"),
	OTHER_COMMERCIAL("http://rss.nytimes.com/services/xml/rss/nyt/Commercial.xml"),
	OTHER_JOBS("http://www.nytimes.com/services/xml/rss/nyt/JobMarket.xml"),
	OTHER_REAL_ESTATE("http://rss.nytimes.com/services/xml/rss/nyt/RealEstate.xml");

	private String url;

	RssFeedUrlEnum(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}
}
