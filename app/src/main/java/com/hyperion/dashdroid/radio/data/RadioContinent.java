package com.hyperion.dashdroid.radio.data;

public class RadioContinent {

    private int ID;
    private String name;
    private String slug;

    public RadioContinent(int ID, String name, String slug) {
        this.ID = ID;
        this.name = name;
        this.slug = slug;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }
}
