package com.havrylyuk.thenounproject.data.remote.model.response;

import com.google.gson.annotations.SerializedName;
import com.havrylyuk.thenounproject.data.remote.model.NounIcon;

import java.util.List;

/**
 * Created by Igor Havrylyuk on 19.05.2017.
 */

public class IconsResponse {

    @SerializedName("generated_at")
    private String generatedAt;
    @SerializedName("icons")
    private List<NounIcon> icons;

    public IconsResponse() {
    }

    public String getGeneratedAt() {
        return generatedAt;
    }

    public List<NounIcon> getIcons() {
        return icons;
    }

    public void setGeneratedAt(String generatedAt) {
        this.generatedAt = generatedAt;
    }

    public void setIcons(List<NounIcon> icons) {
        this.icons = icons;
    }
}
