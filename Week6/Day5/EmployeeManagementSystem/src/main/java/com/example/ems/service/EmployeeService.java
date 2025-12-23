package com.example.ems.service;

import com.example.ems.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Employee Service Interface
 * Day 5: Business logic interface for Employee operations
 */
public interface EmployeeService {

    // Create
    Employee createEmployee(Employee employee, Long departmentId);

    // Read
    List<Employee> getAllEmployees();
    Page<Employee> getAllEmployees(Pageable pageable);
    Employee getEmployeeById(Long id);
    Employee getEmployeeByEmail(String email);
    List<Employee> getEmployeesByDepartment(Long departmentId);
    Page<Employee> getEmployeesByDepartment(Long departmentId, Pageable pageable);
    List<Employee> getActiveEmployees();
    List<Employee> getInactiveEmployees();
    List<Employee> searchEmployeesByName(String name);
    Page<Employee> searchEmployeesByName(String name, Pageable pageable);
    List<Employee> getEmployeesBySalaryRange(Double minSalary, Double maxSalary);
    List<Employee> getEmployeesByJobTitle(String jobTitle);

    // Update
    Employee updateEmployee(Long id, Employee employee, Long departmentId);
    Employee toggleEmployeeStatus(Long id);
    Employee changeDepartment(Long employeeId, Long newDepartmentId);

    // Delete
    void deleteEmployee(Long id);

    // Utility
    boolean existsById(Long id);
    boolean existsByEmail(String email);
    long countEmployees();
    long countActiveEmployees();
    Double getAverageSalaryByDepartment(Long departmentId);
}
