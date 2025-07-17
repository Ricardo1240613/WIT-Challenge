package com.wit.calculator.service;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorServiceTest {

    private final CalculatorService calculator = new CalculatorService();

    @Test
    void testSum() {
        BigDecimal result = calculator.sum(new BigDecimal("2"), new BigDecimal("3"));
        assertEquals(new BigDecimal("5"), result);
    }

    @Test
    void testSubtract() {
        BigDecimal result = calculator.subtract(new BigDecimal("10"), new BigDecimal("4"));
        assertEquals(new BigDecimal("6"), result);
    }

    @Test
    void testMultiply() {
        BigDecimal result = calculator.multiply(new BigDecimal("3"), new BigDecimal("4"));
        assertEquals(new BigDecimal("12"), result);
    }

    @Test
    void testDivide() {
        BigDecimal result = calculator.divide(new BigDecimal("10"), new BigDecimal("4"));
        assertEquals(new BigDecimal("2.5"), result.stripTrailingZeros());
    }

    @Test
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () ->
            calculator.divide(new BigDecimal("10"), BigDecimal.ZERO)
        );
    }
}
