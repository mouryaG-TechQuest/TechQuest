package com.techquest.CrudDelete.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.techquest.CrudDelete.model.Employee;
import com.techquest.CrudDelete.service.EmployeeService;

@RestController
public class EmployeeController {
	
	private final EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@DeleteMapping("/deleteEmployee/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable Integer id) {
		Employee deleted = employeeService.delete(id);
		if (deleted == null) {
			throw new EmployeeNotFoundException("Employee with ID " + id + " not found");
		}
		return ResponseEntity.status(HttpStatus.OK)
			.body(new DeleteResponse("Employee with ID " + id + " deleted successfully", deleted));
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
