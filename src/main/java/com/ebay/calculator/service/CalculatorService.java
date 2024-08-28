package com.ebay.calculator.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public interface CalculatorService {
    BigDecimal calculateOperation(BigDecimal num1, Optional<String> operation, Optional<BigDecimal> num2);
    BigDecimal chainOperations(BigDecimal initialValue, Optional<String[]> operations, Optional<BigDecimal[]> values);
}