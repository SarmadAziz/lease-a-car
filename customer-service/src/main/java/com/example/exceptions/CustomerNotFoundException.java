package com.example.exceptions;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(Long id) {
        super("Customer with id " + id + " was not found");
    }
}
