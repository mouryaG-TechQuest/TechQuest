package com.example.ems.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Employee Response DTO
 * Day 5: DTO for employee responses
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
    private String phone;
    private Double salary;
    private LocalDate hireDate;
    private String jobTitle;
    private Boolean isActive;
    private String status;
    private Long departmentId;
    private String departmentName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructor for mapping from entity
    public EmployeeResponseDTO(Long id, String firstName, String lastName, String email,
                               String phone, Double salary, LocalDate hireDate, String jobTitle,
                               Boolean isActive, Long departmentId, String departmentName,
                               LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = firstName + " " + lastName;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.hireDate = hireDate;
        this.jobTitle = jobTitle;
        this.isActive = isActive;
        this.status = isActive != null && isActive ? "Active" : "Inactive";
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
