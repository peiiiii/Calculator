package com.ebay.calculator.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebay.calculator.request.CalculateRequest;
import com.ebay.calculator.request.ChainRequest;
import com.ebay.calculator.response.CustomizedResponse;
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
		BigDecimal result = calculatorService.calculateOperation(calculateRequest.getOperation(), calculateRequest.getNum1(), calculateRequest.getNum2());
		System.out.println("result : " + result);
		return CustomizedResponse.builder().code("200").message("Calculate operation successful").data(result).success(true).build();
	}

	@PostMapping("/chain")
	public CustomizedResponse chainOperations(@RequestBody ChainRequest chainRequest) {
		BigDecimal result = calculatorService.chainOperations(chainRequest.getOperations(), chainRequest.getInitialValue(), chainRequest.getValues());
		return CustomizedResponse.builder().code("200").message("Chaining operations successful").data(result).success(true).build();
	}
}
