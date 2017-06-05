package com.havrylyuk.thenounproject.data.remote.model.response;

import com.google.gson.annotations.SerializedName;
import com.havrylyuk.thenounproject.data.remote.model.NounIcon;

import java.util.List;

/**
 * Created by Igor Havrylyuk on 19.05.2017.
 */

public class RecentUploadResponse {

    @SerializedName("generated_at")
    private String generatedAt;
    @SerializedName("recent_uploads")
    private List<NounIcon> recentUploads;

    public RecentUploadResponse() {
    }

    public String getGeneratedAt() {
        return generatedAt;
    }

    public List<NounIcon> getRecentUploads() {
        return recentUploads;
    }

    public void setGeneratedAt(String generatedAt) {
        this.generatedAt = generatedAt;
    }

    public void setRecentUploads(List<NounIcon> recentUploads) {
        this.recentUploads = recentUploads;
    }
}
