package com.hyperion.dashdroid.uitest;

import android.content.Context;
import android.graphics.Point;
import android.media.AudioManager;
import android.test.ActivityInstrumentationTestCase2;

import com.hyperion.dashdroid.radio.RadioModuleActivity;
import com.robotium.solo.Solo;


public class RadioModuleActivityTest extends ActivityInstrumentationTestCase2<RadioModuleActivity> {

    private Solo solo;

    public RadioModuleActivityTest() {
        super(RadioModuleActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void openNavigationDrawer() {
        Point deviceSize = new Point();
        solo.getCurrentActivity().getWindowManager().getDefaultDisplay().getSize(deviceSize);

        int screenWidth = deviceSize.x;
        int screenHeight = deviceSize.y;
        int fromX = 0;
        int toX = screenWidth / 2;
        int fromY = screenHeight / 2;
        int toY = fromY;

        solo.drag(fromX, toX, fromY, toY, 1);
    }

    public void testGenreChannelPlayback() {
        AudioManager manager = (AudioManager) solo.getCurrentActivity().getSystemService(Context.AUDIO_SERVICE);

        openNavigationDrawer();
        solo.sleep(1000);
        solo.clickOnText("Genres");
        solo.sleep(1000);
        solo.clickInRecyclerView(0);
        solo.sleep(1000);
        solo.clickInRecyclerView(0);
        solo.sleep(1000);
        solo.clickInRecyclerView(0);
        solo.sleep(10000);

        assertTrue(manager.isMusicActive());
        //solo.clickOnText("Genre");
    }
}