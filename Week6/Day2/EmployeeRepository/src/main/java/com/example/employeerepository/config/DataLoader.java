package com.example.employeerepository.config;

import com.example.employeerepository.entity.Employee;
import com.example.employeerepository.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * Data Loader - Loads test data at startup
 * Day 2: Test data for verifying repository operations
 */
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception {
        // Clear existing data
        employeeRepository.deleteAll();

        // Create test employees
        List<Employee> employees = Arrays.asList(
            new Employee("John", "Doe", "john.doe@example.com", "IT", 75000.0, LocalDate.of(2020, 1, 15)),
            new Employee("Jane", "Smith", "jane.smith@example.com", "HR", 65000.0, LocalDate.of(2019, 6, 20)),
            new Employee("Bob", "Johnson", "bob.johnson@example.com", "IT", 80000.0, LocalDate.of(2018, 3, 10)),
            new Employee("Alice", "Williams", "alice.williams@example.com", "Finance", 70000.0, LocalDate.of(2021, 2, 28)),
            new Employee("Charlie", "Brown", "charlie.brown@example.com", "IT", 72000.0, LocalDate.of(2020, 8, 5))
        );

        // Save all employees
        employeeRepository.saveAll(employees);

        System.out.println("\n=== Test Data Loaded ===");
        System.out.println("✓ Loaded " + employeeRepository.count() + " employees");
        
        // Test repository methods
        testRepositoryMethods();
    }

    private void testRepositoryMethods() {
        System.out.println("\n=== Testing Repository Methods ===");

        // Test findAll
        System.out.println("\n1. All Employees:");
        employeeRepository.findAll().forEach(e -> 
            System.out.println("   - " + e.getFirstName() + " " + e.getLastName() + " (" + e.getDepartment() + ")"));

        // Test findByDepartment
        System.out.println("\n2. IT Department Employees:");
        employeeRepository.findByDepartment("IT").forEach(e -> 
            System.out.println("   - " + e.getFirstName() + " " + e.getLastName()));

        // Test findBySalaryGreaterThan
        System.out.println("\n3. Employees with Salary > 70000:");
        employeeRepository.findBySalaryGreaterThan(70000.0).forEach(e -> 
            System.out.println("   - " + e.getFirstName() + " " + e.getLastName() + " - $" + e.getSalary()));

        // Test searchByName
        System.out.println("\n4. Search by name 'John':");
        employeeRepository.searchByName("John").forEach(e -> 
            System.out.println("   - " + e.getFirstName() + " " + e.getLastName()));

        // Test countByDepartment
        System.out.println("\n5. Count by Department:");
        System.out.println("   - IT: " + employeeRepository.countByDepartment("IT"));
        System.out.println("   - HR: " + employeeRepository.countByDepartment("HR"));
        System.out.println("   - Finance: " + employeeRepository.countByDepartment("Finance"));

        // Test findByEmail
        System.out.println("\n6. Find by Email 'john.doe@example.com':");
        employeeRepository.findByEmail("john.doe@example.com").ifPresent(e -> 
            System.out.println("   - Found: " + e.getFirstName() + " " + e.getLastName()));

        System.out.println("\n=================================\n");
    }
}
