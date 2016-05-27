package com.hyperion.dashdroid.uitest;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.TextView;

import com.hyperion.dashdroid.DashboardActivity;
import com.hyperion.dashdroid.news.NewsModuleActivity;
import com.hyperion.dashdroid.radio.DirbleProvider;
import com.hyperion.dashdroid.radio.RadioChannel;
import com.hyperion.dashdroid.radio.RadioModuleActivity;
import com.hyperion.dashdroid.tv.TvModuleActivity;
import com.hyperion.dashdroid.weather.WeatherModuleActivity;
import com.robotium.solo.Solo;

import java.util.ArrayList;

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

		for(View v : solo.getCurrentViews()) {

			if(v instanceof TextView) {


				if(((TextView) v).getText().equals("News")) {

					solo.clickOnView(v);
					assertTrue(solo.waitForActivity(NewsModuleActivity.class));
					solo.goBack();

				} else if(((TextView) v).getText().equals("Radio")) {

					solo.clickOnView(v);
					assertTrue(solo.waitForActivity(RadioModuleActivity.class));
					solo.goBack();

				} else if(((TextView) v).getText().equals("Tv")) {

					solo.clickOnView(v);
					assertTrue(solo.waitForActivity(TvModuleActivity.class));
					solo.goBack();

				} else if(((TextView) v).getText().equals("Weather")) {

					solo.clickOnView(v);
					assertTrue(solo.waitForActivity(WeatherModuleActivity.class));
					solo.goBack();

				}

			}

		}

	}

	public void testJSONParsing(){

		RadioChannel radioChannel = new RadioChannel(20564);
		ArrayList<RadioChannel> radioChannels;
		radioChannels = DirbleProvider.getInstance().search("raute");
		assertTrue(radioChannels.contains(radioChannel));

	}
}