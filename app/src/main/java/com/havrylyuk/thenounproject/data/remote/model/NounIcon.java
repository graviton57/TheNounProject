package com.havrylyuk.thenounproject.data.remote.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Object representing icon information returned
 * by the NounProject API.
 * Created by Igor Havrylyuk on 18.05.2017.
 */

public class NounIcon  {

    private String attribution;
    @SerializedName("attribution_icon_url")
    private String attributionIconUrl;
    @SerializedName("attribution_preview_url")
    private String attributionPreviewUrl;
    @SerializedName("date_uploaded")
    private String dateUploaded;
    private String id;
    @SerializedName("is_active")
    private String isActive;
    @SerializedName("license_description")
    private String licenseDescription;
    private String permalink;
    @SerializedName("preview_url")
    private String previewUrl;
    @SerializedName("preview_url_42")
    private String previewUrl42;
    @SerializedName("preview_url_84")
    private String previewUrl84;
    private NounSponsor sponsor;
    @SerializedName("sponsor_campaign_link")
    private String sponsorCampaignLink;
    @SerializedName("sponsor_id")
    private String sponsorId;
    private List<NounTag> tags;
    private String term;
    @SerializedName("term_id")
    private String termId;
    @SerializedName("term_slug")
    private String termSlug;
    private NounUploader uploader;
    @SerializedName("uploader_id")
    private String uploaderId;
    private int year;
    private boolean isFavorite;

    public String getAttribution() {
        return attribution;
    }

    public void setAttribution(String attribution) {
        this.attribution = attribution;
    }

    public String getAttributionIconUrl() {
        return attributionIconUrl;
    }

    public String getAttributionPreviewUrl() {
        return attributionPreviewUrl;
    }

    public String getDateUploaded() {
        return dateUploaded;
    }

    public String getId() {
        return id;
    }

    public String getIsActive() {
        return isActive;
    }

    public String getLicenseDescription() {
        return licenseDescription;
    }

    public String getPermalink() {
        return permalink;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public String getPreviewUrl42() {
        return previewUrl42;
    }

    public String getPreviewUrl84() {
        return previewUrl84;
    }

    public NounSponsor getSponsor() {
        return sponsor;
    }

    public String getSponsorCampaignLink() {
        return sponsorCampaignLink;
    }

    public String getSponsorId() {
        return sponsorId;
    }

    public List<NounTag> getTags() {
        return tags;
    }

    public String getTerm() {
        return term;
    }

    public String getTermId() {
        return termId;
    }

    public String getTermSlug() {
        return termSlug;
    }

    public NounUploader getUploader() {
        return uploader;
    }

    public String getUploaderId() {
        return uploaderId;
    }

    public int getYear() {
        return year;
    }

    public void setAttributionIconUrl(String attributionIconUrl) {
        this.attributionIconUrl = attributionIconUrl;
    }

    public void setAttributionPreviewUrl(String attributionPreviewUrl) {
        this.attributionPreviewUrl = attributionPreviewUrl;
    }

    public void setDateUploaded(String dateUploaded) {
        this.dateUploaded = dateUploaded;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public void setLicenseDescription(String licenseDescription) {
        this.licenseDescription = licenseDescription;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public void setPreviewUrl42(String previewUrl42) {
        this.previewUrl42 = previewUrl42;
    }

    public void setPreviewUrl84(String previewUrl84) {
        this.previewUrl84 = previewUrl84;
    }

    public void setSponsor(NounSponsor sponsor) {
        this.sponsor = sponsor;
    }

    public void setSponsorCampaignLink(String sponsorCampaignLink) {
        this.sponsorCampaignLink = sponsorCampaignLink;
    }

    public void setSponsorId(String sponsorId) {
        this.sponsorId = sponsorId;
    }

    public void setTags(List<NounTag> tags) {
        this.tags = tags;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public void setTermId(String termId) {
        this.termId = termId;
    }

    public void setTermSlug(String termSlug) {
        this.termSlug = termSlug;
    }

    public void setUploader(NounUploader uploader) {
        this.uploader = uploader;
    }

    public void setUploaderId(String uploaderId) {
        this.uploaderId = uploaderId;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
