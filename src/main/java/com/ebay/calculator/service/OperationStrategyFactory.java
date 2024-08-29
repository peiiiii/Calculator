package com.ebay.calculator.service;

import java.util.HashMap;

import com.ebay.calculator.exception.CalculationException;
import com.ebay.calculator.service.impl.AdditionStrategy;
import com.ebay.calculator.service.impl.DivisionStrategy;
import com.ebay.calculator.service.impl.MultiplicationStrategy;
import com.ebay.calculator.service.impl.SubtractionStrategy;
import com.ebay.calculator.util.Operation;

public class OperationStrategyFactory {
    public static OperationStrategy getStrategy(Operation operation) {
        HashMap<Operation, OperationStrategy> map = new HashMap<>();
        map.put(Operation.ADD, new AdditionStrategy());
        map.put(Operation.SUBTRACT, new SubtractionStrategy());
        map.put(Operation.MULTIPLY, new MultiplicationStrategy());
        map.put(Operation.DIVIDE, new DivisionStrategy());

        if(map.containsKey(operation)){
            return map.get(operation);
        }else{
            throw new CalculationException("Unsupported operation: {}"+ operation, "400");
        }
    }
}
