package com.example.tiims;

import javax.persistence.AttributeConverter;
import java.sql.Timestamp;

public class TimestampLongConverter implements AttributeConverter<Long, Timestamp> {
    @Override
    public Timestamp convertToDatabaseColumn(Long attribute) {
        if (attribute == null) {
            return null;
        }
        return new Timestamp(attribute);
    }

    @Override
    public Long convertToEntityAttribute(Timestamp dbData) {
        if (dbData == null) {
            return null;
        }
        return dbData.getTime();
    }
}
