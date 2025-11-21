package com.techquest.CrudDelete.service;

import java.util.Optional;
import com.techquest.CrudDelete.model.Employee;

public interface EmployeeService {
	Optional<Employee> findById(Integer id);
	Employee delete(Integer id);
	void deleteAll();
}
