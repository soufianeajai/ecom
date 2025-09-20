package com.codewithmosh.store.exceptions;

public class InvalidCategoryException extends RuntimeException{
    public InvalidCategoryException(String message) {
        super(message);
    }
}
