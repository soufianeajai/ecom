package com.codewithmosh.store.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id) {
        super("the user with id"  + id + " not found");
    }
}
