package com.ace.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class ObjectMapperUtils {

    public static final ObjectMapper objectMapper;
    public static final ObjectWriter objectWriter;

    public static final ValidatorFactory validatorFactory;
    public static final Validator validator;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE);
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        objectMapper.enable(DeserializationFeature.ACCEPT_FLOAT_AS_INT);
        objectWriter = objectMapper.writerWithDefaultPrettyPrinter();

        validatorFactory= Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    public static <T> T convert(Object value, Class<T> clazz) {
        return objectMapper.convertValue(value, clazz);
    }

    public static <T> T convertAndValidate(Object value, Class<T> clazz) {
        T result = objectMapper.convertValue(value, clazz);

        // Validate the converted object
        Set<ConstraintViolation<T>> violations = validator.validate(result);
        if (!violations.isEmpty()) {
            // Build an error message from violations
            StringBuilder errorMessage = new StringBuilder("Validation failed: ");
            for (ConstraintViolation<T> violation : violations) {
                errorMessage.append(violation.getPropertyPath())
                        .append(" ")
                        .append(violation.getMessage())
                        .append("; ");
            }
            throw new IllegalArgumentException(errorMessage.toString());
        }
        return result;
    }

    public static <T> T convertAndValidate(Object value, Class<T> clazz, Exception exception) throws Exception {
        T result = objectMapper.convertValue(value, clazz);

        // Validate the converted object
        Set<ConstraintViolation<T>> violations = validator.validate(result);
        if (!violations.isEmpty()) {
            throw exception;
        }
        return result;
    }

    public static JsonNode emptyObjectNode() {
        return objectMapper.createObjectNode();
    }
}
