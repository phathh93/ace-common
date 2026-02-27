package com.ace.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class JsonNodeConverter extends AbstractJsonNodeConverter<JsonNode> {
    private static final Logger logger = LoggerFactory.getLogger(AbstractJsonNodeConverter.class);

    /**
     * @param jsonNode
     * @return
     */
    @Override
    public String convertToDatabaseColumn(JsonNode jsonNode) {
        if (jsonNode == null) {
            logger.warn("jsonNode input is null, returning null");
            return null;
        }
        return jsonNode.toPrettyString();
    }

    /**
     * @param json
     * @return
     */
    @Override
    public JsonNode convertToEntityAttribute(String json) {
        if (StringUtils.isEmpty(json)) {
            logger.warn("jsonNodeString input is empty, returning null");
            return null;
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readTree(json);
        } catch (JsonProcessingException e) {
            logger.error("Error parsing jsonNodeString", e);
        }
        return null;
    }
}