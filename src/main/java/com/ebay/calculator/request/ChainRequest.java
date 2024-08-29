package com.ebay.calculator.request;

import java.util.Optional;

import lombok.Data;

@Data
public class ChainRequest {
    private Optional<Number> initialValue;
    private Optional<String[]> operations;
    private Optional<Number[]> values;
}
