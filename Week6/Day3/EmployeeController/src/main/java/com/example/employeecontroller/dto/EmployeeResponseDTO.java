package com.example.employeecontroller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Employee Response DTO
 * Day 3: Data Transfer Object for outgoing responses
 * Used to send employee data to clients
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponseDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String email;
    private String department;
    private Double salary;
    private LocalDate hireDate;
    private Boolean isActive;
    private String status;

    // Constructor without derived fields
    public EmployeeResponseDTO(Long id, String firstName, String lastName, 
                               String email, String department, Double salary, 
                               LocalDate hireDate, Boolean isActive) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = firstName + " " + lastName;
        this.email = email;
        this.department = department;
        this.salary = salary;
        this.hireDate = hireDate;
        this.isActive = isActive;
        this.status = isActive ? "Active" : "Inactive";
    }
}
