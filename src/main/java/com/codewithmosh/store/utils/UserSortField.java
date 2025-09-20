package com.codewithmosh.store.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserSortField {
    ID("id"),
    NAME("name"),
    EMAIL("email");

    private final String fieldName;

    public static UserSortField fromString(String fieldName) {
        for (UserSortField field : UserSortField.values()) {
            if (field.fieldName.equalsIgnoreCase(fieldName)) {
                return field;
            }
        }
        return ID;
    }
}
