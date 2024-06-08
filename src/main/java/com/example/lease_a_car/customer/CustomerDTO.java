package com.example.lease_a_car.customer;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CustomerDTO {
    private Long id;
    private String name;
    private String street;
    private Integer houseNumber;
    private String zipcode;
    private String place;
    private String email;
    private String phoneNumber;
}
