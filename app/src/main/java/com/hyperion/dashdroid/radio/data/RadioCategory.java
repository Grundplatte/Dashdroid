package com.hyperion.dashdroid.radio.data;

public class RadioCategory {

	private int ID;
	private String title;
	private String description;
	private String slug;
	private int ancestry;

	public RadioCategory(int ID, String title, String description, String slug, int ancestry) {
		this.ID = ID;
		this.title = title;
		this.description = description;
		this.slug = slug;
		this.ancestry = ancestry;
	}

	public int getID() {
		return ID;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getSlug() {
		return slug;
	}

	public int getAncestry() {
		return ancestry;
	}
}
