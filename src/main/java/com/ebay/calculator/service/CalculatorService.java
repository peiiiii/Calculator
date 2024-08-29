package com.ebay.calculator.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public interface CalculatorService {
    BigDecimal calculateOperation(Optional<String> operation, Optional<Number> num1, Optional<Number> num2);
    BigDecimal chainOperations(Optional<String[]> operations, Optional<Number> initialValue, Optional<Number[]> values);
}