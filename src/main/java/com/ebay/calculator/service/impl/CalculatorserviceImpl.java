package com.ebay.calculator.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ebay.calculator.exception.CalculationException;
import com.ebay.calculator.model.Operation;
import com.ebay.calculator.service.CalculatorService;

@Service
public class CalculatorserviceImpl implements CalculatorService {

    @Override
    public BigDecimal calculateOperation(BigDecimal num1, Optional<String> operation, Optional<BigDecimal> num2) {
        if (num1 == null) {
            throw new CalculationException("Initial number cannot be null", "400");
        }
        if (operation == null || !operation.isPresent()) {
            throw new CalculationException("Operation cannot be null or empty", "400");
        }
        if (num2 == null || !num2.isPresent()) {
            throw new CalculationException("Second number cannot be null", "400");
        }
        

        try {
            switch (Operation.valueOf(operation.get().toUpperCase())) {
                case ADD:
                    return num1.add(num2.get());
                case SUBTRACT:
                    return num1.subtract(num2.get());
                case MULTIPLY:
                    return num1.multiply(num2.get());
                case DIVIDE:
                    if (num2.get().compareTo(BigDecimal.ZERO) == 0) {
                        throw new CalculationException("Division by zero", "400");
                    }
                    return num1.divide(num2.get(), MathContext.DECIMAL128);
                default:
                    throw new CalculationException("Invalid operation: " + operation.get(), "400");
            }
        } catch (CalculationException e) {
            // Handle overflow or arithmetic issues
            throw new CalculationException("Arithmetic error: " + e.getMessage(), "400");
        }
    }

    @Override
    public BigDecimal chainOperations(BigDecimal initialValue, Optional<String[]> operations,Optional<BigDecimal[]> values) {
        if (initialValue == null) {
            throw new CalculationException("Initial value cannot be null", "400");
        }

        if (!operations.isPresent() || !values.isPresent()) {
            throw new CalculationException("Operations and values must be provided", "400");
        }

        String[] ops = operations.get();
        BigDecimal[] vals = values.get();

        if (ops.length != vals.length) {
            throw new CalculationException("Number of operations must match number of values", "400");
        }

        BigDecimal result = initialValue;
        for (int i = 0; i < ops.length; i++) {
            result = calculateOperation(result, Optional.of(ops[i]), Optional.of(vals[i]));
        }
        return result;
    }


}
