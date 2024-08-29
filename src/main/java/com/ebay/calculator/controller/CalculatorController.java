package com.ebay.calculator.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<CustomizedResponse> calculate(@RequestBody CalculateRequest calculateRequest) {
		BigDecimal result = calculatorService.calculateOperation(calculateRequest.getOperation(), calculateRequest.getNum1(), calculateRequest.getNum2());
		return new ResponseEntity<>(CustomizedResponse.builder().code("200").message("Calculate operation successful").data(result).success(true).build(), HttpStatus.OK);
	}

	@PostMapping("/chain")
	public ResponseEntity<CustomizedResponse> chainOperations(@RequestBody ChainRequest chainRequest) {
		BigDecimal result = calculatorService.chainOperations(chainRequest.getOperations(), chainRequest.getInitialValue(), chainRequest.getValues());
		return new ResponseEntity<>(CustomizedResponse.builder().code("200").message("Chaining operations successful").data(result).success(true).build(), HttpStatus.OK);
	}
}
