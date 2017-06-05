package com.havrylyuk.thenounproject.data.local.db.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by Igor Havrylyuk on 20.05.2017.
 */

@Entity(nameInDb = "collections")
public class OrmCollection {

    @Id
    private String id;
    @Property(nameInDb = "author_name")
    private String authorName;
    @Property(nameInDb = "author_loc")
    private String authorLoc;
    @Property(nameInDb = "date_created")
    private String dateCreated;
    @Property(nameInDb = "date_updated")
    private String dateUpdated;
    private String description;
    private String name;
    private String permalink;
    private String slug;
    @Property(nameInDb = "is_favorite")
    private boolean isFavorite;

    @Generated(hash = 1782087458)
    public OrmCollection() {
    }

    @Generated(hash = 1479514744)
    public OrmCollection(String id, String authorName, String authorLoc, String dateCreated,
            String dateUpdated, String description, String name, String permalink, String slug,
            boolean isFavorite) {
        this.id = id;
        this.authorName = authorName;
        this.authorLoc = authorLoc;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.description = description;
        this.name = name;
        this.permalink = permalink;
        this.slug = slug;
        this.isFavorite = isFavorite;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorLoc() {
        return authorLoc;
    }

    public void setAuthorLoc(String authorLoc) {
        this.authorLoc = authorLoc;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
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
