package com.havrylyuk.thenounproject.data.local.db;


import org.greenrobot.greendao.converter.PropertyConverter;
import org.joda.time.DateTime;

/**
 * Created by Igor Havrylyuk on 08.06.2017.
 */

public class DateTimeConverter implements PropertyConverter<DateTime, Long> {

    @Override
    public DateTime convertToEntityProperty(Long databaseValue) {
        return databaseValue == null ? null : new DateTime(databaseValue);
    }

    @Override
    public Long convertToDatabaseValue(DateTime entityProperty) {
        return entityProperty == null ? null : entityProperty.getMillis();
    }
}
