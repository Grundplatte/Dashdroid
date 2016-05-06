package com.hyperion.dashdroid.uitest;

import android.test.ActivityInstrumentationTestCase2;

import com.hyperion.dashdroid.DashboardActivity;
import com.hyperion.dashdroid.news.NewsModuleActivity;
import com.hyperion.dashdroid.radio.RadioModuleActivity;
import com.hyperion.dashdroid.tv.TvModuleActivity;
import com.hyperion.dashdroid.weather.WeatherModuleActivity;
import com.robotium.solo.Solo;

/**
 * Created by infinity on 27-Apr-16.
 */
public class DashboardActivityTest extends ActivityInstrumentationTestCase2<DashboardActivity> {

	private Solo solo;

	public DashboardActivityTest() {
		super(DashboardActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testButtons() {

		solo.clickOnButton("News");
		assertTrue(solo.waitForActivity(NewsModuleActivity.class));

		solo.goBack();

		solo.clickOnButton("Tv");
		assertTrue(solo.waitForActivity(TvModuleActivity.class));

		solo.goBack();

		solo.clickOnButton("Radio");
		assertTrue(solo.waitForActivity(RadioModuleActivity.class));

		solo.goBack();

		solo.clickOnButton("Weather");
		assertTrue(solo.waitForActivity(WeatherModuleActivity.class));

	}
}