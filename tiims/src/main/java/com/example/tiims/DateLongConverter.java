package com.example.tiims;

import javax.persistence.AttributeConverter;
import java.util.Date;

public class DateLongConverter implements AttributeConverter<Long, Date> {

    @Override
    public Date convertToDatabaseColumn(Long attribute) {
        if (attribute == null) {
            return null;
        }
        Date date = new Date();
        date.setTime(attribute);
        return date;
    }

    @Override
    public Long convertToEntityAttribute(Date dbData) {
        if (dbData == null) {
            return null;
        }
        return dbData.getTime();
    }

}
