package com.example.ems.service.impl;

import com.example.ems.entity.Department;
import com.example.ems.entity.Employee;
import com.example.ems.exception.DuplicateResourceException;
import com.example.ems.exception.ResourceNotFoundException;
import com.example.ems.repository.DepartmentRepository;
import com.example.ems.repository.EmployeeRepository;
import com.example.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Employee Service Implementation
 * Day 5: Business logic implementation for Employee operations
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, 
                               DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Employee createEmployee(Employee employee, Long departmentId) {
        // Check for duplicate email
        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new DuplicateResourceException("Employee", "email", employee.getEmail());
        }

        // Set department if provided
        if (departmentId != null) {
            Department department = departmentRepository.findById(departmentId)
                    .orElseThrow(() -> new ResourceNotFoundException("Department", "id", departmentId));
            employee.setDepartment(department);
        }

        return employeeRepository.save(employee);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Employee> getAllEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
    }

    @Override
    @Transactional(readOnly = true)
    public Employee getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "email", email));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getEmployeesByDepartment(Long departmentId) {
        // Verify department exists
        if (!departmentRepository.existsById(departmentId)) {
            throw new ResourceNotFoundException("Department", "id", departmentId);
        }
        return employeeRepository.findByDepartmentId(departmentId);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Employee> getEmployeesByDepartment(Long departmentId, Pageable pageable) {
        if (!departmentRepository.existsById(departmentId)) {
            throw new ResourceNotFoundException("Department", "id", departmentId);
        }
        return employeeRepository.findByDepartmentId(departmentId, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getActiveEmployees() {
        return employeeRepository.findByIsActiveTrue();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getInactiveEmployees() {
        return employeeRepository.findByIsActiveFalse();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> searchEmployeesByName(String name) {
        return employeeRepository.searchByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Employee> searchEmployeesByName(String name, Pageable pageable) {
        return employeeRepository.searchByName(name, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getEmployeesBySalaryRange(Double minSalary, Double maxSalary) {
        return employeeRepository.findBySalaryBetween(minSalary, maxSalary);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getEmployeesByJobTitle(String jobTitle) {
        return employeeRepository.findByJobTitleContainingIgnoreCase(jobTitle);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employeeDetails, Long departmentId) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));

        // Check if new email conflicts with another employee
        if (!existingEmployee.getEmail().equals(employeeDetails.getEmail()) 
            && employeeRepository.existsByEmail(employeeDetails.getEmail())) {
            throw new DuplicateResourceException("Employee", "email", employeeDetails.getEmail());
        }

        // Update fields
        existingEmployee.setFirstName(employeeDetails.getFirstName());
        existingEmployee.setLastName(employeeDetails.getLastName());
        existingEmployee.setEmail(employeeDetails.getEmail());
        existingEmployee.setPhone(employeeDetails.getPhone());
        existingEmployee.setSalary(employeeDetails.getSalary());
        existingEmployee.setHireDate(employeeDetails.getHireDate());
        existingEmployee.setJobTitle(employeeDetails.getJobTitle());
        existingEmployee.setIsActive(employeeDetails.getIsActive());

        // Update department if provided
        if (departmentId != null) {
            Department department = departmentRepository.findById(departmentId)
                    .orElseThrow(() -> new ResourceNotFoundException("Department", "id", departmentId));
            existingEmployee.setDepartment(department);
        }

        return employeeRepository.save(existingEmployee);
    }

    @Override
    public Employee toggleEmployeeStatus(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        
        employee.setIsActive(!employee.getIsActive());
        return employeeRepository.save(employee);
    }

    @Override
    public Employee changeDepartment(Long employeeId, Long newDepartmentId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));

        if (newDepartmentId != null) {
            Department department = departmentRepository.findById(newDepartmentId)
                    .orElseThrow(() -> new ResourceNotFoundException("Department", "id", newDepartmentId));
            employee.setDepartment(department);
        } else {
            employee.setDepartment(null);
        }

        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        employeeRepository.delete(employee);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return employeeRepository.existsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return employeeRepository.existsByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public long countEmployees() {
        return employeeRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public long countActiveEmployees() {
        return employeeRepository.findByIsActiveTrue().size();
    }

    @Override
    @Transactional(readOnly = true)
    public Double getAverageSalaryByDepartment(Long departmentId) {
        if (!departmentRepository.existsById(departmentId)) {
            throw new ResourceNotFoundException("Department", "id", departmentId);
        }
        return employeeRepository.getAverageSalaryByDepartment(departmentId);
    }
}
