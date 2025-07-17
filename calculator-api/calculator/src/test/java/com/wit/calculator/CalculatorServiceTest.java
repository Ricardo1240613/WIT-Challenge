package com.wit.calculator;

import com.wit.calculator.service.CalculatorService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorServiceTest {

    private final CalculatorService calculatorService = new CalculatorService();

    @Test
    void testSum() {
        BigDecimal result = calculatorService.sum(BigDecimal.valueOf(2), BigDecimal.valueOf(3));
        assertEquals(BigDecimal.valueOf(5), result);
    }

    @Test
    void testSubtract() {
        BigDecimal result = calculatorService.subtract(BigDecimal.valueOf(5), BigDecimal.valueOf(2));
        assertEquals(BigDecimal.valueOf(3), result);
    }

    @Test
    void testMultiply() {
        BigDecimal result = calculatorService.multiply(BigDecimal.valueOf(4), BigDecimal.valueOf(3));
        assertEquals(BigDecimal.valueOf(12), result);
    }

    @Test
    void testDivide() {
        BigDecimal result = calculatorService.divide(BigDecimal.valueOf(10), BigDecimal.valueOf(4));
        assertEquals(new BigDecimal("2.5000000000"), result);
    }

    @Test
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> {
            calculatorService.divide(BigDecimal.TEN, BigDecimal.ZERO);
        });
    }
}
