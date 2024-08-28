package com.ebay.calculator.exception;

import lombok.Getter;

@Getter
public class CalculationException extends RuntimeException {
    private final String code;

    public CalculationException(String message, String code) {
        super(message);
        this.code = code;
    }
}