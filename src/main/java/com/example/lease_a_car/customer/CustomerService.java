package com.example.lease_a_car.customer;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    CustomerRepository customerRepository;

    CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(this::convertToDto).toList();
    }

    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        Customer customer = convertToEntity(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return convertToDto(savedCustomer);
    }

    public void deleteCustomer(Long id) {
        boolean exists = customerRepository.existsById(id);
        if (!exists){
            throw new CustomerNotFoundException(id);
        }
        customerRepository.deleteById(id);
    }

    public void updateCustomer(Long customerId, CustomerDTO updatedCustomer) {
        Optional<Customer> existingCustomerOptional = customerRepository.findById(customerId);
        if (existingCustomerOptional.isPresent()) {
            Customer existingCustomer = updateCustomer(updatedCustomer, existingCustomerOptional.get());
            customerRepository.save(existingCustomer);
        } else {
            customerRepository.save(convertToEntity(updatedCustomer));
        }

//        customerRepository.findById(customerId)
//                .map(customer -> {
//                    Customer changedCustomer = updateCustomer(updatedCustomer, customer);
//                    customerRepository.save(changedCustomer);
//                    return null;
//                })
//                .orElse(customerRepository.save(convertToEntity(updatedCustomer)));
    }

    private CustomerDTO convertToDto(Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getId())
                .name(customer.getName())
                .street(customer.getStreet())
                .houseNumber(customer.getHouseNumber())
                .zipcode(customer.getZipcode())
                .place(customer.getPlace())
                .email(customer.getEmail())
                .phoneNumber(customer.getPhoneNumber())
                .build();
    }
    private Customer convertToEntity(CustomerDTO customerDTO) {
        return Customer.builder()
                .name(customerDTO.getName())
                .street(customerDTO.getStreet())
                .houseNumber(customerDTO.getHouseNumber())
                .zipcode(customerDTO.getZipcode())
                .place(customerDTO.getPlace())
                .email(customerDTO.getEmail())
                .phoneNumber(customerDTO.getPhoneNumber())
                .build();

    }

    private static Customer updateCustomer(CustomerDTO updatedCustomer, Customer existingCustomer) {
        existingCustomer.setName(updatedCustomer.getName());
        existingCustomer.setStreet(updatedCustomer.getStreet());
        existingCustomer.setHouseNumber(updatedCustomer.getHouseNumber());
        existingCustomer.setZipcode(updatedCustomer.getZipcode());
        existingCustomer.setPlace(updatedCustomer.getPlace());
        existingCustomer.setEmail(updatedCustomer.getEmail());
        existingCustomer.setPhoneNumber(updatedCustomer.getPhoneNumber());
        return existingCustomer;
    }
}