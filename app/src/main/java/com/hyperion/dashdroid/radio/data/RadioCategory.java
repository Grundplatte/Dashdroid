package com.hyperion.dashdroid.radio.data;

import java.util.ArrayList;

/**
 * Created by Rainer on 11.05.2016.
 */
public class RadioCategory {

    private int ID;
    private String title;
    private String description;
    private String slug;
    private int ancestry;

    private ArrayList<RadioCategory> subCategories;

    public RadioCategory(int ID, String title, String description, String slug, int ancestry, ArrayList<RadioCategory> subCategories) {
        this.ID = ID;
        this.title = title;
        this.description = description;
        this.slug = slug;
        this.ancestry = ancestry;
        this.subCategories = subCategories;
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

    public ArrayList<RadioCategory> getSubCategories() {
        return subCategories;
    }
}
