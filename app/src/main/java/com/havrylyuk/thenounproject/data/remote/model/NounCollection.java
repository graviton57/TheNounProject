package com.havrylyuk.thenounproject.data.remote.model;


import java.util.List;

/**
 *  Object representing collection information returned
 *  by the NounProject API.
 *  See http://api.thenounproject.com/documentation.html#collection
 * Created by Igor Havrylyuk on 18.05.2017.
 */
public class NounCollection  {

    private NounAuthor author;
    private String author_id;
    private String date_created;
    private String date_updated;
    private String description;
    private String id;
    private String is_collaborative;
    private String is_featured;
    private String is_published;
    private String is_store_item;
    private String name;
    private String permalink;
    private String slug;
    private NounSponsor sponsor;
    private String sponsor_campaign_link;
    private String sponsor_id;
    private List<String> tags;
    private String template;

    public NounCollection() {
    }

    public NounAuthor getAuthor() {
        return author;
    }

    public void setAuthor(NounAuthor author) {
        this.author = author;
    }

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getDate_updated() {
        return date_updated;
    }

    public void setDate_updated(String date_updated) {
        this.date_updated = date_updated;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIs_collaborative() {
        return is_collaborative;
    }

    public void setIs_collaborative(String is_collaborative) {
        this.is_collaborative = is_collaborative;
    }

    public NounSponsor getSponsor() {
        return sponsor;
    }

    public void setSponsor(NounSponsor sponsor) {
        this.sponsor = sponsor;
    }

    public String getIs_featured() {
        return is_featured;
    }

    public void setIs_featured(String is_featured) {
        this.is_featured = is_featured;
    }

    public String getIs_published() {
        return is_published;
    }

    public void setIs_published(String is_published) {
        this.is_published = is_published;
    }

    public String getIs_store_item() {
        return is_store_item;
    }

    public void setIs_store_item(String is_store_item) {
        this.is_store_item = is_store_item;
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


    public String getSponsor_campaign_link() {
        return sponsor_campaign_link;
    }

    public void setSponsor_campaign_link(String sponsor_campaign_link) {
        this.sponsor_campaign_link = sponsor_campaign_link;
    }

    public String getSponsor_id() {
        return sponsor_id;
    }

    public void setSponsor_id(String sponsor_id) {
        this.sponsor_id = sponsor_id;
    }


    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }
}
