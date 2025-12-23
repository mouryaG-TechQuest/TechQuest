package com.example.ems.mapper;

import com.example.ems.dto.request.DepartmentRequestDTO;
import com.example.ems.dto.response.DepartmentResponseDTO;
import com.example.ems.entity.Department;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Department Mapper
 * Day 5: Maps between Department entity and DTOs
 */
@Component
public class DepartmentMapper {

    /**
     * Convert Entity to Response DTO
     */
    public DepartmentResponseDTO toResponseDTO(Department department) {
        if (department == null) {
            return null;
        }
        
        DepartmentResponseDTO dto = new DepartmentResponseDTO();
        dto.setId(department.getId());
        dto.setName(department.getName());
        dto.setDescription(department.getDescription());
        dto.setLocation(department.getLocation());
        dto.setCreatedAt(department.getCreatedAt());
        dto.setUpdatedAt(department.getUpdatedAt());
        
        // Count employees if available
        if (department.getEmployees() != null) {
            dto.setEmployeeCount(department.getEmployees().size());
        } else {
            dto.setEmployeeCount(0);
        }
        
        return dto;
    }

    /**
     * Convert List of Entities to List of Response DTOs
     */
    public List<DepartmentResponseDTO> toResponseDTOList(List<Department> departments) {
        return departments.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Convert Request DTO to Entity
     */
    public Department toEntity(DepartmentRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Department department = new Department();
        department.setName(dto.getName());
        department.setDescription(dto.getDescription());
        department.setLocation(dto.getLocation());
        
        return department;
    }

    /**
     * Update Entity from Request DTO
     */
    public void updateEntityFromDTO(DepartmentRequestDTO dto, Department department) {
        if (dto.getName() != null) {
            department.setName(dto.getName());
        }
        if (dto.getDescription() != null) {
            department.setDescription(dto.getDescription());
        }
        if (dto.getLocation() != null) {
            department.setLocation(dto.getLocation());
        }
    }
}
