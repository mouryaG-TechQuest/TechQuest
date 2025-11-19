package com.techquest.CrudPost.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;

import com.techquest.model.Employee;

@RestController
public class CRUDRestController {
    
    @PostMapping("/addEmployee")
    public ResponseEntity<String> addEmployee(@Valid @RequestBody Employee employee) {
        
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Employee added successfully: " + employee.getName() + 
                      " (ID: " + employee.getId() + ", Department: " + 
                      employee.getDepartment() + ", Salary: " + employee.getSalary() + ")");
    }
}
