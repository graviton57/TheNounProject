package com.havrylyuk.thenounproject.data.remote.model;


/**
 *  Object representing author information returned by the NounProject API.
 *
 *  Created by Igor Havrylyuk on 18.05.2017.
 *  "author" Is identical "uploader"
 */

public class NounAuthor {

    private String location;
    private String name;
    private String permalink;
    private String username;

    public NounAuthor() {
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NounAuthor)) return false;

        NounAuthor that = (NounAuthor) o;

        if (!getLocation().equals(that.getLocation())) return false;
        if (!getName().equals(that.getName())) return false;
        return getPermalink().equals(that.getPermalink()) && getUsername().equals(that.getUsername());

    }

    @Override
    public int hashCode() {
        int result = getLocation().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getPermalink().hashCode();
        result = 31 * result + getUsername().hashCode();
        return result;
    }
}

