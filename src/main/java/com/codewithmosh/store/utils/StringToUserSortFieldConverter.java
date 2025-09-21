package com.codewithmosh.store.utils;

import com.codewithmosh.store.exceptions.InvalidSortFieldException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToUserSortFieldConverter implements Converter<String, UserSortField> {

    @Override
    public UserSortField convert(String source) {
        try {
            return UserSortField.valueOf(source.toUpperCase());
        } catch (Exception e) {
            throw new InvalidSortFieldException("Invalid sort field: " + source);
        }
    }
}