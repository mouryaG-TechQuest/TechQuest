package com.example.employeevalidation.exception;

/**
 * Custom Exception - Bad Request
 * Day 4: Custom exception for invalid request data
 */
public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}
