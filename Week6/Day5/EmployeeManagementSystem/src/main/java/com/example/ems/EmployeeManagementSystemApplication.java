package com.example.ems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Employee Management System - Main Application
 * Day 5: Mini Project - Complete REST API with MySQL Integration
 * 
 * Features:
 * - Full CRUD Operations for Employees and Departments
 * - Input Validation
 * - Global Exception Handling
 * - DTOs for Data Transfer
 * - Layered Architecture (Controller -> Service -> Repository)
 */
@SpringBootApplication
public class EmployeeManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagementSystemApplication.class, args);
        
        System.out.println("\n");
        System.out.println("╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║     EMPLOYEE MANAGEMENT SYSTEM - MINI PROJECT             ║");
        System.out.println("║     Week 6 - Day 5: Complete Backend Integration          ║");
        System.out.println("╠═══════════════════════════════════════════════════════════╣");
        System.out.println("║  Server running on: http://localhost:8080                 ║");
        System.out.println("╠═══════════════════════════════════════════════════════════╣");
        System.out.println("║  EMPLOYEE ENDPOINTS:                                      ║");
        System.out.println("║    GET    /api/employees          - Get all employees     ║");
        System.out.println("║    GET    /api/employees/{id}     - Get employee by ID    ║");
        System.out.println("║    POST   /api/employees          - Create employee       ║");
        System.out.println("║    PUT    /api/employees/{id}     - Update employee       ║");
        System.out.println("║    DELETE /api/employees/{id}     - Delete employee       ║");
        System.out.println("╠═══════════════════════════════════════════════════════════╣");
        System.out.println("║  DEPARTMENT ENDPOINTS:                                    ║");
        System.out.println("║    GET    /api/departments        - Get all departments   ║");
        System.out.println("║    GET    /api/departments/{id}   - Get department by ID  ║");
        System.out.println("║    POST   /api/departments        - Create department     ║");
        System.out.println("║    PUT    /api/departments/{id}   - Update department     ║");
        System.out.println("║    DELETE /api/departments/{id}   - Delete department     ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝");
        System.out.println("\n");
    }
}
