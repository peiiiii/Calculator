package com.ebay.calculator.model;

import java.math.BigDecimal;
import java.util.Optional;

import lombok.Data;

@Data
public class CalculateRequest {
    private BigDecimal initialValue;
    private Optional<String> operation = Optional.empty();
    private Optional<BigDecimal> value = Optional.empty();
    private Optional<String[]> operations = Optional.empty();
    private Optional<BigDecimal[]> values = Optional.empty();
}