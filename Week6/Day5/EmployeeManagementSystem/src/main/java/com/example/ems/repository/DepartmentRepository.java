package com.example.ems.repository;

import com.example.ems.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Department Repository
 * Day 5: Repository interface for Department entity
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    // Find by name
    Optional<Department> findByName(String name);

    // Find by name (case insensitive)
    Optional<Department> findByNameIgnoreCase(String name);

    // Check if name exists
    boolean existsByName(String name);

    // Check if name exists (case insensitive)
    boolean existsByNameIgnoreCase(String name);

    // Find by location
    List<Department> findByLocation(String location);

    // Search by name containing
    List<Department> findByNameContainingIgnoreCase(String name);

    // Get department with employee count
    @Query("SELECT d, COUNT(e) FROM Department d LEFT JOIN d.employees e GROUP BY d")
    List<Object[]> findAllWithEmployeeCount();

    // Count employees in department
    @Query("SELECT COUNT(e) FROM Employee e WHERE e.department.id = :departmentId")
    Long countEmployeesByDepartmentId(@Param("departmentId") Long departmentId);
}
