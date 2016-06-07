package com.hyperion.dashdroid.settings;

/**
 * Created by infinity on 06-Jun-16.
 */
public class SettingsItem {

	private SettingsConfigItemsEnum configEnum;
	private String title;
	private boolean enabled;
	private int displaySize;

	public SettingsConfigItemsEnum getConfigEnum() {
		return configEnum;
	}

	public void setConfigEnum(SettingsConfigItemsEnum configEnum) {
		this.configEnum = configEnum;
	}

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
