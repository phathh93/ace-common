package com.ace.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import java.util.ArrayList;
import java.util.List;

public class AbstractJsonArrayConverter<T> implements AttributeConverter<List<T>, String> {
    private static final Logger logger = LoggerFactory.getLogger(AbstractJsonArrayConverter.class);

    /**
     * @param jsonNode
     * @return
     */
    @Override
    public String convertToDatabaseColumn(List<T> jsonNode) {
        if (jsonNode == null) return "[]";
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
    public List<T> convertToEntityAttribute(String json) {
        final List<T> listItem = new ArrayList<>();
        if (StringUtils.isEmpty(json)) {
            return listItem;
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            CollectionType javaType = mapper.getTypeFactory().constructCollectionType(List.class, getClass());
            listItem.addAll(mapper.readValue(json, javaType));
        } catch (Exception e) {
            logger.error("Error parsing jsonNodeString", e);
        } finally {
            return listItem;
        }
    }
}