package com.ebay.calculator.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.ebay.calculator.controller.CalculatorController;
import com.ebay.calculator.request.CalculateRequest;
import com.ebay.calculator.request.ChainRequest;
import com.ebay.calculator.service.CalculatorService;
import com.ebay.calculator.util.Operation;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CalculatorController.class)
public class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CalculatorService calculatorService;

    @Autowired
    private ObjectMapper objectMapper;

    private CalculateRequest calculateRequest;
    private ChainRequest chainRequest;

    @BeforeEach
    void setUp() {
        calculateRequest = new CalculateRequest(Optional.of("ADD"), Optional.of(5), Optional.of(10));
        chainRequest = new ChainRequest(Optional.of(5), Optional.of(new String[]{"ADD", "MULTIPLY"}), Optional.of(new Number[]{10,2}));
    }

    @Test
    void testCalculateOperation() throws Exception {
        when(calculatorService.calculateOperation(Operation.ADD, Optional.of(5),  Optional.of(10))).thenReturn(new BigDecimal("15"));

        mockMvc.perform(post("/api/v1/calculator/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(calculateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value(15))
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    void testChainOperations() throws Exception {
        when(calculatorService.chainOperations(new Operation[]{Operation.ADD, Operation.MULTIPLY}, Optional.of(5), Optional.of(new Number[]{10,2}))).thenReturn(new BigDecimal("30"));

        mockMvc.perform(post("/api/v1/calculator/chain")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(chainRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Chaining operations successful"))
                .andExpect(jsonPath("$.success").value(true));
    }

}