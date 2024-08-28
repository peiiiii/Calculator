package com.ebay.calculator.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // Around advice that logs before and after method execution
    @Around("execution(* com.ebay.calculator.service.*.*(..))")
    public void logAroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("Executing: {} with arguments: {}", joinPoint.getSignature().getName(), joinPoint.getArgs());
        logger.info("Method {} executed successfully", joinPoint.getSignature().getName());
        logger.info("Method returned: {}", joinPoint.proceed());
    }

    // Log any exceptions thrown by methods in the service package
    @AfterThrowing(pointcut = "execution(* com.ebay.calculator.service.*.*(..))", throwing = "e")
    public void logAfterThrowingMethod(Exception e) {
        logger.error("An exception occurred: {} from {}",  e.getMessage(), e);
    }
}
