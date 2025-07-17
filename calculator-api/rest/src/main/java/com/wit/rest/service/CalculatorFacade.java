package com.wit.rest.service;

import com.wit.calculator.service.CalculatorService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalculatorFacade {

    private final CalculatorService calculatorService = new CalculatorService();

    public BigDecimal sum(BigDecimal a, BigDecimal b) {
        return calculatorService.sum(a, b);
    }

    public BigDecimal subtract(BigDecimal a, BigDecimal b) {
        return calculatorService.subtract(a, b);
    }

    public BigDecimal multiply(BigDecimal a, BigDecimal b) {
        return calculatorService.multiply(a, b);
    }

    public BigDecimal divide(BigDecimal a, BigDecimal b) {
        return calculatorService.divide(a, b);
    }
}
