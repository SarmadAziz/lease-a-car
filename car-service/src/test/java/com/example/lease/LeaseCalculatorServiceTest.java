package com.example.lease;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class LeaseCalculatorServiceTest {

    @InjectMocks
    private LeaseCalculatorService leaseCalculatorService;

    @Test
    void testCalculateLeaseRate() {
        // Given
        LeaseRateCalculationInput input = LeaseRateCalculationInput.builder()
                .kmPerYear(45000)
                .durationInMonths(60)
                .interestRate(BigDecimal.valueOf(4.5))
                .nettPrice(BigDecimal.valueOf(63000))
                .build();

        // When
        BigDecimal leaseRate = leaseCalculatorService.calculateLeaseRate(input);

        // Then
        BigDecimal expectedLeaseRate = BigDecimal.valueOf(239.82);
        assertEquals(expectedLeaseRate, leaseRate.setScale(2, RoundingMode.HALF_UP));
    }
}