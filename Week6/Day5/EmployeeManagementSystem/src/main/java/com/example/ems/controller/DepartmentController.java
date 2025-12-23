package com.example.ems.controller;

import com.example.ems.dto.request.DepartmentRequestDTO;
import com.example.ems.dto.response.ApiResponse;
import com.example.ems.dto.response.DepartmentResponseDTO;
import com.example.ems.entity.Department;
import com.example.ems.mapper.DepartmentMapper;
import com.example.ems.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Department REST Controller
 * Day 5: REST API endpoints for Department management
 */
@RestController
@RequestMapping("/api/departments")
@CrossOrigin(origins = "*")
public class DepartmentController {

    private final DepartmentService departmentService;
    private final DepartmentMapper departmentMapper;

    @Autowired
    public DepartmentController(DepartmentService departmentService, DepartmentMapper departmentMapper) {
        this.departmentService = departmentService;
        this.departmentMapper = departmentMapper;
    }

    // ==================== CREATE ====================

    /**
     * Create a new department
     * POST /api/departments
     */
    @PostMapping
    public ResponseEntity<ApiResponse<DepartmentResponseDTO>> createDepartment(
            @Valid @RequestBody DepartmentRequestDTO requestDTO) {
        
        Department department = departmentMapper.toEntity(requestDTO);
        Department savedDepartment = departmentService.createDepartment(department);
        DepartmentResponseDTO responseDTO = departmentMapper.toResponseDTO(savedDepartment);
        
        return new ResponseEntity<>(
            ApiResponse.created("Department created successfully", responseDTO),
            HttpStatus.CREATED
        );
    }

    // ==================== READ ====================

    /**
     * Get all departments
     * GET /api/departments
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<DepartmentResponseDTO>>> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        List<DepartmentResponseDTO> responseDTOs = departmentMapper.toResponseDTOList(departments);
        
        return ResponseEntity.ok(
            ApiResponse.success("Departments retrieved successfully", responseDTOs)
        );
    }

    /**
     * Get department by ID
     * GET /api/departments/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DepartmentResponseDTO>> getDepartmentById(@PathVariable Long id) {
        Department department = departmentService.getDepartmentById(id);
        DepartmentResponseDTO responseDTO = departmentMapper.toResponseDTO(department);
        
        return ResponseEntity.ok(
            ApiResponse.success("Department found", responseDTO)
        );
    }

    /**
     * Get department by name
     * GET /api/departments/name/{name}
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponse<DepartmentResponseDTO>> getDepartmentByName(@PathVariable String name) {
        Department department = departmentService.getDepartmentByName(name);
        DepartmentResponseDTO responseDTO = departmentMapper.toResponseDTO(department);
        
        return ResponseEntity.ok(
            ApiResponse.success("Department found", responseDTO)
        );
    }

    /**
     * Search departments by name
     * GET /api/departments/search?name=IT
     */
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<DepartmentResponseDTO>>> searchDepartments(
            @RequestParam String name) {
        List<Department> departments = departmentService.searchDepartmentsByName(name);
        List<DepartmentResponseDTO> responseDTOs = departmentMapper.toResponseDTOList(departments);
        
        return ResponseEntity.ok(
            ApiResponse.success("Search results for: " + name, responseDTOs)
        );
    }

    /**
     * Get departments by location
     * GET /api/departments/location/{location}
     */
    @GetMapping("/location/{location}")
    public ResponseEntity<ApiResponse<List<DepartmentResponseDTO>>> getDepartmentsByLocation(
            @PathVariable String location) {
        List<Department> departments = departmentService.getDepartmentsByLocation(location);
        List<DepartmentResponseDTO> responseDTOs = departmentMapper.toResponseDTOList(departments);
        
        return ResponseEntity.ok(
            ApiResponse.success("Departments in " + location + " retrieved", responseDTOs)
        );
    }

    /**
     * Get department count
     * GET /api/departments/count
     */
    @GetMapping("/count")
    public ResponseEntity<ApiResponse<Long>> getDepartmentCount() {
        long count = departmentService.countDepartments();
        return ResponseEntity.ok(
            ApiResponse.success("Department count retrieved", count)
        );
    }

    /**
     * Get employee count in department
     * GET /api/departments/{id}/employee-count
     */
    @GetMapping("/{id}/employee-count")
    public ResponseEntity<ApiResponse<Long>> getEmployeeCountInDepartment(@PathVariable Long id) {
        long count = departmentService.countEmployeesInDepartment(id);
        return ResponseEntity.ok(
            ApiResponse.success("Employee count in department retrieved", count)
        );
    }

    // ==================== UPDATE ====================

    /**
     * Update a department
     * PUT /api/departments/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DepartmentResponseDTO>> updateDepartment(
            @PathVariable Long id,
            @Valid @RequestBody DepartmentRequestDTO requestDTO) {
        
        Department department = departmentMapper.toEntity(requestDTO);
        Department updatedDepartment = departmentService.updateDepartment(id, department);
        DepartmentResponseDTO responseDTO = departmentMapper.toResponseDTO(updatedDepartment);
        
        return ResponseEntity.ok(
            ApiResponse.success("Department updated successfully", responseDTO)
        );
    }

    /**
     * Partial update a department
     * PATCH /api/departments/{id}
     */
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<DepartmentResponseDTO>> partialUpdateDepartment(
            @PathVariable Long id,
            @RequestBody DepartmentRequestDTO requestDTO) {
        
        Department existingDepartment = departmentService.getDepartmentById(id);
        departmentMapper.updateEntityFromDTO(requestDTO, existingDepartment);
        Department updatedDepartment = departmentService.updateDepartment(id, existingDepartment);
        DepartmentResponseDTO responseDTO = departmentMapper.toResponseDTO(updatedDepartment);
        
        return ResponseEntity.ok(
            ApiResponse.success("Department partially updated", responseDTO)
        );
    }

    // ==================== DELETE ====================

    /**
     * Delete a department
     * DELETE /api/departments/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.ok(
            ApiResponse.success("Department deleted successfully")
        );
    }
}
