package com.example.employeerepository.repository;

import com.example.employeerepository.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Employee Repository Interface
 * Day 2: Extends JpaRepository for CRUD operations
 * 
 * JpaRepository provides:
 * - save(entity) - Insert or update
 * - findById(id) - Find by primary key
 * - findAll() - Get all records
 * - deleteById(id) - Delete by primary key
 * - count() - Count total records
 * - existsById(id) - Check if exists
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Custom query method - Find by email
    Optional<Employee> findByEmail(String email);

    // Find by department
    List<Employee> findByDepartment(String department);

    // Find by first name containing (like search)
    List<Employee> findByFirstNameContainingIgnoreCase(String firstName);

    // Find by last name containing
    List<Employee> findByLastNameContainingIgnoreCase(String lastName);

    // Find active employees
    List<Employee> findByIsActiveTrue();

    // Find inactive employees
    List<Employee> findByIsActiveFalse();

    // Find employees with salary greater than
    List<Employee> findBySalaryGreaterThan(Double salary);

    // Find employees with salary between range
    List<Employee> findBySalaryBetween(Double minSalary, Double maxSalary);

    // Find employees by department and active status
    List<Employee> findByDepartmentAndIsActive(String department, Boolean isActive);

    // Custom JPQL query - Find by name (first or last)
    @Query("SELECT e FROM Employee e WHERE LOWER(e.firstName) LIKE LOWER(CONCAT('%', :name, '%')) OR LOWER(e.lastName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Employee> searchByName(@Param("name") String name);

    // Custom JPQL query - Get employees by department with salary order
    @Query("SELECT e FROM Employee e WHERE e.department = :department ORDER BY e.salary DESC")
    List<Employee> findByDepartmentOrderBySalary(@Param("department") String department);

    // Custom native SQL query - Count employees by department
    @Query(value = "SELECT COUNT(*) FROM employees WHERE department = :department", nativeQuery = true)
    Long countByDepartment(@Param("department") String department);

    // Check if email exists
    boolean existsByEmail(String email);
}
