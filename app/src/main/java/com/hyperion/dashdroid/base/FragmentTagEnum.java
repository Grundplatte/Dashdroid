package com.hyperion.dashdroid.base;

/**
 * Created by infinity on 19-May-16.
 */
public enum FragmentTagEnum {

	RSS_NEWS_HOME("RSS_NEWS_HOME_FRAGMENT_TAG"), RSS_NEWS_WORLD("RSS_NEWS_WORLD"), RSS_NEWS_POLITICS("RSS_NEWS_POLITICS"),
	RSS_SPORTS_INTERNATIONAL("RSS_SPORTS_INTERNATIONAL_FRAGMENT_TAG"), RSS_SPORTS_FOOTBALL("RSS_SPORTS_FOOTBALL_FRAGMENT_TAG"), RSS_SPORTS_BASKETBALL("RSS_SPORTS_BASKETBALL_FRAGMENT_TAG"),
	RSS_SPORTS_TENNIS("RSS_SPORTS_TENNIS_FRAGMENT_TAG"), RSS_SPORTS_GOLF("RSS_SPORTS_GOLF_FRAGMENT_TAG"), RSS_CULTURE_ARTS("RSS_CULTURE_ARTS"), RSS_CULTURE_BOOKS("RSS_CULTURE_BOOKS"),
	RSS_CULTURE_DANCE("RSS_CULTURE_DANCE"), RSS_CULTURE_MOVIES("RSS_CULTURE_MOVIES"), RSS_CULTURE_MUSIC("RSS_CULTURE_MUSIC"), RSS_CULTURE_TV("RSS_CULTURE_TV"), RSS_CULTURE_THEATER("RSS_CULTURE_THEATER"),
	RSS_OTHER_AUTO("RSS_OTHER_AUTO"), RSS_OTHER_COMMERCIAL("RSS_OTHER_COMMERCIAL"), RSS_OTHER_JOBS("RSS_OTHER_JOBS"), RSS_OTHER_REAL_ESTATE("RSS_OTHER_REAL_ESTATE"),

	RADIO_HOME("RADIO_HOME_FRAGMENT_TAG"), TV_HOME("TV_HOME_FRAGMENT_TAG"), WEATHER_HOME("WEATHER_HOME_FRAGMENT_TAG");

	private String tag;

	FragmentTagEnum(String tag) {
		this.tag = tag;
	}

	public String getTag() {
		return tag;
	}
}
