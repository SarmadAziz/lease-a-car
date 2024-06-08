package com.example.lease_a_car.exceptions;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(Long id) {
        super(STR."Customer with id \{id} was not found");
    }
}
