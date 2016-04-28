package com.hyperion.dashdroid;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

/**
 * Created by Rainer on 28.04.2016.
 */
public class UserInterfaceTests extends ActivityInstrumentationTestCase2<MainActivity>{
    private Solo mySolo;

    public UserInterfaceTests() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        mySolo = new Solo(getInstrumentation(), getActivity());

    }

    public void tearDown() throws Exception {
    }

    public void testMainMenuButtons()
    {
        mySolo.clickInList(0,2);
        mySolo.waitForActivity(RadioActivity.class);
    }

}
