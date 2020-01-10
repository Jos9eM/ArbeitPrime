package com.arbeitapp.arbeitprime.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Countrys {

    @SerializedName("iso_3166_1")
    @Expose
    private String iso3166;
    @SerializedName("name")
    @Expose
    private String name;

    public String getIso3166() {
        return iso3166;
    }

    public void setIso3166(String iso3166) {
        this.iso3166 = iso3166;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
