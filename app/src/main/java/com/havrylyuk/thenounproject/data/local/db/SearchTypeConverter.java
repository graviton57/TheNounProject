package com.havrylyuk.thenounproject.data.local.db;

import com.havrylyuk.thenounproject.data.local.db.model.SearchType;

import org.greenrobot.greendao.converter.PropertyConverter;

/**
 * Created by Igor Havrylyuk on 02.06.2017.
 */

public class SearchTypeConverter implements PropertyConverter<SearchType, String> {

    @Override
    public SearchType convertToEntityProperty(String databaseValue) {
        return SearchType.valueOf(databaseValue);
    }

    @Override
    public String convertToDatabaseValue(SearchType entityProperty) {
        return entityProperty.name();
    }
}
