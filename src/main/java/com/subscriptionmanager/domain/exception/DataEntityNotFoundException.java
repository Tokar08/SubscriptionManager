package com.subscriptionmanager.domain.exception;

import lombok.Getter;

@Getter
public class DataEntityNotFoundException extends RuntimeException {
    private final String entityTypeName;
    private final String fieldName;
    private final Object fieldValue;

    public DataEntityNotFoundException(String entityTypeName, String fieldName, Object fieldValue) {
        super(entityTypeName + " not found with " + fieldName + " : '" + fieldValue + "'");
        this.entityTypeName = entityTypeName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
