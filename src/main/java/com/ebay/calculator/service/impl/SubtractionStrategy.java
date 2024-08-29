package com.ebay.calculator.service.impl;

import java.math.BigDecimal;

import com.ebay.calculator.service.OperationStrategy;

public class SubtractionStrategy implements OperationStrategy {
    @Override
    public BigDecimal apply(BigDecimal num1, BigDecimal num2) {
        return num1.subtract(num2);
    }
}
