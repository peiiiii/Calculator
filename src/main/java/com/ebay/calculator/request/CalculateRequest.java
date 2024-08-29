package com.ebay.calculator.request;

import java.util.Optional;

import lombok.Data;

@Data
public class CalculateRequest {
    private Optional<String> operation;
    private Optional<Number> num1;
    private Optional<Number> num2;
}