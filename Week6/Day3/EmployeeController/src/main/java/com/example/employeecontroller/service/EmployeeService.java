package com.example.employeecontroller.service;

import com.example.employeecontroller.entity.Employee;

import java.util.List;
import java.util.Optional;

/**
 * Employee Service Interface
 * Day 3: Business logic interface
 */
public interface EmployeeService {

    Employee createEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Optional<Employee> getEmployeeById(Long id);
    Optional<Employee> getEmployeeByEmail(String email);
    List<Employee> getEmployeesByDepartment(String department);
    List<Employee> getActiveEmployees();
    List<Employee> searchEmployeesByName(String name);
    Employee updateEmployee(Long id, Employee employee);
    void deleteEmployee(Long id);
    boolean existsById(Long id);
    boolean existsByEmail(String email);
    long countEmployees();
}
