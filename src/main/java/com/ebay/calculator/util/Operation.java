package com.ebay.calculator.util;

import java.util.Optional;

import com.ebay.calculator.exception.CalculationException;

public enum Operation {
    ADD,
	SUBTRACT,
	MULTIPLY,
	DIVIDE;

	public static Operation fromString(String operation) {
        try {
            return Operation.valueOf(operation.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new CalculationException("Unsupported operation: "+ operation, "400");
        }
    }
}
