package com.example.lease_a_car.customer;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Customer {
    @Id
    @SequenceGenerator(
            name = "customer_id_sequence",
            sequenceName = "customer_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_sequence"
    )
    private Long id;
    private String name;
    private String street;
    private Integer houseNumber;
    private String zipcode;
    private String place;
    private String email;
    private String phoneNumber;
}
