package com.ebay.calculator.request;

import java.util.Arrays;
import java.util.Optional;

import com.ebay.calculator.exception.CalculationException;
import com.ebay.calculator.util.Operation;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChainRequest {
    private Optional<Number> initialValue;
    private Optional<String[]> operations;
    private Optional<Number[]> values;

    public Operation[] getOperations() {
        if(!this.operations.isPresent()){
            throw new CalculationException("Operation cannot be null or empty", "400"); 
        } 
        return Arrays.stream(this.operations.get())
                     .map(Operation::fromString)
                     .toArray(Operation[]::new);
    }
}
