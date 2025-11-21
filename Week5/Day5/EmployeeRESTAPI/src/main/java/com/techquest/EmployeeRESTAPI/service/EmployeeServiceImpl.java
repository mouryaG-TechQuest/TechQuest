package com.techquest.EmployeeRESTAPI.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Service;
import com.techquest.EmployeeRESTAPI.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private final ConcurrentHashMap<Integer, Employee> store = new ConcurrentHashMap<>();
	private final AtomicInteger idCounter = new AtomicInteger(3);
	
	public EmployeeServiceImpl() {
		// Seed with sample data
		store.put(1, new Employee(1, "Alice Johnson", 75000.0, "Engineering"));
		store.put(2, new Employee(2, "Bob Smith", 65000.0, "Sales"));
		store.put(3, new Employee(3, "Charlie Brown", 70000.0, "Marketing"));
	}
	
	@Override
	public List<Employee> getAllEmployees() {
		return new ArrayList<>(store.values());
	}
	
	@Override
	public Optional<Employee> getEmployeeById(Integer id) {
		return Optional.ofNullable(store.get(id));
	}
	
	@Override
	public Employee addEmployee(Employee employee) {
		Integer newId = idCounter.incrementAndGet();
		employee.setId(newId);
		store.put(newId, employee);
		return employee;
	}
	
	@Override
	public Employee updateEmployee(Integer id, Employee updated) {
		Employee existing = store.get(id);
		if (existing == null) {
			return null;
		}
		// Retain ID, update other fields
		updated.setId(id);
		store.put(id, updated);
		return updated;
	}
	
	@Override
	public Employee deleteEmployee(Integer id) {
		return store.remove(id);
	}

}
