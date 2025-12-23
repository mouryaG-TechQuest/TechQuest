package com.example.ems.mapper;

import com.example.ems.dto.request.EmployeeRequestDTO;
import com.example.ems.dto.response.EmployeeResponseDTO;
import com.example.ems.entity.Employee;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Employee Mapper
 * Day 5: Maps between Employee entity and DTOs
 */
@Component
public class EmployeeMapper {

    /**
     * Convert Entity to Response DTO
     */
    public EmployeeResponseDTO toResponseDTO(Employee employee) {
        if (employee == null) {
            return null;
        }
        
        EmployeeResponseDTO dto = new EmployeeResponseDTO();
        dto.setId(employee.getId());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setFullName(employee.getFirstName() + " " + employee.getLastName());
        dto.setEmail(employee.getEmail());
        dto.setPhone(employee.getPhone());
        dto.setSalary(employee.getSalary());
        dto.setHireDate(employee.getHireDate());
        dto.setJobTitle(employee.getJobTitle());
        dto.setIsActive(employee.getIsActive());
        dto.setStatus(employee.getIsActive() != null && employee.getIsActive() ? "Active" : "Inactive");
        dto.setCreatedAt(employee.getCreatedAt());
        dto.setUpdatedAt(employee.getUpdatedAt());
        
        // Map department info if available
        if (employee.getDepartment() != null) {
            dto.setDepartmentId(employee.getDepartment().getId());
            dto.setDepartmentName(employee.getDepartment().getName());
        }
        
        return dto;
    }

    /**
     * Convert List of Entities to List of Response DTOs
     */
    public List<EmployeeResponseDTO> toResponseDTOList(List<Employee> employees) {
        return employees.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Convert Request DTO to Entity (without department)
     */
    public Employee toEntity(EmployeeRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Employee employee = new Employee();
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setEmail(dto.getEmail());
        employee.setPhone(dto.getPhone());
        employee.setSalary(dto.getSalary());
        employee.setHireDate(dto.getHireDate());
        employee.setJobTitle(dto.getJobTitle());
        employee.setIsActive(dto.getIsActive() != null ? dto.getIsActive() : true);
        
        return employee;
    }

    /**
     * Update Entity from Request DTO
     */
    public void updateEntityFromDTO(EmployeeRequestDTO dto, Employee employee) {
        if (dto.getFirstName() != null) {
            employee.setFirstName(dto.getFirstName());
        }
        if (dto.getLastName() != null) {
            employee.setLastName(dto.getLastName());
        }
        if (dto.getEmail() != null) {
            employee.setEmail(dto.getEmail());
        }
        if (dto.getPhone() != null) {
            employee.setPhone(dto.getPhone());
        }
        if (dto.getSalary() != null) {
            employee.setSalary(dto.getSalary());
        }
        if (dto.getHireDate() != null) {
            employee.setHireDate(dto.getHireDate());
        }
        if (dto.getJobTitle() != null) {
            employee.setJobTitle(dto.getJobTitle());
        }
        if (dto.getIsActive() != null) {
            employee.setIsActive(dto.getIsActive());
        }
    }
}
