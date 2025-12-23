package com.example.ems.config;

import com.example.ems.entity.Department;
import com.example.ems.entity.Employee;
import com.example.ems.repository.DepartmentRepository;
import com.example.ems.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * Data Loader - Loads sample data at startup
 * Day 5: Sample data for testing the Employee Management System
 */
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception {
        // Only load data if database is empty
        if (departmentRepository.count() == 0) {
            loadSampleData();
        } else {
            System.out.println("\n✓ Database already contains data. Skipping sample data load.");
        }
    }

    private void loadSampleData() {
        System.out.println("\n=== Loading Sample Data ===");

        // Create Departments
        Department itDept = new Department("Information Technology", 
            "IT department handles all technology-related matters", "Building A, Floor 3");
        Department hrDept = new Department("Human Resources", 
            "HR department manages employee relations and recruitment", "Building A, Floor 1");
        Department financeDept = new Department("Finance", 
            "Finance department handles all financial operations", "Building B, Floor 2");
        Department marketingDept = new Department("Marketing", 
            "Marketing department handles brand and promotions", "Building B, Floor 1");
        Department operationsDept = new Department("Operations", 
            "Operations department manages daily business operations", "Building C, Floor 1");

        List<Department> departments = departmentRepository.saveAll(
            Arrays.asList(itDept, hrDept, financeDept, marketingDept, operationsDept)
        );

        System.out.println("✓ Created " + departments.size() + " departments");

        // Create Employees
        List<Employee> employees = Arrays.asList(
            // IT Department
            new Employee("John", "Doe", "john.doe@company.com", "+1-555-0101",
                85000.0, LocalDate.of(2020, 1, 15), "Senior Software Engineer", itDept),
            new Employee("Jane", "Smith", "jane.smith@company.com", "+1-555-0102",
                92000.0, LocalDate.of(2019, 6, 20), "Tech Lead", itDept),
            new Employee("Bob", "Johnson", "bob.johnson@company.com", "+1-555-0103",
                78000.0, LocalDate.of(2021, 3, 10), "Software Engineer", itDept),
            new Employee("Alice", "Williams", "alice.williams@company.com", "+1-555-0104",
                72000.0, LocalDate.of(2022, 2, 28), "Junior Developer", itDept),
            
            // HR Department
            new Employee("Emily", "Brown", "emily.brown@company.com", "+1-555-0201",
                68000.0, LocalDate.of(2018, 5, 5), "HR Manager", hrDept),
            new Employee("Michael", "Davis", "michael.davis@company.com", "+1-555-0202",
                55000.0, LocalDate.of(2021, 8, 15), "HR Specialist", hrDept),
            
            // Finance Department
            new Employee("Sarah", "Miller", "sarah.miller@company.com", "+1-555-0301",
                95000.0, LocalDate.of(2017, 4, 12), "Finance Director", financeDept),
            new Employee("David", "Wilson", "david.wilson@company.com", "+1-555-0302",
                75000.0, LocalDate.of(2020, 9, 1), "Senior Accountant", financeDept),
            new Employee("Jennifer", "Taylor", "jennifer.taylor@company.com", "+1-555-0303",
                62000.0, LocalDate.of(2022, 1, 20), "Accountant", financeDept),
            
            // Marketing Department
            new Employee("Chris", "Anderson", "chris.anderson@company.com", "+1-555-0401",
                78000.0, LocalDate.of(2019, 11, 10), "Marketing Manager", marketingDept),
            new Employee("Lisa", "Thomas", "lisa.thomas@company.com", "+1-555-0402",
                58000.0, LocalDate.of(2021, 6, 25), "Marketing Specialist", marketingDept),
            
            // Operations Department
            new Employee("Robert", "Jackson", "robert.jackson@company.com", "+1-555-0501",
                88000.0, LocalDate.of(2018, 2, 14), "Operations Manager", operationsDept),
            new Employee("Karen", "White", "karen.white@company.com", "+1-555-0502",
                65000.0, LocalDate.of(2020, 7, 30), "Operations Coordinator", operationsDept)
        );

        employeeRepository.saveAll(employees);
        System.out.println("✓ Created " + employees.size() + " employees");

        // Print summary
        System.out.println("\n=== Sample Data Summary ===");
        System.out.println("Departments: " + departmentRepository.count());
        System.out.println("Employees: " + employeeRepository.count());
        System.out.println("  - IT: " + employeeRepository.findByDepartmentId(itDept.getId()).size());
        System.out.println("  - HR: " + employeeRepository.findByDepartmentId(hrDept.getId()).size());
        System.out.println("  - Finance: " + employeeRepository.findByDepartmentId(financeDept.getId()).size());
        System.out.println("  - Marketing: " + employeeRepository.findByDepartmentId(marketingDept.getId()).size());
        System.out.println("  - Operations: " + employeeRepository.findByDepartmentId(operationsDept.getId()).size());
        System.out.println("===========================\n");
    }
}
