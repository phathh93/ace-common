// package com.ace.util;

// import javax.persistence.AttributeConverter;
// import javax.persistence.Converter;

// import com.ace.module.transaction.TransactionType;

// @Converter(autoApply = true)
// public class TransactionTypeConverter implements
// AttributeConverter<TransactionType, String> {

// @Override
// public String convertToDatabaseColumn(TransactionType attribute) {
// if (attribute == null) {
// return null;
// }
// return attribute.getCode();
// }

// @Override
// public TransactionType convertToEntityAttribute(String dbData) {
// if (dbData == null) {
// return null;
// }
// return TransactionType.fromName(dbData);
// }
// }