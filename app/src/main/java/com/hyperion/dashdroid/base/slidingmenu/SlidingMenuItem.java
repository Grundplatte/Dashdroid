package com.hyperion.dashdroid.base.slidingmenu;

import android.app.Fragment;

/**
 * Created by infinity on 05-May-16.
 */
public class SlidingMenuItem {

	public enum ItemType {
		CATEGORY, ITEM
	}

	private String title;
	private ItemType type;
	private Fragment fragment;
	private String fragmentTag;

	public SlidingMenuItem(String title, ItemType type, Fragment fragment, String fragmentTag) {
		this.title = title;
		this.type = type;
		this.fragment = fragment;
		this.fragmentTag = fragmentTag;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ItemType getType() {
		return type;
	}

	public void setType(ItemType type) {
		this.type = type;
	}

	public Fragment getFragment() {
		return fragment;
	}

	public String getFragmentTag() {
		return fragmentTag;
	}
}
