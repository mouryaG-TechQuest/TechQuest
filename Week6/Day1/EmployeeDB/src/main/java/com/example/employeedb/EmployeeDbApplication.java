package com.example.employeedb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot Application Class
 * Day 1: MySQL Database Connection Setup
 */
@SpringBootApplication
public class EmployeeDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeDbApplication.class, args);
        System.out.println("=================================");
        System.out.println("Employee DB Application Started!");
        System.out.println("MySQL Database Connected!");
        System.out.println("=================================");
    }
}
