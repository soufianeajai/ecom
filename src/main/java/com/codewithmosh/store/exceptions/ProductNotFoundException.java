package com.codewithmosh.store.exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(Long id ) {
        super("Product with the Id " + id + " is not found");
    }
    public ProductNotFoundException(Byte id ) {
        super("Product with the Category Id " + id + " is not found");
    }
}
