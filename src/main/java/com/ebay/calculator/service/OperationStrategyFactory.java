package com.ebay.calculator.service;

import com.ebay.calculator.exception.CalculationException;
import com.ebay.calculator.service.impl.AdditionStrategy;
import com.ebay.calculator.service.impl.DivisionStrategy;
import com.ebay.calculator.service.impl.MultiplicationStrategy;
import com.ebay.calculator.service.impl.SubtractionStrategy;
import com.ebay.calculator.util.Operation;

public class OperationStrategyFactory {
    public static OperationStrategy getStrategy(String operation) {
        switch (Operation.valueOf(operation.toUpperCase())) {
            case ADD:
                return new AdditionStrategy();
            case SUBTRACT:
                return new SubtractionStrategy();
            case MULTIPLY:
                return new MultiplicationStrategy();
            case DIVIDE:
                return new DivisionStrategy();
            default:
                throw new CalculationException("Unsupported operation: {}"+ operation, "400");
        }
    }
}
