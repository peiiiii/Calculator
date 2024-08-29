package com.ebay.calculator.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.ebay.calculator.exception.CalculationException;
import com.ebay.calculator.service.impl.Calculator;
import com.ebay.calculator.util.Operation;

public class CalculatorTest {
    @InjectMocks
    private Calculator calculator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalculateAddition() {
        BigDecimal result = calculator.calculateOperation(Operation.ADD, Optional.of(5), Optional.of(3));
        assertEquals(BigDecimal.valueOf(8.0), result);
    }

    @Test
    void testCalculateSubtraction() {
        BigDecimal result = calculator.calculateOperation(Operation.SUBTRACT, Optional.of(5), Optional.of(3));
        assertEquals(BigDecimal.valueOf(2.0), result);
    }

    @Test
    void testCalculateMultiplication() {
        BigDecimal result = calculator.calculateOperation(Operation.MULTIPLY, Optional.of(5), Optional.of(3));
        assertEquals(BigDecimal.valueOf(15.0), result.setScale(1, RoundingMode.HALF_UP));
    }

    @Test
    void testCalculateDivision() {
        BigDecimal result = calculator.calculateOperation(Operation.DIVIDE, Optional.of(5), Optional.of(3));
        assertEquals(BigDecimal.valueOf(1.667), result);
    }

    @Test
    void testCalculateDivisionByZero() {
        Exception exception = assertThrows(CalculationException.class, () -> {
            calculator.calculateOperation(Operation.DIVIDE, Optional.of(5), Optional.of(0));
        });
        assertEquals("Division by zero is not allowed", exception.getMessage());
    }

    // @Test
    // void testCalculateInvalidOperation() {
    //     Exception exception = assertThrows(CalculationException.class, () -> {
    //         calculator.calculateOperation(null, Optional.of(5), Optional.of(3));
    //     });
    //     assertEquals("Operation cannot be null or empty", exception.getMessage());
    // }

    @Test
    void testCalculateInvalidNumber() {
        Exception exception = assertThrows(CalculationException.class, () -> {
            calculator.calculateOperation(Operation.ADD, Optional.empty(), Optional.of(3));
        });
        assertEquals("Numbers cannot be null", exception.getMessage());
    }

    @Test
    void testChainOperations() {
        BigDecimal result = calculator.chainOperations(
            new Operation[]{Operation.ADD, Operation.MULTIPLY},
            Optional.of(5), 
            Optional.of(new Number[]{3,2}));
        BigDecimal bd = new BigDecimal("16.00");
        assertTrue(bd.equals(result));
    }

    @Test
    void testChainOperationsWithOperationsWithMismatchedLengths() {
        Exception exception = assertThrows(CalculationException.class, () -> {
            calculator.chainOperations(
                new Operation[]{Operation.ADD, Operation.MULTIPLY, Operation.ADD},
                Optional.of(5), 
                Optional.of(new Number[]{3,2}));
        });
        assertEquals("Number of operations must match number of values", exception.getMessage());
    }

}
