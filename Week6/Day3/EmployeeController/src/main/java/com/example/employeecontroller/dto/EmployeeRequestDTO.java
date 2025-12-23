package com.example.employeecontroller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Employee Request DTO
 * Day 3: Data Transfer Object for incoming requests
 * Used to receive data from client for creating/updating employees
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequestDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String department;
    private Double salary;
    private LocalDate hireDate;
    private Boolean isActive;
}
