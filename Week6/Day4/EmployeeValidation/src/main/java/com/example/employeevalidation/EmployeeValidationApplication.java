package com.example.employeevalidation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot Application Class
 * Day 4: Exception Handling and Validation
 */
@SpringBootApplication
public class EmployeeValidationApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeValidationApplication.class, args);
        System.out.println("=========================================");
        System.out.println("Employee Validation Application Started!");
        System.out.println("Features:");
        System.out.println("  - Global Exception Handling");
        System.out.println("  - Input Validation with Annotations");
        System.out.println("  - Custom Validation Messages");
        System.out.println("=========================================");
    }
}
