package com.example.employeerepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot Application Class
 * Day 2: Repository and Service Layer
 */
@SpringBootApplication
public class EmployeeRepositoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeRepositoryApplication.class, args);
        System.out.println("=================================");
        System.out.println("Employee Repository App Started!");
        System.out.println("Repository & Service Layer Ready!");
        System.out.println("=================================");
    }
}
