package com.ebay.calculator.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.ebay.calculator.exception.CalculationException;
import com.ebay.calculator.service.OperationStrategy;

public class DivisionStrategy implements OperationStrategy {
    private final int SCALE = 3;
    @Override
    public BigDecimal apply(BigDecimal num1, BigDecimal num2) {
        if (num2.compareTo(BigDecimal.ZERO) == 0) {
            throw new CalculationException("Division by zero is not allowed", "400");
        }
        return num1.divide(num2, SCALE, RoundingMode.HALF_UP);
    }
}