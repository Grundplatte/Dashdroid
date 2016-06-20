package com.hyperion.dashdroid.uitest;

import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListView;

import com.hyperion.dashdroid.R;
import com.hyperion.dashdroid.news.DetailNewsActivity;
import com.hyperion.dashdroid.news.NewsModuleActivity;
import com.robotium.solo.Solo;

import junit.framework.TestCase;

/**
 * Created by infinity on 09-Jun-16.
 */
public class NewsModuleActivityTest extends ActivityInstrumentationTestCase2<NewsModuleActivity> {

	private Solo solo;

	public NewsModuleActivityTest() {
		super(NewsModuleActivity.class);
	}

	public void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}

	public void tearDown() throws Exception {
		super.tearDown();
	}

	public void testNewsDetails() {
		solo.clickOnImageButton(0);
		solo.sleep(200);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.sleep(200);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
		solo.sleep(200);

		ListView myList = (ListView)solo.getView(R.id.modulesListView);
		View listElement = myList.getChildAt(0);
		solo.clickOnView(listElement);
		solo.waitForActivity(DetailNewsActivity.class, 200);
		solo.assertCurrentActivity("Expected activity is:", DetailNewsActivity.class);
	}
}