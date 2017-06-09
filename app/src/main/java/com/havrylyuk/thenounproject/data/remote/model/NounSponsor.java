package com.havrylyuk.thenounproject.data.remote.model;

import com.google.gson.annotations.SerializedName;

/**
 * Object Representing sponsor information returned
 * by the NounProject API.
 * Created by Igor Havrylyuk on 19.05.2017.
 */

public class NounSponsor {

    @SerializedName("logo_url")
    private String logoUrl;
    private String name;
    private String permalink;

    public NounSponsor() {
    }

    public NounSponsor(String logoUrl, String name, String permalink) {
        this.logoUrl = logoUrl;
        this.name = name;
        this.permalink = permalink;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }
}
