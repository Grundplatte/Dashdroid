package com.hyperion.dashdroid.radio.data;

import java.util.ArrayList;

/**
 * Data class for handling radio channels
 */
public class RadioChannel {
	private int ID;
	private String name;
	private String country;
	private String description;
	private String imageUrl;
	private String thumbUrl;
	private String slug;
	private String website;

	public boolean isFavorited() {
		return favorited;
	}

	public void setFavorited(boolean favorited) {
		this.favorited = favorited;
	}

	private boolean favorited;
	private ArrayList<RadioStream> radioStreams;

	public RadioChannel(int ID) {
		this.ID = ID;
		this.name = null;
		this.country = null;
		this.description = null;
		this.imageUrl = null;
		this.thumbUrl = null;
		this.slug = null;
		this.website = null;
		this.radioStreams = null;
	}

	public RadioChannel(int ID, String name, String country, String description, String imageUrl, String thumbUrl, String slug, String website, ArrayList<RadioStream> radioStreams) {
		this.ID = ID;
		this.name = name;
		this.country = country;
		this.description = description;
		this.imageUrl = imageUrl;
		this.thumbUrl = thumbUrl;
		this.slug = slug;
		this.website = website;
		this.radioStreams = radioStreams;
		this.favorited = false;
	}

	public RadioChannel(int ID, String name, String country, String description, String imageUrl, String thumbUrl, String slug, String website, ArrayList<RadioStream> radioStreams, ArrayList<RadioCategory> radioChannelCategories) {
		this.ID = ID;
		this.name = name;
		this.country = country;
		this.description = description;
		this.imageUrl = imageUrl;
		this.thumbUrl = thumbUrl;
		this.slug = slug;
		this.website = website;
		this.radioStreams = radioStreams;
		this.favorited = false;
	}

	public int getID() {
		return ID;
	}

	public String getName() {
		return name;
	}

	public String getCountry() {
		return country;
	}

	public String getDescription() {
		return description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public String getThumbUrl() {
		return thumbUrl;
	}

	public String getSlug() {
		return slug;
	}

	public String getWebsite() {
		return website;
	}

	public ArrayList<RadioStream> getRadioStreams() {
		return radioStreams;
	}

	@Override
	public boolean equals(Object o) {
		return this.getID() == ((RadioChannel) o).getID();
	}
}
