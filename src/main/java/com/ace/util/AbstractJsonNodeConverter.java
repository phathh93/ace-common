package com.ace.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;

public class AbstractJsonNodeConverter<T> implements AttributeConverter<T, String> {
    private static final Logger logger = LoggerFactory.getLogger(AbstractJsonNodeConverter.class);

    /**
     * @param jsonNode
     * @return
     */
    @Override
    public String convertToDatabaseColumn(T jsonNode) {
        if (jsonNode == null) return null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(jsonNode);
        } catch (Exception ex) {
            logger.error("jsonNode input, returning null");
        }
        return null;
    }

    /**
     * @param json
     * @return
     */
    @Override
    public T convertToEntityAttribute(String json) {
        if (StringUtils.isEmpty(json)) return null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            return (T) mapper.readValue(json, getClass());
        } catch (JsonProcessingException e) {
            logger.error("Error parsing jsonNodeString", e);
        }
        return null;
    }
}