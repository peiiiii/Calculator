package com.ebay.calculator.service.impl;

import java.math.BigDecimal;

import com.ebay.calculator.service.OperationStrategy;

public class AdditionStrategy implements OperationStrategy  {

    @Override
    public BigDecimal apply(BigDecimal num1, BigDecimal num2) {
        return num1.add(num2);
    }
    
}
