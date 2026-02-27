package com.ace.util.converters;

import javax.persistence.Converter;

import com.ace.enums.CategoryType;

@Converter(autoApply = true)
public class CategoryTypeConverter extends EnumAttributeConverter<CategoryType> {
    public CategoryTypeConverter() {
        super(CategoryType.class);
    }
}

