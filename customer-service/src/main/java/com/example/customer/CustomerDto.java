package com.example.customer;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CustomerDto {
    @Nullable
    private Long id;

    @NotBlank(message= "Name is mandatory")
    private String name;

    @NotBlank(message= "Street is mandatory")
    private String street;

    @NotNull(message= "House number is mandatory")
    private Integer houseNumber;

    @NotBlank(message= "Zipcode is mandatory")
    @Size(min = 6, max=6, message = "Zipcode must be of format 1234AB")
    private String zipcode;

    @NotBlank(message= "Place is mandatory")
    private String place;

    @NotBlank(message= "Email is mandatory")
    @Email(message = "Invalid email")
    private String email;

    @NotBlank(message= "Phone number is mandatory")
    @Size(min = 10, max=10, message = "Phone number must be 10 digits")
    private String phoneNumber;
}
