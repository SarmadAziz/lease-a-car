package com.example.lease_a_car.customer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    CustomerService customerService;

    CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customerResponses = customerService.getAllCustomers();
        return ResponseEntity.ok().body(customerResponses);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO savedCustomer = customerService.saveCustomer(customerDTO);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @DeleteMapping("/{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Long customerId){
        customerService.deleteCustomer(customerId);
    }

    @PutMapping("/{customerId}")
    public void updateCustomer(@PathVariable Long customerId, @RequestBody Customer updatedCustomer) {
        customerService.updateCustomer(updatedCustomer);
    }


}
