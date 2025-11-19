package com.techquest.CrudUpdate.service;

import com.techquest.CrudUpdate.model.Employee;
import java.util.Optional;

public interface EmployeeService {
    Optional<Employee> findById(Integer id);
    Employee update(Integer id, Employee updated);
}
