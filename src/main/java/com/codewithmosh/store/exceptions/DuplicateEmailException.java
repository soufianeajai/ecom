package com.codewithmosh.store.exceptions;

public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException(String email) {
        super("The Email provided : " + email + " already exist");
    }
}
