package com.example.customer;

import jakarta.annotation.PostConstruct;

/**
 * This class initializes the customer database with two customers, not something you want for prod obviously
 */
public class InitCustomer {
    private final CustomerRepository customerRepository;

    public InitCustomer(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostConstruct
    public void init() {
        customerRepository.save(new Customer(1L, "John Doe", "123 Main St", 1,
                "1234AB", "Springfield", "john@example.com", "1234567890"));
        customerRepository.save(new Customer(2L, "Jane Smith", "456 Elm St", 2,
                "6789AB", "Rivertown", "jane@example.com", "9876543210"));
    }
}
