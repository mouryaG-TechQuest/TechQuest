package com.example.ems.service;

import com.example.ems.entity.Department;

import java.util.List;

/**
 * Department Service Interface
 * Day 5: Business logic interface for Department operations
 */
public interface DepartmentService {

    // Create
    Department createDepartment(Department department);

    // Read
    List<Department> getAllDepartments();
    Department getDepartmentById(Long id);
    Department getDepartmentByName(String name);
    List<Department> searchDepartmentsByName(String name);
    List<Department> getDepartmentsByLocation(String location);

    // Update
    Department updateDepartment(Long id, Department department);

    // Delete
    void deleteDepartment(Long id);

    // Utility
    boolean existsById(Long id);
    boolean existsByName(String name);
    long countDepartments();
    long countEmployeesInDepartment(Long departmentId);
}
