package com.example.employeecontroller.controller;

import com.example.employeecontroller.dto.ApiResponseDTO;
import com.example.employeecontroller.dto.EmployeeRequestDTO;
import com.example.employeecontroller.dto.EmployeeResponseDTO;
import com.example.employeecontroller.entity.Employee;
import com.example.employeecontroller.mapper.EmployeeMapper;
import com.example.employeecontroller.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Employee REST Controller
 * Day 3: Maps REST endpoints to service methods
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

    // ==================== CREATE ====================

    /**
     * Create a new employee
     * POST /api/employees
     */
    @PostMapping
    public ResponseEntity<ApiResponseDTO<EmployeeResponseDTO>> createEmployee(
            @RequestBody EmployeeRequestDTO requestDTO) {
        try {
            Employee employee = employeeMapper.toEntity(requestDTO);
            Employee savedEmployee = employeeService.createEmployee(employee);
            EmployeeResponseDTO responseDTO = employeeMapper.toResponseDTO(savedEmployee);
            
            return new ResponseEntity<>(
                ApiResponseDTO.created("Employee created successfully", responseDTO),
                HttpStatus.CREATED
            );
        } catch (RuntimeException e) {
            return new ResponseEntity<>(
                ApiResponseDTO.error(e.getMessage(), 400),
                HttpStatus.BAD_REQUEST
            );
        }
    }

    // ==================== READ ====================

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
        return employeeService.getEmployeeById(id)
            .map(employee -> {
                EmployeeResponseDTO responseDTO = employeeMapper.toResponseDTO(employee);
                return ResponseEntity.ok(
                    ApiResponseDTO.success("Employee found", responseDTO)
                );
            })
            .orElse(new ResponseEntity<>(
                ApiResponseDTO.error("Employee not found with id: " + id, 404),
                HttpStatus.NOT_FOUND
            ));
    }

    /**
     * Get employee by email
     * GET /api/employees/email/{email}
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<ApiResponseDTO<EmployeeResponseDTO>> getEmployeeByEmail(@PathVariable String email) {
        return employeeService.getEmployeeByEmail(email)
            .map(employee -> {
                EmployeeResponseDTO responseDTO = employeeMapper.toResponseDTO(employee);
                return ResponseEntity.ok(
                    ApiResponseDTO.success("Employee found", responseDTO)
                );
            })
            .orElse(new ResponseEntity<>(
                ApiResponseDTO.error("Employee not found with email: " + email, 404),
                HttpStatus.NOT_FOUND
            ));
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

    // ==================== UPDATE ====================

    /**
     * Update an employee
     * PUT /api/employees/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<EmployeeResponseDTO>> updateEmployee(
            @PathVariable Long id,
            @RequestBody EmployeeRequestDTO requestDTO) {
        try {
            Employee employee = employeeMapper.toEntity(requestDTO);
            Employee updatedEmployee = employeeService.updateEmployee(id, employee);
            EmployeeResponseDTO responseDTO = employeeMapper.toResponseDTO(updatedEmployee);
            
            return ResponseEntity.ok(
                ApiResponseDTO.success("Employee updated successfully", responseDTO)
            );
        } catch (RuntimeException e) {
            return new ResponseEntity<>(
                ApiResponseDTO.error(e.getMessage(), 400),
                HttpStatus.BAD_REQUEST
            );
        }
    }

    /**
     * Partial update an employee
     * PATCH /api/employees/{id}
     */
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<EmployeeResponseDTO>> partialUpdateEmployee(
            @PathVariable Long id,
            @RequestBody EmployeeRequestDTO requestDTO) {
        try {
            Employee existingEmployee = employeeService.getEmployeeById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
            
            employeeMapper.updateEntityFromDTO(requestDTO, existingEmployee);
            Employee updatedEmployee = employeeService.updateEmployee(id, existingEmployee);
            EmployeeResponseDTO responseDTO = employeeMapper.toResponseDTO(updatedEmployee);
            
            return ResponseEntity.ok(
                ApiResponseDTO.success("Employee partially updated", responseDTO)
            );
        } catch (RuntimeException e) {
            return new ResponseEntity<>(
                ApiResponseDTO.error(e.getMessage(), 400),
                HttpStatus.BAD_REQUEST
            );
        }
    }

    // ==================== DELETE ====================

    /**
     * Delete an employee
     * DELETE /api/employees/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<Void>> deleteEmployee(@PathVariable Long id) {
        try {
            employeeService.deleteEmployee(id);
            return ResponseEntity.ok(
                ApiResponseDTO.success("Employee deleted successfully")
            );
        } catch (RuntimeException e) {
            return new ResponseEntity<>(
                ApiResponseDTO.error(e.getMessage(), 404),
                HttpStatus.NOT_FOUND
            );
        }
    }
}
