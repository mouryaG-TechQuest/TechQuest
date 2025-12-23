package com.example.employeecontroller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot Application Class
 * Day 3: Controller Layer and DTOs
 */
@SpringBootApplication
public class EmployeeControllerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeControllerApplication.class, args);
        System.out.println("=====================================");
        System.out.println("Employee Controller Application Started!");
        System.out.println("REST API Endpoints Available:");
        System.out.println("  GET    /api/employees");
        System.out.println("  GET    /api/employees/{id}");
        System.out.println("  POST   /api/employees");
        System.out.println("  PUT    /api/employees/{id}");
        System.out.println("  DELETE /api/employees/{id}");
        System.out.println("=====================================");
    }
}
