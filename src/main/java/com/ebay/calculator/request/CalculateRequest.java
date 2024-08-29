package com.ebay.calculator.request;

import java.util.Optional;

import com.ebay.calculator.exception.CalculationException;
import com.ebay.calculator.util.Operation;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CalculateRequest {
    private Optional<String> operation;
    private Optional<Number> num1;
    private Optional<Number> num2;

    public Operation getOperation() {
        if(!this.operation.isPresent()){
            throw new CalculationException("Operation cannot be null or empty", "400"); 
        } 
        return Operation.fromString(this.operation.get());
    }
}