package com.example.lease_a_car.customer;

import com.example.lease_a_car.exceptions.CustomerNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void getAllCustomers() {
        Customer customer1 = new Customer(1L, "John Doe", "Main St", 123, "1234AB", "Anytown", "john.doe@example.com", "1234567890");
        Customer customer2 = new Customer(2L, "Jane Smith", "Second St", 456, "5678CD", "Othertown", "jane.smith@example.com", "0987654321");

        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer1, customer2));

        List<CustomerDto> customerDtos = customerService.getAllCustomers();
        assertEquals(2, customerDtos.size());
        assertEquals("John Doe", customerDtos.get(0).getName());
        assertEquals("Jane Smith", customerDtos.get(1).getName());
    }

    @Test
    void saveCustomer() {
        CustomerDto customerDto = CustomerDto.builder()
                .name("John")
                .street("Main St")
                .houseNumber(123)
                .zipcode("1234AB")
                .place("Anytown")
                .email("john.@example.com")
                .phoneNumber("1234567890")
                .build();
        Customer savedCustomer = new Customer(1L, "John Doe", "Main St", 123, "1234AB", "Anytown", "john.doe@example.com", "1234567890");

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        CustomerDto savedCustomerDto = customerService.saveCustomer(customerDto);
        assertEquals(1L, savedCustomerDto.getId());
        assertEquals("John Doe", savedCustomerDto.getName());
    }

    @Test
    void deleteCustomer_CustomerExists() {
        Long customerId = 1L;

        when(customerRepository.existsById(customerId)).thenReturn(true);
        doNothing().when(customerRepository).deleteById(customerId);

        customerService.deleteCustomer(customerId);
        verify(customerRepository, times(1)).deleteById(customerId);
    }

    @Test
    void deleteCustomer_CustomerDoesNotExist() {
        Long customerId = 1L;

        when(customerRepository.existsById(customerId)).thenReturn(false);

        CustomerNotFoundException exception = assertThrows(CustomerNotFoundException.class, () -> {
            customerService.deleteCustomer(customerId);
        });
        assertEquals("Customer with id 1 was not found", exception.getMessage());
    }

    @Test
    void updateCustomer_CustomerExists() {
        Long customerId = 1L;
        Customer existingCustomer = new Customer(customerId, "John Doe", "Main St", 123, "1234AB", "Anytown", "john.doe@example.com", "1234567890");
        CustomerDto updatedCustomerDto = CustomerDto.builder()
                .name("John Updated")
                .street("Main St")
                .houseNumber(123)
                .zipcode("1234AB")
                .place("Anytown")
                .email("john.updated@example.com")
                .phoneNumber("1234567890")
                .build();

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(existingCustomer));
        when(customerRepository.save(any(Customer.class))).thenReturn(existingCustomer);

        customerService.updateCustomer(customerId, updatedCustomerDto);
        verify(customerRepository, times(1)).save(existingCustomer);
        assertEquals("John Updated", existingCustomer.getName());
        assertEquals("john.updated@example.com", existingCustomer.getEmail());
    }

    @Test
    void updateCustomer_CustomerDoesNotExist() {
        Long customerId = 1L;
        CustomerDto updatedCustomerDto = CustomerDto.builder()
                .name("John Updated")
                .street("Main St")
                .houseNumber(123)
                .zipcode("1234AB")
                .place("Anytown")
                .email("john.updated@example.com")
                .phoneNumber("1234567890")
                .build();

        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());
        when(customerRepository.save(any(Customer.class))).thenReturn(any(Customer.class));
        customerService.updateCustomer(customerId, updatedCustomerDto);

        verify(customerRepository, times(1)).save(any(Customer.class));
    }
}