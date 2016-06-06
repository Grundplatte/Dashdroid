package com.hyperion.dashdroid.settings;

/**
 * Created by infinity on 06-Jun-16.
 */
public class SettingsItem {

	private String title;
	private boolean enabled;
	private int displaySize;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public int getDisplaySize() {
		return displaySize;
	}

	public void setDisplaySize(int displaySize) {
		this.displaySize = displaySize;
	}
}
