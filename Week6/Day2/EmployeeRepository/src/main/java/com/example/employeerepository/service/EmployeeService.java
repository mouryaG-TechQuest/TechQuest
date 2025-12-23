package com.example.employeerepository.service;

import com.example.employeerepository.entity.Employee;

import java.util.List;
import java.util.Optional;

/**
 * Employee Service Interface
 * Day 2: Defines business logic methods for Employee operations
 */
public interface EmployeeService {

    // Create
    Employee createEmployee(Employee employee);

    // Read
    List<Employee> getAllEmployees();
    Optional<Employee> getEmployeeById(Long id);
    Optional<Employee> getEmployeeByEmail(String email);
    List<Employee> getEmployeesByDepartment(String department);
    List<Employee> getActiveEmployees();
    List<Employee> searchEmployeesByName(String name);
    List<Employee> getEmployeesBySalaryRange(Double minSalary, Double maxSalary);

    // Update
    Employee updateEmployee(Long id, Employee employee);
    Employee toggleEmployeeStatus(Long id);

    // Delete
    void deleteEmployee(Long id);
    void deleteAllEmployees();

    // Utility
    boolean existsById(Long id);
    boolean existsByEmail(String email);
    long countEmployees();
    long countEmployeesByDepartment(String department);
}
