package com.example.lease_a_car.customer;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(Long id) {
        super(STR."Customer with id \{id} was not found");
    }
}
