# Calculator Application

This project is a simple, extensible calculator application implemented using Java and Spring Boot. It supports basic arithmetic operations and chaining of multiple operations. The implementation adheres to the Open-Closed Principle, allowing for easy extension of operations without modifying existing code.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [API Endpoints](#api-endpoints)
- [Logging](#logging)
- [Error Handling](#error-handling)

## Features

- **Basic Arithmetic Operations**: Supports addition, subtraction, multiplication, and division.
- **Chaining Operations**: Allows users to chain multiple operations in sequence.
- **Extensibility**: Easily extend the application with new operations without modifying the existing code.
- **IoC with Spring Boot**: Utilizes Spring Boot for dependency injection and IoC.
- **Custom Logging with Spring AOP**: Logs the execution of service methods using Spring AOP.
- **Custom Error Handling**: Gracefully handles invalid operations and inputs with custom exceptions.
restful api

## Technologies Used

- Java 17
- Spring Boot 3.x
- Spring AOP
- SLF4J
- Maven
- Lombok
- JUnit

## API Endpoints

- **POST /api/v1/calculator/calculate**
    - Performs a single operation on two numbers.
    - Request Body:
    ```json
    {
        "operation": "MULTIPLY",
        "num1": 3,
        "num2": 2
    }
    ```
    - Response:
    ```json
    {
        "message": "Calculate operation successful",
        "data": 6,
        "success": true,
        "code": "200"
    }
    ```

- **POST /api/calculator/chain**
    - Performs a sequence of operations starting with an initial value.
    - Request Body:
    ```json
    {
        "initialValue": 3,
        "operations": ["ADD","MULTIPLY"],
        "values": [1,2]
    }
    ```
    - Response:
    ```json
    {
        "message": "Chaining operations successful",
        "data": 8,
        "success": true,
        "code": "200"
    }
    ```

## Logging

The application uses Spring AOP for logging method executions. Logs are configured to show method names and their results.

## Error Handling

Custom exceptions are thrown for invalid operations, null values, division by zero, and other errors. The exceptions include both a message and a code to help identify the error.



