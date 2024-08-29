package com.ebay.calculator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ebay.calculator.response.CustomizedResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CalculationException.class)
    public ResponseEntity<CustomizedResponse<String>> handleCustomException(CalculationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new CustomizedResponse<>(ex.getMessage(), null, false, ex.getCode())
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomizedResponse<String>> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new CustomizedResponse<>("An unexpected error occurred", null, false, "500")
        );
    }
}
