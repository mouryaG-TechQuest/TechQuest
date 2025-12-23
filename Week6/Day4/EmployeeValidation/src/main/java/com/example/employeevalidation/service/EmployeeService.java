package com.example.employeevalidation.service;

import com.example.employeevalidation.entity.Employee;

import java.util.List;

/**
 * Employee Service Interface
 * Day 4: Business logic interface
 */
public interface EmployeeService {

    Employee createEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Long id);
    Employee getEmployeeByEmail(String email);
    List<Employee> getEmployeesByDepartment(String department);
    List<Employee> getActiveEmployees();
    List<Employee> searchEmployeesByName(String name);
    Employee updateEmployee(Long id, Employee employee);
    void deleteEmployee(Long id);
    long countEmployees();
}
