package com.ebay.calculator.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebay.calculator.model.CalculateRequest;
import com.ebay.calculator.model.CustomizedResponse;
import com.ebay.calculator.service.CalculatorService;


@RestController
@RequestMapping("/api/v1/calculator")
public class CalculatorController {
    private final CalculatorService calculatorService;
    
    @Autowired
    public CalculatorController(CalculatorService calculatorService){
        this.calculatorService = calculatorService;
    }

    @PostMapping("/calculate")
	public CustomizedResponse calculate(@RequestBody CalculateRequest calculateRequest) {
		Number result = calculatorService.calculateOperation(calculateRequest.getInitialValue(), calculateRequest.getOperation(), calculateRequest.getValue());
		return CustomizedResponse.builder().code("200").message("Calculate operation successful").data(result).success(true).build();
	}

	@PostMapping("/chain")
	public CustomizedResponse chainOperations(@RequestBody CalculateRequest calculateRequest) {
		Number result = calculatorService.chainOperations(calculateRequest.getInitialValue(), calculateRequest.getOperations(), calculateRequest.getValues());
		return CustomizedResponse.builder().code("200").message("Chaining operations successful").data(result).success(true).build();
	}
}
