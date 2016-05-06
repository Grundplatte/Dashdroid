package com.hyperion.dashdroid.base.slidingmenu;

import android.app.Fragment;

/**
 * Created by infinity on 06-May-16.
 */
public abstract class BaseFragment extends Fragment {

	protected String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
