package com.hyperion.dashdroid.radio;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.hyperion.dashdroid.R;
import com.hyperion.dashdroid.base.AbstractModuleActivity;
import com.hyperion.dashdroid.base.BaseFragment;
import com.hyperion.dashdroid.base.BaseSearchView;
import com.hyperion.dashdroid.base.FragmentTagEnum;
import com.hyperion.dashdroid.base.slidingmenu.SlidingMenuItem;

/**
 * Created by infinity on 05-May-16.
 */
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
        searchView = new BaseSearchView(this, DirbleAsyncTask.class);

		Fragment homeFragment = new RadioCategoryFragment();

		slidingMenuItems.add(new SlidingMenuItem("Genres", SlidingMenuItem.ItemType.ITEM, homeFragment, FragmentTagEnum.RADIO_HOME.getTag()));
	}

	@Override
	public void addOptionMenuItems(Menu menu) {

        menu.add(Menu.NONE, ID_MENU_SEARCH, Menu.NONE, "").setActionView(searchView).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        //menu.add(Menu.NONE, ID_MENU_SETTINGS, Menu.NONE, "").setIcon(R.drawable.settings).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

	@Override
	public void search() {

		Log.d(getClass().getSimpleName(), "refresh() method called...");

	}

	@Override
	public void onBackPressed() {
		RecyclerView recyclerView = (RecyclerView)findViewById(R.id.radioListView);
		if(recyclerView == null) {
			RadioPlayer.getInstance().reset();
			super.onBackPressed();
			return;
		}

		RecyclerView.Adapter adapter = recyclerView.getAdapter();
		if(adapter == null){
			RadioPlayer.getInstance().reset();
			super.onBackPressed();
			return;
		}

		if((adapter instanceof CategoryAdapter && ((CategoryAdapter) adapter).getRootCategory() != -1) || adapter instanceof ChannelAdapter) {
			DirbleAsyncTask dirbleAsyncTask = new DirbleAsyncTask(recyclerView);
			dirbleAsyncTask.setJobType(DirbleAsyncTask.JobType.GET_CATEGORY_TREE);

			ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
			if (progressBar != null)
				dirbleAsyncTask.setProgressBar(progressBar);

			dirbleAsyncTask.execute();

		}
		else
		{
			RadioPlayer.getInstance().reset();
			super.onBackPressed();
		}

	}

	@Override
	protected void refresh() {
		if(currentSelectedItem.getFragment() != null && currentSelectedItem.getFragment() instanceof BaseFragment) {

			((BaseFragment)currentSelectedItem.getFragment()).refresh();

		}
	}

	/**
	 * Overrides the standard fragment switching, s.t. the inner fragment is switched
	 * @param position
     */
	@Override
	protected void displayView(int position) {

		if(slidingMenuItems != null && slidingMenuItems.size() > position &&
				slidingMenuItems.get(position).getType() == SlidingMenuItem.ItemType.ITEM && slidingMenuItems.get(position).getFragment() != null) {

			FragmentManager fragmentManager = getFragmentManager();

			// check if parent fragment exists
			RelativeLayout player = (RelativeLayout)findViewById(R.id.playerLayout);
			if(player == null) {
				fragmentManager.beginTransaction().replace(R.id.frame_container, new RadioMainFragment()).commit();
			}

			Fragment oldFragment = fragmentManager.findFragmentByTag(slidingMenuItems.get(position).getFragmentTag());

			if(oldFragment != null && oldFragment.isVisible()) {

				refresh();
				drawerLayout.closeDrawer(drawerList);

			}else {

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
}
