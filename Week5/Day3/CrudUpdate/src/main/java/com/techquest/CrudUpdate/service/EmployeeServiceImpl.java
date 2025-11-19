package com.techquest.CrudUpdate.service;

import com.techquest.CrudUpdate.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<Integer, Employee> store = new ConcurrentHashMap<>();

    public EmployeeServiceImpl() {
        // seed with sample data
        store.put(1, new Employee(1, "Alice", 60000.0, "IT"));
        store.put(2, new Employee(2, "Bob", 45000.0, "HR"));
    }

    @Override
    public Optional<Employee> findById(Integer id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Employee update(Integer id, Employee updated) {
        Employee existing = store.get(id);
        if (existing == null) return null;
        // apply changes (we keep id)
        existing.setName(updated.getName());
        existing.setSalary(updated.getSalary());
        existing.setDepartment(updated.getDepartment());
        store.put(id, existing);
        return existing;
    }
}
