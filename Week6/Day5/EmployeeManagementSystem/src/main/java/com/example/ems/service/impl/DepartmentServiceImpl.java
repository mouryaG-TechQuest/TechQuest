package com.example.ems.service.impl;

import com.example.ems.entity.Department;
import com.example.ems.exception.BadRequestException;
import com.example.ems.exception.DuplicateResourceException;
import com.example.ems.exception.ResourceNotFoundException;
import com.example.ems.repository.DepartmentRepository;
import com.example.ems.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Department Service Implementation
 * Day 5: Business logic implementation for Department operations
 */
@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department createDepartment(Department department) {
        // Check for duplicate name
        if (departmentRepository.existsByNameIgnoreCase(department.getName())) {
            throw new DuplicateResourceException("Department", "name", department.getName());
        }
        return departmentRepository.save(department);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", id));
    }

    @Override
    @Transactional(readOnly = true)
    public Department getDepartmentByName(String name) {
        return departmentRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "name", name));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Department> searchDepartmentsByName(String name) {
        return departmentRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Department> getDepartmentsByLocation(String location) {
        return departmentRepository.findByLocation(location);
    }

    @Override
    public Department updateDepartment(Long id, Department departmentDetails) {
        Department existingDepartment = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", id));

        // Check if new name conflicts with another department
        if (!existingDepartment.getName().equalsIgnoreCase(departmentDetails.getName()) 
            && departmentRepository.existsByNameIgnoreCase(departmentDetails.getName())) {
            throw new DuplicateResourceException("Department", "name", departmentDetails.getName());
        }

        existingDepartment.setName(departmentDetails.getName());
        existingDepartment.setDescription(departmentDetails.getDescription());
        existingDepartment.setLocation(departmentDetails.getLocation());

        return departmentRepository.save(existingDepartment);
    }

    @Override
    public void deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", id));

        // Check if department has employees
        Long employeeCount = departmentRepository.countEmployeesByDepartmentId(id);
        if (employeeCount != null && employeeCount > 0) {
            throw new BadRequestException("Cannot delete department with " + employeeCount + " employees. Please reassign or delete employees first.");
        }

        departmentRepository.delete(department);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return departmentRepository.existsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByName(String name) {
        return departmentRepository.existsByNameIgnoreCase(name);
    }

    @Override
    @Transactional(readOnly = true)
    public long countDepartments() {
        return departmentRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public long countEmployeesInDepartment(Long departmentId) {
        Long count = departmentRepository.countEmployeesByDepartmentId(departmentId);
        return count != null ? count : 0;
    }
}
