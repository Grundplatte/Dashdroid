package com.hyperion.dashdroid.uitest;

import android.test.ActivityInstrumentationTestCase2;

import com.hyperion.dashdroid.DashboardActivity;
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
		solo.clickOnButton("Tv");
		solo.clickOnButton("Radio");
		solo.clickOnButton("Weather");

	}
}