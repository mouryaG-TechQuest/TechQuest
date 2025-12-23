package com.example.employeecontroller.mapper;

import com.example.employeecontroller.dto.EmployeeRequestDTO;
import com.example.employeecontroller.dto.EmployeeResponseDTO;
import com.example.employeecontroller.entity.Employee;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Employee Mapper
 * Day 3: Maps between Entity and DTOs
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
        return new EmployeeResponseDTO(
            employee.getId(),
            employee.getFirstName(),
            employee.getLastName(),
            employee.getEmail(),
            employee.getDepartment(),
            employee.getSalary(),
            employee.getHireDate(),
            employee.getIsActive()
        );
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
     * Convert Request DTO to Entity
     */
    public Employee toEntity(EmployeeRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        Employee employee = new Employee();
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setEmail(dto.getEmail());
        employee.setDepartment(dto.getDepartment());
        employee.setSalary(dto.getSalary());
        employee.setHireDate(dto.getHireDate());
        employee.setIsActive(dto.getIsActive() != null ? dto.getIsActive() : true);
        return employee;
    }

    /**
     * Update Entity from Request DTO
     */
    public void updateEntityFromDTO(EmployeeRequestDTO dto, Employee employee) {
        if (dto.getFirstName() != null) employee.setFirstName(dto.getFirstName());
        if (dto.getLastName() != null) employee.setLastName(dto.getLastName());
        if (dto.getEmail() != null) employee.setEmail(dto.getEmail());
        if (dto.getDepartment() != null) employee.setDepartment(dto.getDepartment());
        if (dto.getSalary() != null) employee.setSalary(dto.getSalary());
        if (dto.getHireDate() != null) employee.setHireDate(dto.getHireDate());
        if (dto.getIsActive() != null) employee.setIsActive(dto.getIsActive());
    }
}
