package com.havrylyuk.thenounproject.data.local.db.model;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;


/**
 * Created by Igor Havrylyuk on 25.05.2017.
 */
@Entity(nameInDb = "icons")
public class OrmIcon {

    @Id
    private String id;
    private String attribution;
    @Property(nameInDb = "date_uploaded")
    private String dateUploaded;
    @Property(nameInDb = "is_active")
    private String isActive;
    @Property(nameInDb = "license_description")
    private String licenseDesc;
    private String permalink;
    @Property(nameInDb = "preview_url")
    private String previewUrl;
    private String preview_url_42;
    private String preview_url_84;
    private String sponsor_id;
    private String term;
    @Property(nameInDb = "term_slug")
    private String termSlug;
    @Property(nameInDb = "uploader_id")
    private String uploaderId;
    private int year;
    @Property(nameInDb = "is_favorite")
    private boolean isFavorite = false;

    @Generated(hash = 1192879787)
    public OrmIcon() {
    }

    @Generated(hash = 22946277)
    public OrmIcon(String id, String attribution, String dateUploaded, String isActive,
            String licenseDesc, String permalink, String previewUrl, String preview_url_42,
            String preview_url_84, String sponsor_id, String term, String termSlug,
            String uploaderId, int year, boolean isFavorite) {
        this.id = id;
        this.attribution = attribution;
        this.dateUploaded = dateUploaded;
        this.isActive = isActive;
        this.licenseDesc = licenseDesc;
        this.permalink = permalink;
        this.previewUrl = previewUrl;
        this.preview_url_42 = preview_url_42;
        this.preview_url_84 = preview_url_84;
        this.sponsor_id = sponsor_id;
        this.term = term;
        this.termSlug = termSlug;
        this.uploaderId = uploaderId;
        this.year = year;
        this.isFavorite = isFavorite;
    }

    public String getAttribution() {
        return attribution;
    }

    public void setAttribution(String attribution) {
        this.attribution = attribution;
    }

    public String getDateUploaded() {
        return dateUploaded;
    }

    public void setDateUploaded(String dateUploaded) {
        this.dateUploaded = dateUploaded;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getLicenseDesc() {
        return licenseDesc;
    }

    public void setLicenseDesc(String licenseDesc) {
        this.licenseDesc = licenseDesc;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public String getPreview_url_42() {
        return preview_url_42;
    }

    public void setPreview_url_42(String preview_url_42) {
        this.preview_url_42 = preview_url_42;
    }

    public String getPreview_url_84() {
        return preview_url_84;
    }

    public void setPreview_url_84(String preview_url_84) {
        this.preview_url_84 = preview_url_84;
    }

    public String getSponsor_id() {
        return sponsor_id;
    }

    public void setSponsor_id(String sponsor_id) {
        this.sponsor_id = sponsor_id;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getTermSlug() {
        return termSlug;
    }

    public void setTermSlug(String termSlug) {
        this.termSlug = termSlug;
    }

    public String getUploaderId() {
        return uploaderId;
    }

    public void setUploaderId(String uploaderId) {
        this.uploaderId = uploaderId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean getFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public boolean getIsFavorite() {
        return this.isFavorite;
    }

    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

}
