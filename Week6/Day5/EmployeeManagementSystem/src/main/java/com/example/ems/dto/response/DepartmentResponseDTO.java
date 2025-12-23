package com.example.ems.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Department Response DTO
 * Day 5: DTO for department responses
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentResponseDTO {

    private Long id;
    private String name;
    private String description;
    private String location;
    private int employeeCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public DepartmentResponseDTO(Long id, String name, String description, 
                                 String location, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.employeeCount = 0;
    }
}
