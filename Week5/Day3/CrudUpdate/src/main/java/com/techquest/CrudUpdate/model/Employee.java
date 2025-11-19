package com.techquest.CrudUpdate.model;

import jakarta.validation.constraints.*;

public class Employee {

    @NotNull(message = "ID cannot be null")
    @Positive(message = "ID must be positive")
    private Integer id;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotNull(message = "Salary cannot be null")
    @Min(value = 0, message = "Salary must be positive")
    private Double salary;

    @NotBlank(message = "Department cannot be blank")
    private String department;

    public Employee() {}

    public Employee(Integer id, String name, Double salary, String department) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Double getSalary() { return salary; }
    public void setSalary(Double salary) { this.salary = salary; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
}
