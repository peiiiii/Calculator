package com.ebay.calculator.service.impl;

import java.math.BigDecimal;

import com.ebay.calculator.exception.CalculationException;
import com.ebay.calculator.service.OperationStrategy;

public class DivisionStrategy implements OperationStrategy {
    @Override
    public BigDecimal apply(BigDecimal num1, BigDecimal num2) {
        if (num2.compareTo(BigDecimal.ZERO) == 0) {
            throw new CalculationException("Division by zero is not allowed", "400");
        }
        return num1.divide(num2);
    }
}