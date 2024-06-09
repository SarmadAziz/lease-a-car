package com.example.lease_a_car.lease;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LeaseRateCalculationInput {
    private Integer kmPerYear;
    private Integer durationInMonths;
    private BigDecimal interestRate;
    private BigDecimal nettPrice;
}