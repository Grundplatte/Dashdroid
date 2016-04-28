package com.hyperion.dashdroid;

import android.test.ActivityInstrumentationTestCase2;
import android.test.AndroidTestCase;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ListView;

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

    public void testMainMenuButtons() throws Exception
    {
        GridLayout gridLayout = (GridLayout)mySolo.getView(R.id.gridLayout);
        int elements = gridLayout.getChildCount();

        for(int i = 0; i < elements; i++){
            mySolo.clickOnView(gridLayout.getChildAt(i));
            mySolo.sleep(1000);
            mySolo.goBack();
        }
    }
}
