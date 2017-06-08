package com.havrylyuk.thenounproject.data.local.db.model;


import com.havrylyuk.thenounproject.data.local.db.DateTimeConverter;
import com.havrylyuk.thenounproject.data.local.db.SearchTypeConverter;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.joda.time.DateTime;

/**
 * Created by Igor Havrylyuk on 02.06.2017.
 */

@Entity(nameInDb = "search_history")
public class OrmHistory {

    @Id
    private String id;
    private String query;
    @Convert(converter = SearchTypeConverter.class, columnType = String.class)
    private SearchType type;
    @Convert(converter = DateTimeConverter.class, columnType = Long.class)
    private DateTime date;
    @Property(nameInDb = "preview_url")
    private String previewUrl;

    @Generated(hash = 1170299813)
    public OrmHistory() {
    }
    @Generated(hash = 1371833595)
    public OrmHistory(String id, String query, SearchType type, DateTime date, String previewUrl) {
        this.id = id;
        this.query = query;
        this.type = type;
        this.date = date;
        this.previewUrl = previewUrl;
    }
    public void setId(String id) {
        this.id = id;
    }

    public SearchType getType() {
        return type;
    }

    public void setType(SearchType type) {
        this.type = type;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }
    public String getId() {
        return this.id;
    }
}
