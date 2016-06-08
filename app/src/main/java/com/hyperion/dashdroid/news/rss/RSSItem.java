package com.hyperion.dashdroid.news.rss;

import java.io.Serializable;

/**
 * Created by Valdrin on 12/05/2016.
 */

public class RSSItem implements Serializable {

	private String title = null;
	private String description = null;
	private String link = null;
	private String date = null;
	private String image = null;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}

