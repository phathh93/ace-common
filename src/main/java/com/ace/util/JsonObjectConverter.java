package com.ace.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class JsonObjectConverter implements AttributeConverter<JsonObject, String> {

    private final Gson gson = new Gson();

    @Override
    public String convertToDatabaseColumn(JsonObject jsonObject) {
        return gson.toJson(jsonObject);
    }

    @Override
    public JsonObject convertToEntityAttribute(String jsonString) {
        return gson.fromJson(jsonString, JsonObject.class);
    }
}
