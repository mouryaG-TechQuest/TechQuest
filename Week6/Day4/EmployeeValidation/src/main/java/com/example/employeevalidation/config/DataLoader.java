package com.example.employeevalidation.config;

import com.example.employeevalidation.entity.Employee;
import com.example.employeevalidation.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * Data Loader - Loads sample data at startup
 * Day 4: Sample data for testing
 */
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception {
        if (employeeRepository.count() == 0) {
            List<Employee> employees = Arrays.asList(
                new Employee("John", "Doe", "john.doe@example.com", "IT", 75000.0, LocalDate.of(2020, 1, 15)),
                new Employee("Jane", "Smith", "jane.smith@example.com", "HR", 65000.0, LocalDate.of(2019, 6, 20)),
                new Employee("Bob", "Johnson", "bob.johnson@example.com", "IT", 80000.0, LocalDate.of(2018, 3, 10))
            );

            employeeRepository.saveAll(employees);
            System.out.println("\n✓ Sample data loaded: " + employeeRepository.count() + " employees");
        }
    }
}
