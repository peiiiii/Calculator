package com.ebay.calculator.service.impl;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ebay.calculator.exception.CalculationException;
import com.ebay.calculator.service.CalculatorService;
import com.ebay.calculator.service.OperationStrategy;
import com.ebay.calculator.service.OperationStrategyFactory;

@Service
public class Calculator implements CalculatorService {

    @Override
    public BigDecimal calculateOperation(Optional<String> operation, Optional<Number> num1, Optional<Number> num2) {
        if (num1 == null || !num1.isPresent() ||num2 == null || !num2.isPresent()) {
            throw new CalculationException("Numbers cannot be null", "400");
        }
        if (operation == null || !operation.isPresent()) {
            throw new CalculationException("Operation cannot be null or empty", "400");
        }
        BigDecimal bdNum1 = BigDecimal.valueOf(num1.get().doubleValue());
        BigDecimal bdNum2 = BigDecimal.valueOf(num2.get().doubleValue());
        OperationStrategy strategy = OperationStrategyFactory.getStrategy(operation.get());
        return strategy.apply(bdNum1, bdNum2);
    }

    @Override
    public BigDecimal chainOperations(Optional<String[]> operations, Optional<Number> initialValue, Optional<Number[]> values) {
        if (initialValue == null || !initialValue.isPresent()) {
            throw new CalculationException("Initial value cannot be null", "400");
        }
        if (!operations.isPresent() || !values.isPresent()) {
            throw new CalculationException("Operations and values must be provided", "400");
        }

        String[] ops = operations.get();
        Number[] vals = values.get();

        if (ops.length != vals.length) {
            throw new CalculationException("Number of operations must match number of values", "400");
        }

        BigDecimal result = BigDecimal.valueOf(initialValue.get().doubleValue());
        for (int i = 0; i < ops.length; i++) {
            if (vals[i] == null) {
                throw new CalculationException("Value cannot be null", "400");
            }

            BigDecimal bdValue = BigDecimal.valueOf(vals[i].doubleValue());
            OperationStrategy strategy = OperationStrategyFactory.getStrategy(ops[i]);
            result = strategy.apply(result, bdValue);
        }
        return result;
    }
}
