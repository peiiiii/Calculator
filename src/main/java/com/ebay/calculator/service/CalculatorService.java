package com.ebay.calculator.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ebay.calculator.util.Operation;

@Service
public interface CalculatorService {
    BigDecimal calculateOperation(Operation operation, Optional<Number> num1, Optional<Number> num2);
    BigDecimal chainOperations(Operation[] operations, Optional<Number> initialValue, Optional<Number[]> values);
}