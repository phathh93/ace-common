package com.ace.util.converters;

import javax.persistence.AttributeConverter;

public abstract class EnumAttributeConverter<E extends Enum<E>> implements AttributeConverter<E, String> {
    private final Class<E> enumClass;

    protected EnumAttributeConverter(Class<E> enumClass) {
        this.enumClass = enumClass;
    }

    @Override
    public String convertToDatabaseColumn(E attribute) {
        return (attribute == null) ? null : attribute.name();
    }

    @Override
    public E convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }
        try {
            return Enum.valueOf(enumClass, dbData);
        } catch (IllegalArgumentException e) {
            // handle log here if needed
            return null;
        }
    }
}
