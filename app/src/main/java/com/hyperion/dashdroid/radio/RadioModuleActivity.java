package com.hyperion.dashdroid.radio;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.hyperion.dashdroid.R;
import com.hyperion.dashdroid.base.AbstractModuleActivity;
import com.hyperion.dashdroid.base.BaseFragment;
import com.hyperion.dashdroid.base.BaseSearchView;
import com.hyperion.dashdroid.base.FragmentTagEnum;
import com.hyperion.dashdroid.base.slidingmenu.SlidingMenuItem;
import com.hyperion.dashdroid.radio.async.CategoryAsyncTask;
import com.hyperion.dashdroid.radio.async.ChannelSearchAsyncTask;
import com.hyperion.dashdroid.radio.async.ContinentAsyncTask;
import com.hyperion.dashdroid.radio.async.FavoriteChannelsAsyncTask;
import com.hyperion.dashdroid.radio.async.PopularChannelAsyncTask;

public class RadioModuleActivity extends AbstractModuleActivity {

    private static RadioModuleActivity instance;

    public static RadioModuleActivity getInstance() {
        return instance;
    }

    public final int ID_MENU_SEARCH = 1;
    public final int ID_MENU_SETTINGS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
    }

    public BaseSearchView getSearchView() {
        return searchView;
    }

    private BaseSearchView searchView;

    @Override
    public void addSpecificContent() {

        getSupportActionBar().setTitle(R.string.dashboard_radio);
        searchView = new RadioSearchView(this, ChannelSearchAsyncTask.class);

        Fragment favoriteFragment = RadioListFragment.makeInstance(FavoriteChannelsAsyncTask.class);
        Fragment categoryFragment = RadioListFragment.makeInstance(CategoryAsyncTask.class);
        Fragment popularChannelFragment = RadioListFragment.makeInstance(PopularChannelAsyncTask.class);
        Fragment countryFragment = RadioListFragment.makeInstance(ContinentAsyncTask.class);

        slidingMenuItems.add(new SlidingMenuItem("Favorites", SlidingMenuItem.ItemType.ITEM, favoriteFragment, FragmentTagEnum.RADIO_HOME.getTag()));
        slidingMenuItems.add(new SlidingMenuItem("Genres", SlidingMenuItem.ItemType.ITEM, categoryFragment, FragmentTagEnum.RADIO_CATEGORIES.getTag()));
        slidingMenuItems.add(new SlidingMenuItem("Popular", SlidingMenuItem.ItemType.ITEM, popularChannelFragment, FragmentTagEnum.RADIO_POPULAR.getTag()));
        slidingMenuItems.add(new SlidingMenuItem("Countries", SlidingMenuItem.ItemType.ITEM, countryFragment, FragmentTagEnum.RADIO_COUNTRIES.getTag()));
    }

    @Override
    public void addOptionMenuItems(Menu menu) {
        menu.add(Menu.NONE, ID_MENU_SEARCH, Menu.NONE, "").setActionView(searchView).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }


    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0)
            getFragmentManager().popBackStack();
        else
            super.onBackPressed();
    }

    @Override
    protected void refresh() {
        if (currentSelectedItem.getFragment() != null && currentSelectedItem.getFragment() instanceof BaseFragment) {

            ((BaseFragment) currentSelectedItem.getFragment()).refresh();

        }
    }

    /**
     * Overrides the standard fragment switching, s.t. the inner fragment is switched
     *
     * @param position
     */
    @Override
    protected void displayView(int position) {

        if (getFragmentManager().getBackStackEntryCount() > 0)
            getFragmentManager().popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);


        if (slidingMenuItems != null && slidingMenuItems.size() > position &&
                slidingMenuItems.get(position).getType() == SlidingMenuItem.ItemType.ITEM && slidingMenuItems.get(position).getFragment() != null) {

            FragmentManager fragmentManager = getFragmentManager();

            // check if parent fragment exists
            Fragment mainFragment = fragmentManager.findFragmentByTag(RadioMainFragment.TAG);
            if (mainFragment == null) {
                fragmentManager.beginTransaction().replace(R.id.frame_container, new RadioMainFragment(), RadioMainFragment.TAG).commit();
            }

            Fragment oldFragment = fragmentManager.findFragmentByTag(slidingMenuItems.get(position).getFragmentTag());

            if (oldFragment != null && oldFragment.isVisible()) {

                refresh();
                drawerLayout.closeDrawer(drawerList);

            } else {

                fragmentManager.beginTransaction().replace(R.id.radioList_container, slidingMenuItems.get(position).getFragment(), slidingMenuItems.get(position).getFragmentTag()).commit();
                drawerList.setItemChecked(position, true);
                drawerList.setSelection(position);
                getSupportActionBar().setSubtitle(slidingMenuItems.get(position).getTitle());
                drawerLayout.closeDrawer(drawerList);

                currentSelectedItem = slidingMenuItems.get(position);
            }

        } else {
            Log.e(getClass().getSimpleName(), "Error in creating fragment");
        }
    }

    @Override
    protected void onStop() {
        RadioMainFragment radioMainFragment = (RadioMainFragment) getFragmentManager().findFragmentByTag(RadioMainFragment.TAG);
        radioMainFragment.getRadioPlayer().stopRadio();
        super.onStop();
    }
}
