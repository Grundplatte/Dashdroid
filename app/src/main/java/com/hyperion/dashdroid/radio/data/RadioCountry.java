package com.hyperion.dashdroid.radio.data;

public class RadioCountry {

    private String name;
    private String country_code;
    private String region;
    private String subregion;

    public RadioCountry(String name, String country_code, String region, String subregion) {
        this.name = name;
        this.country_code = country_code;
        this.region = region;
        this.subregion = subregion;
    }

    public String getName() {
        return name;
    }

    public String getCountry_code() {
        return country_code;
    }

    public String getRegion() {
        return region;
    }

    public String getSubregion() {
        return subregion;
    }

}

