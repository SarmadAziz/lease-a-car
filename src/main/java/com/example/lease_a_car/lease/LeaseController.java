package com.example.lease_a_car.lease;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1")
public class LeaseController {

    private final LeaseCalculatorService leaseCalculatorService;

    @Autowired
    public LeaseController(LeaseCalculatorService leaseCalculatorService) {
        this.leaseCalculatorService = leaseCalculatorService;
    }

    @GetMapping("/calculateLeaseRate")
    public ResponseEntity<BigDecimal> calculateLeaseRate(@Valid @RequestBody LeaseRateCalculationInput input) {
        BigDecimal leaseRate = leaseCalculatorService.calculateLeaseRate(input);
        return ResponseEntity.ok(leaseRate);
    }
}
