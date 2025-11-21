package com.techquest.EmployeeRESTAPI.controller;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.techquest.EmployeeRESTAPI.model.Employee;
import com.techquest.EmployeeRESTAPI.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	
	private final EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employees = employeeService.getAllEmployees();
		return ResponseEntity.ok(employees);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
		Employee employee = employeeService.getEmployeeById(id)
			.orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found"));
		return ResponseEntity.ok(employee);
	}
	
	@PostMapping
	public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee employee) {
		Employee created = employeeService.addEmployee(employee);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(
		@PathVariable Integer id,
		@Valid @RequestBody Employee employee) {
		Employee updated = employeeService.updateEmployee(id, employee);
		if (updated == null) {
			throw new EmployeeNotFoundException("Employee with ID " + id + " not found");
		}
		return ResponseEntity.ok(updated);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable Integer id) {
		Employee deleted = employeeService.deleteEmployee(id);
		if (deleted == null) {
			throw new EmployeeNotFoundException("Employee with ID " + id + " not found");
		}
		return ResponseEntity.ok(new DeleteResponse("Employee with ID " + id + " deleted successfully", deleted));
	}
	
	public static class DeleteResponse {
		public String message;
		public Employee deletedEmployee;
		
		public DeleteResponse(String message, Employee deletedEmployee) {
			this.message = message;
			this.deletedEmployee = deletedEmployee;
		}
	}

}
