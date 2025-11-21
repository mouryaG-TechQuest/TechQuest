package com.techquest.EmployeeRESTAPI.service;

import java.util.List;
import java.util.Optional;
import com.techquest.EmployeeRESTAPI.model.Employee;

public interface EmployeeService {
	List<Employee> getAllEmployees();
	Optional<Employee> getEmployeeById(Integer id);
	Employee addEmployee(Employee employee);
	Employee updateEmployee(Integer id, Employee employee);
	Employee deleteEmployee(Integer id);
}
