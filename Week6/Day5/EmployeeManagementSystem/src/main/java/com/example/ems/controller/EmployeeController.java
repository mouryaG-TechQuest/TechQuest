package com.example.ems.controller;

import com.example.ems.dto.request.EmployeeRequestDTO;
import com.example.ems.dto.response.ApiResponse;
import com.example.ems.dto.response.EmployeeResponseDTO;
import com.example.ems.dto.response.PagedResponse;
import com.example.ems.entity.Employee;
import com.example.ems.mapper.EmployeeMapper;
import com.example.ems.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Employee REST Controller
 * Day 5: REST API endpoints for Employee management
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
    public ResponseEntity<ApiResponse<EmployeeResponseDTO>> createEmployee(
            @Valid @RequestBody EmployeeRequestDTO requestDTO) {
        
        Employee employee = employeeMapper.toEntity(requestDTO);
        Employee savedEmployee = employeeService.createEmployee(employee, requestDTO.getDepartmentId());
        EmployeeResponseDTO responseDTO = employeeMapper.toResponseDTO(savedEmployee);
        
        return new ResponseEntity<>(
            ApiResponse.created("Employee created successfully", responseDTO),
            HttpStatus.CREATED
        );
    }

    // ==================== READ ====================

    /**
     * Get all employees
     * GET /api/employees
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<EmployeeResponseDTO>>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        List<EmployeeResponseDTO> responseDTOs = employeeMapper.toResponseDTOList(employees);
        
        return ResponseEntity.ok(
            ApiResponse.success("Employees retrieved successfully", responseDTOs)
        );
    }

    /**
     * Get all employees with pagination
     * GET /api/employees/paged?page=0&size=10&sortBy=firstName&sortDir=asc
     */
    @GetMapping("/paged")
    public ResponseEntity<ApiResponse<PagedResponse<EmployeeResponseDTO>>> getAllEmployeesPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        Sort sort = sortDir.equalsIgnoreCase("desc") 
            ? Sort.by(sortBy).descending() 
            : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        
        Page<Employee> employeePage = employeeService.getAllEmployees(pageable);
        List<EmployeeResponseDTO> content = employeeMapper.toResponseDTOList(employeePage.getContent());
        
        PagedResponse<EmployeeResponseDTO> pagedResponse = new PagedResponse<>();
        pagedResponse.setContent(content);
        pagedResponse.setPageNumber(employeePage.getNumber());
        pagedResponse.setPageSize(employeePage.getSize());
        pagedResponse.setTotalElements(employeePage.getTotalElements());
        pagedResponse.setTotalPages(employeePage.getTotalPages());
        pagedResponse.setFirst(employeePage.isFirst());
        pagedResponse.setLast(employeePage.isLast());
        pagedResponse.setEmpty(employeePage.isEmpty());
        
        return ResponseEntity.ok(
            ApiResponse.success("Employees retrieved successfully", pagedResponse)
        );
    }

    /**
     * Get employee by ID
     * GET /api/employees/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeResponseDTO>> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        EmployeeResponseDTO responseDTO = employeeMapper.toResponseDTO(employee);
        
        return ResponseEntity.ok(
            ApiResponse.success("Employee found", responseDTO)
        );
    }

    /**
     * Get employee by email
     * GET /api/employees/email/{email}
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<ApiResponse<EmployeeResponseDTO>> getEmployeeByEmail(@PathVariable String email) {
        Employee employee = employeeService.getEmployeeByEmail(email);
        EmployeeResponseDTO responseDTO = employeeMapper.toResponseDTO(employee);
        
        return ResponseEntity.ok(
            ApiResponse.success("Employee found", responseDTO)
        );
    }

    /**
     * Get employees by department
     * GET /api/employees/department/{departmentId}
     */
    @GetMapping("/department/{departmentId}")
    public ResponseEntity<ApiResponse<List<EmployeeResponseDTO>>> getEmployeesByDepartment(
            @PathVariable Long departmentId) {
        List<Employee> employees = employeeService.getEmployeesByDepartment(departmentId);
        List<EmployeeResponseDTO> responseDTOs = employeeMapper.toResponseDTOList(employees);
        
        return ResponseEntity.ok(
            ApiResponse.success("Employees in department retrieved", responseDTOs)
        );
    }

    /**
     * Get active employees
     * GET /api/employees/active
     */
    @GetMapping("/active")
    public ResponseEntity<ApiResponse<List<EmployeeResponseDTO>>> getActiveEmployees() {
        List<Employee> employees = employeeService.getActiveEmployees();
        List<EmployeeResponseDTO> responseDTOs = employeeMapper.toResponseDTOList(employees);
        
        return ResponseEntity.ok(
            ApiResponse.success("Active employees retrieved", responseDTOs)
        );
    }

    /**
     * Get inactive employees
     * GET /api/employees/inactive
     */
    @GetMapping("/inactive")
    public ResponseEntity<ApiResponse<List<EmployeeResponseDTO>>> getInactiveEmployees() {
        List<Employee> employees = employeeService.getInactiveEmployees();
        List<EmployeeResponseDTO> responseDTOs = employeeMapper.toResponseDTOList(employees);
        
        return ResponseEntity.ok(
            ApiResponse.success("Inactive employees retrieved", responseDTOs)
        );
    }

    /**
     * Search employees by name
     * GET /api/employees/search?name=John
     */
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<EmployeeResponseDTO>>> searchEmployees(
            @RequestParam String name) {
        List<Employee> employees = employeeService.searchEmployeesByName(name);
        List<EmployeeResponseDTO> responseDTOs = employeeMapper.toResponseDTOList(employees);
        
        return ResponseEntity.ok(
            ApiResponse.success("Search results for: " + name, responseDTOs)
        );
    }

    /**
     * Get employees by salary range
     * GET /api/employees/salary?min=50000&max=100000
     */
    @GetMapping("/salary")
    public ResponseEntity<ApiResponse<List<EmployeeResponseDTO>>> getEmployeesBySalaryRange(
            @RequestParam Double min,
            @RequestParam Double max) {
        List<Employee> employees = employeeService.getEmployeesBySalaryRange(min, max);
        List<EmployeeResponseDTO> responseDTOs = employeeMapper.toResponseDTOList(employees);
        
        return ResponseEntity.ok(
            ApiResponse.success("Employees with salary between " + min + " and " + max, responseDTOs)
        );
    }

    /**
     * Get employees by job title
     * GET /api/employees/job-title/{jobTitle}
     */
    @GetMapping("/job-title/{jobTitle}")
    public ResponseEntity<ApiResponse<List<EmployeeResponseDTO>>> getEmployeesByJobTitle(
            @PathVariable String jobTitle) {
        List<Employee> employees = employeeService.getEmployeesByJobTitle(jobTitle);
        List<EmployeeResponseDTO> responseDTOs = employeeMapper.toResponseDTOList(employees);
        
        return ResponseEntity.ok(
            ApiResponse.success("Employees with job title: " + jobTitle, responseDTOs)
        );
    }

    /**
     * Get employee count
     * GET /api/employees/count
     */
    @GetMapping("/count")
    public ResponseEntity<ApiResponse<Long>> getEmployeeCount() {
        long count = employeeService.countEmployees();
        return ResponseEntity.ok(
            ApiResponse.success("Employee count retrieved", count)
        );
    }

    /**
     * Get active employee count
     * GET /api/employees/count/active
     */
    @GetMapping("/count/active")
    public ResponseEntity<ApiResponse<Long>> getActiveEmployeeCount() {
        long count = employeeService.countActiveEmployees();
        return ResponseEntity.ok(
            ApiResponse.success("Active employee count retrieved", count)
        );
    }

    /**
     * Get average salary by department
     * GET /api/employees/department/{departmentId}/average-salary
     */
    @GetMapping("/department/{departmentId}/average-salary")
    public ResponseEntity<ApiResponse<Double>> getAverageSalaryByDepartment(@PathVariable Long departmentId) {
        Double avgSalary = employeeService.getAverageSalaryByDepartment(departmentId);
        return ResponseEntity.ok(
            ApiResponse.success("Average salary retrieved", avgSalary)
        );
    }

    // ==================== UPDATE ====================

    /**
     * Update an employee
     * PUT /api/employees/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeResponseDTO>> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequestDTO requestDTO) {
        
        Employee employee = employeeMapper.toEntity(requestDTO);
        Employee updatedEmployee = employeeService.updateEmployee(id, employee, requestDTO.getDepartmentId());
        EmployeeResponseDTO responseDTO = employeeMapper.toResponseDTO(updatedEmployee);
        
        return ResponseEntity.ok(
            ApiResponse.success("Employee updated successfully", responseDTO)
        );
    }

    /**
     * Partial update an employee
     * PATCH /api/employees/{id}
     */
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeResponseDTO>> partialUpdateEmployee(
            @PathVariable Long id,
            @RequestBody EmployeeRequestDTO requestDTO) {
        
        Employee existingEmployee = employeeService.getEmployeeById(id);
        employeeMapper.updateEntityFromDTO(requestDTO, existingEmployee);
        Employee updatedEmployee = employeeService.updateEmployee(id, existingEmployee, requestDTO.getDepartmentId());
        EmployeeResponseDTO responseDTO = employeeMapper.toResponseDTO(updatedEmployee);
        
        return ResponseEntity.ok(
            ApiResponse.success("Employee partially updated", responseDTO)
        );
    }

    /**
     * Toggle employee status (active/inactive)
     * PATCH /api/employees/{id}/toggle-status
     */
    @PatchMapping("/{id}/toggle-status")
    public ResponseEntity<ApiResponse<EmployeeResponseDTO>> toggleEmployeeStatus(@PathVariable Long id) {
        Employee updatedEmployee = employeeService.toggleEmployeeStatus(id);
        EmployeeResponseDTO responseDTO = employeeMapper.toResponseDTO(updatedEmployee);
        
        return ResponseEntity.ok(
            ApiResponse.success("Employee status toggled", responseDTO)
        );
    }

    /**
     * Change employee department
     * PATCH /api/employees/{id}/department/{departmentId}
     */
    @PatchMapping("/{id}/department/{departmentId}")
    public ResponseEntity<ApiResponse<EmployeeResponseDTO>> changeEmployeeDepartment(
            @PathVariable Long id,
            @PathVariable Long departmentId) {
        Employee updatedEmployee = employeeService.changeDepartment(id, departmentId);
        EmployeeResponseDTO responseDTO = employeeMapper.toResponseDTO(updatedEmployee);
        
        return ResponseEntity.ok(
            ApiResponse.success("Employee department changed", responseDTO)
        );
    }

    // ==================== DELETE ====================

    /**
     * Delete an employee
     * DELETE /api/employees/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok(
            ApiResponse.success("Employee deleted successfully")
        );
    }
}
