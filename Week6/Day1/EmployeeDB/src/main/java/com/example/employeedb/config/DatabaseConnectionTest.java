package com.example.employeedb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * Database Connection Test
 * Day 1: Tests MySQL database connection at application startup
 */
@Component
public class DatabaseConnectionTest implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n=== Testing Database Connection ===");
        
        try (Connection connection = dataSource.getConnection()) {
            System.out.println("✓ Database Connection Successful!");
            System.out.println("✓ Database URL: " + connection.getMetaData().getURL());
            System.out.println("✓ Database Product: " + connection.getMetaData().getDatabaseProductName());
            System.out.println("✓ Database Version: " + connection.getMetaData().getDatabaseProductVersion());
            System.out.println("✓ Driver Name: " + connection.getMetaData().getDriverName());
        } catch (Exception e) {
            System.err.println("✗ Database Connection Failed!");
            System.err.println("Error: " + e.getMessage());
        }
        
        System.out.println("===================================\n");
    }
}
