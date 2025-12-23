package com.example.employeecontroller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * API Response Wrapper DTO
 * Day 3: Standard response format for all API responses
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseDTO<T> {

    private boolean success;
    private String message;
    private T data;
    private LocalDateTime timestamp;
    private int statusCode;

    // Success response with data
    public static <T> ApiResponseDTO<T> success(String message, T data) {
        return new ApiResponseDTO<>(true, message, data, LocalDateTime.now(), 200);
    }

    // Success response without data
    public static <T> ApiResponseDTO<T> success(String message) {
        return new ApiResponseDTO<>(true, message, null, LocalDateTime.now(), 200);
    }

    // Error response
    public static <T> ApiResponseDTO<T> error(String message, int statusCode) {
        return new ApiResponseDTO<>(false, message, null, LocalDateTime.now(), statusCode);
    }

    // Created response
    public static <T> ApiResponseDTO<T> created(String message, T data) {
        return new ApiResponseDTO<>(true, message, data, LocalDateTime.now(), 201);
    }
}
