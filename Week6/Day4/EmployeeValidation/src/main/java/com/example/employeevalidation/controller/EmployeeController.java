package com.example.employeevalidation.controller;

import com.example.employeevalidation.dto.ApiResponseDTO;
import com.example.employeevalidation.dto.EmployeeRequestDTO;
import com.example.employeevalidation.dto.EmployeeResponseDTO;
import com.example.employeevalidation.entity.Employee;
import com.example.employeevalidation.mapper.EmployeeMapper;
import com.example.employeevalidation.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Employee REST Controller with Validation
 * Day 4: Controller with @Valid annotation for input validation
 */
@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    /**
     * Create a new employee with validation
     * POST /api/employees
     */
    @PostMapping
    public ResponseEntity<ApiResponseDTO<EmployeeResponseDTO>> createEmployee(
            @Valid @RequestBody EmployeeRequestDTO requestDTO) {
        
        Employee employee = employeeMapper.toEntity(requestDTO);
        Employee savedEmployee = employeeService.createEmployee(employee);
        EmployeeResponseDTO responseDTO = employeeMapper.toResponseDTO(savedEmployee);
        
        return new ResponseEntity<>(
            ApiResponseDTO.created("Employee created successfully", responseDTO),
            HttpStatus.CREATED
        );
    }

    /**
     * Get all employees
     * GET /api/employees
     */
    @GetMapping
    public ResponseEntity<ApiResponseDTO<List<EmployeeResponseDTO>>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        List<EmployeeResponseDTO> responseDTOs = employeeMapper.toResponseDTOList(employees);
        
        return ResponseEntity.ok(
            ApiResponseDTO.success("Employees retrieved successfully", responseDTOs)
        );
    }

    /**
     * Get employee by ID
     * GET /api/employees/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<EmployeeResponseDTO>> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        EmployeeResponseDTO responseDTO = employeeMapper.toResponseDTO(employee);
        
        return ResponseEntity.ok(
            ApiResponseDTO.success("Employee found", responseDTO)
        );
    }

    /**
     * Get employee by email
     * GET /api/employees/email/{email}
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<ApiResponseDTO<EmployeeResponseDTO>> getEmployeeByEmail(@PathVariable String email) {
        Employee employee = employeeService.getEmployeeByEmail(email);
        EmployeeResponseDTO responseDTO = employeeMapper.toResponseDTO(employee);
        
        return ResponseEntity.ok(
            ApiResponseDTO.success("Employee found", responseDTO)
        );
    }

    /**
     * Get employees by department
     * GET /api/employees/department/{department}
     */
    @GetMapping("/department/{department}")
    public ResponseEntity<ApiResponseDTO<List<EmployeeResponseDTO>>> getEmployeesByDepartment(
            @PathVariable String department) {
        List<Employee> employees = employeeService.getEmployeesByDepartment(department);
        List<EmployeeResponseDTO> responseDTOs = employeeMapper.toResponseDTOList(employees);
        
        return ResponseEntity.ok(
            ApiResponseDTO.success("Employees in " + department + " department retrieved", responseDTOs)
        );
    }

    /**
     * Get active employees
     * GET /api/employees/active
     */
    @GetMapping("/active")
    public ResponseEntity<ApiResponseDTO<List<EmployeeResponseDTO>>> getActiveEmployees() {
        List<Employee> employees = employeeService.getActiveEmployees();
        List<EmployeeResponseDTO> responseDTOs = employeeMapper.toResponseDTOList(employees);
        
        return ResponseEntity.ok(
            ApiResponseDTO.success("Active employees retrieved", responseDTOs)
        );
    }

    /**
     * Search employees by name
     * GET /api/employees/search?name=John
     */
    @GetMapping("/search")
    public ResponseEntity<ApiResponseDTO<List<EmployeeResponseDTO>>> searchEmployees(
            @RequestParam String name) {
        List<Employee> employees = employeeService.searchEmployeesByName(name);
        List<EmployeeResponseDTO> responseDTOs = employeeMapper.toResponseDTOList(employees);
        
        return ResponseEntity.ok(
            ApiResponseDTO.success("Search results for: " + name, responseDTOs)
        );
    }

    /**
     * Get employee count
     * GET /api/employees/count
     */
    @GetMapping("/count")
    public ResponseEntity<ApiResponseDTO<Long>> getEmployeeCount() {
        long count = employeeService.countEmployees();
        return ResponseEntity.ok(
            ApiResponseDTO.success("Employee count retrieved", count)
        );
    }

    /**
     * Update an employee with validation
     * PUT /api/employees/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<EmployeeResponseDTO>> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequestDTO requestDTO) {
        
        Employee employee = employeeMapper.toEntity(requestDTO);
        Employee updatedEmployee = employeeService.updateEmployee(id, employee);
        EmployeeResponseDTO responseDTO = employeeMapper.toResponseDTO(updatedEmployee);
        
        return ResponseEntity.ok(
            ApiResponseDTO.success("Employee updated successfully", responseDTO)
        );
    }

    /**
     * Delete an employee
     * DELETE /api/employees/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<Void>> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok(
            ApiResponseDTO.success("Employee deleted successfully")
        );
    }
}
