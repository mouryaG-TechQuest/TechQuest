package com.example.ems.repository;

import com.example.ems.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Employee Repository
 * Day 5: Repository interface for Employee entity
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Find by email
    Optional<Employee> findByEmail(String email);

    // Check if email exists
    boolean existsByEmail(String email);

    // Find by department ID
    List<Employee> findByDepartmentId(Long departmentId);

    // Find by department ID with pagination
    Page<Employee> findByDepartmentId(Long departmentId, Pageable pageable);

    // Find active employees
    List<Employee> findByIsActiveTrue();

    // Find inactive employees
    List<Employee> findByIsActiveFalse();

    // Find by job title
    List<Employee> findByJobTitleContainingIgnoreCase(String jobTitle);

    // Find by salary range
    List<Employee> findBySalaryBetween(Double minSalary, Double maxSalary);

    // Find by salary greater than
    List<Employee> findBySalaryGreaterThan(Double salary);

    // Find by hire date range
    List<Employee> findByHireDateBetween(LocalDate startDate, LocalDate endDate);

    // Search by name (first or last)
    @Query("SELECT e FROM Employee e WHERE " +
           "LOWER(e.firstName) LIKE LOWER(CONCAT('%', :name, '%')) OR " +
           "LOWER(e.lastName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Employee> searchByName(@Param("name") String name);

    // Search by name with pagination
    @Query("SELECT e FROM Employee e WHERE " +
           "LOWER(e.firstName) LIKE LOWER(CONCAT('%', :name, '%')) OR " +
           "LOWER(e.lastName) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<Employee> searchByName(@Param("name") String name, Pageable pageable);

    // Find employees by department name
    @Query("SELECT e FROM Employee e WHERE e.department.name = :departmentName")
    List<Employee> findByDepartmentName(@Param("departmentName") String departmentName);

    // Get average salary by department
    @Query("SELECT AVG(e.salary) FROM Employee e WHERE e.department.id = :departmentId")
    Double getAverageSalaryByDepartment(@Param("departmentId") Long departmentId);

    // Count active employees by department
    @Query("SELECT COUNT(e) FROM Employee e WHERE e.department.id = :departmentId AND e.isActive = true")
    Long countActiveByDepartment(@Param("departmentId") Long departmentId);

    // Find top earners
    @Query("SELECT e FROM Employee e ORDER BY e.salary DESC")
    List<Employee> findTopEarners(Pageable pageable);
}
