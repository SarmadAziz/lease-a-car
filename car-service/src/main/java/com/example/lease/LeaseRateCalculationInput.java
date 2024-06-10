package com.example.lease;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LeaseRateCalculationInput {
    /**
     * The max amount of kilometers to be driven per year.
     */
    private Integer kmPerYear;

    /**
     * The duration of the lease contract in months.
     */
    private Integer durationInMonths;

    /**
     * The interest rate percentage for the lease contract.
     */
    private BigDecimal interestRate;

    /**
     * The net price of the leased item.
     */
    private BigDecimal nettPrice;
}