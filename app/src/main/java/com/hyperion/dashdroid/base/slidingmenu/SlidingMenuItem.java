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

	public SlidingMenuItem(String title, ItemType type, Fragment fragment) {
		this.title = title;
		this.type = type;
		this.fragment = fragment;
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
}
