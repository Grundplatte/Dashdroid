package test;

import com.hyperion.dashdroid.radio.data.RadioCategory;
import com.hyperion.dashdroid.radio.data.RadioChannel;
import com.hyperion.dashdroid.radio.data.RadioCountry;
import com.hyperion.dashdroid.radio.dirble.DirbleProvider;

import junit.framework.TestCase;

import java.util.ArrayList;

public class RadioTest extends TestCase {

    public RadioTest() {
    }

    // dirble tests
    public void testCategories() {
        ArrayList<RadioCategory> radioCategories = DirbleProvider.getInstance().getCategories();
        assertFalse(radioCategories.isEmpty());
    }

    public void testCountries() {
        ArrayList<RadioCountry> radioCountries = DirbleProvider.getInstance().getCountries();
        assertFalse(radioCountries.isEmpty());
    }

    public void testPopular() {
        ArrayList<RadioChannel> radioChannels = DirbleProvider.getInstance().getPopularChannels();
        assertFalse(radioChannels.isEmpty());
    }

    public void testSearch() {

        RadioChannel radioChannel = new RadioChannel(20564);
        ArrayList<RadioChannel> radioChannels;
        radioChannels = DirbleProvider.getInstance().search("raute");
        assertTrue(radioChannels.contains(radioChannel));
    }
}
