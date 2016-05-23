package com.hyperion.dashdroid.radio;

import java.util.ArrayList;

/**
 * Created by Rainer on 11.05.2016.
 */
public class RadioChannelCategory {

    private int ID;
    private String title;
    private String description;
    private String slug;
    private int ancestry;

    private ArrayList<RadioChannelCategory> subCategories;

    public RadioChannelCategory(int ID, String title, String description, String slug, int ancestry, ArrayList<RadioChannelCategory> subCategories) {
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

    public ArrayList<RadioChannelCategory> getSubCategories() {
        return subCategories;
    }
}
