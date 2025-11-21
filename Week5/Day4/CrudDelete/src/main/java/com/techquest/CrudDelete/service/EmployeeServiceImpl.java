package com.techquest.CrudDelete.service;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;
import com.techquest.CrudDelete.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private final ConcurrentHashMap<Integer, Employee> store = new ConcurrentHashMap<>();
	
	public EmployeeServiceImpl() {
		// Seed with sample data
		store.put(1, new Employee(1, "Alice", 50000.0, "Engineering"));
		store.put(2, new Employee(2, "Bob", 55000.0, "Sales"));
		store.put(3, new Employee(3, "Charlie", 60000.0, "Management"));
	}
	
	@Override
	public Optional<Employee> findById(Integer id) {
		return Optional.ofNullable(store.get(id));
	}
	
	@Override
	public Employee delete(Integer id) {
		return store.remove(id);
	}
	
	@Override
	public void deleteAll() {
		store.clear();
	}

}
