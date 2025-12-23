package com.example.employeerepository;

import com.example.employeerepository.entity.Employee;
import com.example.employeerepository.repository.EmployeeRepository;
import com.example.employeerepository.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Repository Tests
 * Day 2: Unit tests for verifying DB operations
 */
@SpringBootTest
class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeRepository.deleteAll();
    }

    @Test
    void testCreateEmployee() {
        Employee employee = new Employee("Test", "User", "test@example.com", "IT", 50000.0, LocalDate.now());
        Employee saved = employeeRepository.save(employee);
        
        assertNotNull(saved.getId());
        assertEquals("Test", saved.getFirstName());
        System.out.println("✓ Create Employee Test Passed");
    }

    @Test
    void testFindById() {
        Employee employee = new Employee("Find", "ById", "findbyid@example.com", "HR", 60000.0, LocalDate.now());
        Employee saved = employeeRepository.save(employee);
        
        Employee found = employeeRepository.findById(saved.getId()).orElse(null);
        
        assertNotNull(found);
        assertEquals("Find", found.getFirstName());
        System.out.println("✓ Find By ID Test Passed");
    }

    @Test
    void testFindByEmail() {
        Employee employee = new Employee("Email", "Test", "unique@example.com", "Finance", 55000.0, LocalDate.now());
        employeeRepository.save(employee);
        
        Employee found = employeeRepository.findByEmail("unique@example.com").orElse(null);
        
        assertNotNull(found);
        assertEquals("Email", found.getFirstName());
        System.out.println("✓ Find By Email Test Passed");
    }

    @Test
    void testFindByDepartment() {
        employeeRepository.save(new Employee("IT1", "User", "it1@example.com", "IT", 50000.0, LocalDate.now()));
        employeeRepository.save(new Employee("IT2", "User", "it2@example.com", "IT", 55000.0, LocalDate.now()));
        employeeRepository.save(new Employee("HR1", "User", "hr1@example.com", "HR", 45000.0, LocalDate.now()));
        
        List<Employee> itEmployees = employeeRepository.findByDepartment("IT");
        
        assertEquals(2, itEmployees.size());
        System.out.println("✓ Find By Department Test Passed");
    }

    @Test
    void testFindBySalaryGreaterThan() {
        employeeRepository.save(new Employee("High", "Salary", "high@example.com", "IT", 80000.0, LocalDate.now()));
        employeeRepository.save(new Employee("Low", "Salary", "low@example.com", "IT", 40000.0, LocalDate.now()));
        
        List<Employee> highEarners = employeeRepository.findBySalaryGreaterThan(50000.0);
        
        assertEquals(1, highEarners.size());
        assertEquals("High", highEarners.get(0).getFirstName());
        System.out.println("✓ Find By Salary Greater Than Test Passed");
    }

    @Test
    void testUpdateEmployee() {
        Employee employee = new Employee("Update", "Test", "update@example.com", "IT", 50000.0, LocalDate.now());
        Employee saved = employeeRepository.save(employee);
        
        saved.setSalary(60000.0);
        saved.setDepartment("HR");
        Employee updated = employeeRepository.save(saved);
        
        assertEquals(60000.0, updated.getSalary());
        assertEquals("HR", updated.getDepartment());
        System.out.println("✓ Update Employee Test Passed");
    }

    @Test
    void testDeleteEmployee() {
        Employee employee = new Employee("Delete", "Test", "delete@example.com", "IT", 50000.0, LocalDate.now());
        Employee saved = employeeRepository.save(employee);
        Long id = saved.getId();
        
        employeeRepository.deleteById(id);
        
        assertFalse(employeeRepository.existsById(id));
        System.out.println("✓ Delete Employee Test Passed");
    }

    @Test
    void testSearchByName() {
        employeeRepository.save(new Employee("Johnson", "Smith", "johnson@example.com", "IT", 50000.0, LocalDate.now()));
        employeeRepository.save(new Employee("Mike", "Johnson", "mike@example.com", "HR", 55000.0, LocalDate.now()));
        
        List<Employee> results = employeeRepository.searchByName("Johnson");
        
        assertEquals(2, results.size());
        System.out.println("✓ Search By Name Test Passed");
    }

    @Test
    void testExistsByEmail() {
        employeeRepository.save(new Employee("Exists", "Test", "exists@example.com", "IT", 50000.0, LocalDate.now()));
        
        assertTrue(employeeRepository.existsByEmail("exists@example.com"));
        assertFalse(employeeRepository.existsByEmail("notexists@example.com"));
        System.out.println("✓ Exists By Email Test Passed");
    }

    @Test
    void testServiceCreateEmployee() {
        Employee employee = new Employee("Service", "Test", "service@example.com", "IT", 50000.0, LocalDate.now());
        Employee created = employeeService.createEmployee(employee);
        
        assertNotNull(created.getId());
        assertEquals("Service", created.getFirstName());
        System.out.println("✓ Service Create Employee Test Passed");
    }

    @Test
    void testServiceUpdateEmployee() {
        Employee employee = new Employee("ServiceUpdate", "Test", "serviceupdate@example.com", "IT", 50000.0, LocalDate.now());
        Employee saved = employeeService.createEmployee(employee);
        
        saved.setSalary(70000.0);
        Employee updated = employeeService.updateEmployee(saved.getId(), saved);
        
        assertEquals(70000.0, updated.getSalary());
        System.out.println("✓ Service Update Employee Test Passed");
    }
}
