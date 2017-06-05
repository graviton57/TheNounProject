package com.havrylyuk.thenounproject.data.remote.model.response;

import com.google.gson.annotations.SerializedName;
import com.havrylyuk.thenounproject.data.remote.model.NounIcon;

import java.util.List;

/**
 * Created by Igor Havrylyuk on 19.05.2017.
 */

public class UploadsResponse {

    @SerializedName("generated_at")
    private String generatedAt;
    private List<NounIcon> uploads;

    public UploadsResponse() {
    }

    public String getGeneratedAt() {
        return generatedAt;
    }

    public List<NounIcon> getUploads() {
        return uploads;
    }
}
