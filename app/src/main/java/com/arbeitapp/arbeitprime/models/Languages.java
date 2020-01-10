package com.arbeitapp.arbeitprime.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Languages {

    @SerializedName("iso_639_1")
    @Expose
    private String iso639;
    @SerializedName("name")
    @Expose
    private String name;

    public String getIso639() {
        return iso639;
    }

    public void setIso639(String iso639) {
        this.iso639 = iso639;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
