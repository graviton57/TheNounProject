package com.havrylyuk.thenounproject.data.remote.model;


/**
 * Object Representing user information returned
 * by the NounProject API.
 * Created by Igor Havrylyuk on 18.05.2017.
 */

public class NounUploader  {

    private String location;
    private String name;
    private String permalink;
    private String username;

    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public String getPermalink() {
        return permalink;
    }

    public String getUsername() {
        return username;
    }
}
