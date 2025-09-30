package com.codewithmosh.store.exceptions;

public class WrongPasswordException extends RuntimeException{
    public WrongPasswordException() {
        super("wrong old password");
    }
}
