package com.example.lease;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class LeaseCalculatorService {

    /**
     * Calculates the lease rate based on the provided input parameters.
     *
     * @param input The input containing parameters for the lease rate calculation.
     * @return The calculated lease rate.
     */
    public BigDecimal calculateLeaseRate(LeaseRateCalculationInput input) {
        BigDecimal MONTHS = BigDecimal.valueOf(12);
        BigDecimal kmPerYear = BigDecimal.valueOf(input.getKmPerYear());
        BigDecimal durationInMonths = BigDecimal.valueOf(input.getDurationInMonths());
        BigDecimal interestRate = input.getInterestRate();
        BigDecimal nettPrice = input.getNettPrice();

        // firstTerm = (( mileage / 12 ) * duration ) / Nett price
        BigDecimal firstTerm = kmPerYear.divide(MONTHS,10, RoundingMode.HALF_UP)
                .multiply(durationInMonths)
                .divide(nettPrice, 10, RoundingMode.HALF_UP);

        // secondTerm = (( Interest rate / 100 ) * Nett price) / 12
        BigDecimal secondTerm = interestRate.divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP)
                .multiply(nettPrice)
                .divide(MONTHS, 10, RoundingMode.HALF_UP);

        BigDecimal leaseRate = firstTerm.add(secondTerm);
        return leaseRate.setScale(2, RoundingMode.HALF_UP);
    }
}
