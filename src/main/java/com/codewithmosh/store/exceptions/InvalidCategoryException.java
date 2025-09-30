package com.codewithmosh.store.exceptions;

public class InvalidCategoryException extends RuntimeException{
    public InvalidCategoryException() {
        super("Invalid Category");
    }
}
