package com.techquest.CrudUpdate.controller;

import com.techquest.CrudUpdate.model.Employee;
import com.techquest.CrudUpdate.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @PutMapping("/updateEmployee/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Integer id, @Valid @RequestBody Employee payload) {
        Optional<Employee> found = service.findById(id);
        if (found.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Employee updated = service.update(id, payload);
        return ResponseEntity.ok(updated);
    }
}
