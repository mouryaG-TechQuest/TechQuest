package com.example.ems.exception;

/**
 * Bad Request Exception
 * Day 5: Custom exception for 400 errors
 */
public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}
