package com.hyperion.dashdroid.books.recommendations;

/**
 * Created by infinity on 08-Jun-16.
 */
public enum BookCategoriesEnum {

	ART("Art"), BIOGRAPHY("Biography"), BUSINESS("Business"), CHILDREN("Children"),
	COOKBOOKS("Cookbooks"), CRIME("Crime"), FANTASY("Fantasy"), FICTION("Fiction"),
	GRAPHIC_NOVELS("Graphic Novels"), HISTORY("History"), HORROR("Horror"), HUMOR_AND_COMEDY("Comedy"),
	MUSIC("Music"), MYSTERY("Mystery"), PHILOSOPHY("Philosophy"),
	POETRY("Poetry"), PSYCHOLOGY("Psychology"), RELIGION("Religion"), ROMANCE("Romance"),
	SCIENCE("Science"), SCIENCE_FICTION("Science Fiction"),
	SPORTS("Sports"), THRILLER("Thriller"), TRAVEL("Travel");

	private String name;

	BookCategoriesEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getUrlPart() {
		return this.name.replace(" ", "+");
	}
}
