package com.ebay.calculator.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CustomizedResponse<T> {
    private String message;
    private T data;
    private boolean success;
    private String code;
}
