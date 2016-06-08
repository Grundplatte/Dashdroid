package com.hyperion.dashdroid.books.recommendations;

/**
 * Created by infinity on 08-Jun-16.
 */
public enum BookCategoriesEnum {

	ART("Art"), BIOGRAPHY("Biography"), BUSINESS("Business"), CHICK_LIT("Chick Lit"), CHILDREN("Children"),
	CHRISTIAN("Christian"), CLASSICS("Classics"), COMICS("Comics"), CONTEMPORARY("Contemporary"),
	COOKBOOKS("Cookbooks"), CRIME("Crime"), FANTASY("Fantasy"), FICTION("Fiction"),
	GRAPHIC_NOVELS("Graphic Novels"), HISTORICAL_FICTION("Historical Fiction"), HISTORY("History"),
	HORROR("Horror"), HUMOR_AND_COMEDY("Humor and Comedy"), MANGA("Manga"), MEMOIR("Memoir"),
	MUSIC("Music"), MYSTERY("Mystery"), NONFICTION("Nonfiction"), PHILOSOPHY("Philosophy"),
	POETRY("Poetry"), PSYCHOLOGY("Psychology"), RELIGION("Religion"), ROMANCE("Romance"),
	SCIENCE("Science"), SCIENCE_FICTION("Science Fiction"), SELF_HELP("Selft Help"), SPIRITUALLY("Spiritually"),
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
