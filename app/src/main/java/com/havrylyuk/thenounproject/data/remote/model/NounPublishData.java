package com.havrylyuk.thenounproject.data.remote.model;

/**
 * See http://api.thenounproject.com/documentation.html#user
 * {
 *   "icons": "42,143,1337"
 *  }
 * Created by Igor Havrylyuk on 19.05.2017.
 */

public class NounPublishData {

    private String icons;

    public NounPublishData() {
    }

    public String getIcons() {
        return icons;
    }

    public void setIcons(String icons) {
        this.icons = icons;
    }
}
