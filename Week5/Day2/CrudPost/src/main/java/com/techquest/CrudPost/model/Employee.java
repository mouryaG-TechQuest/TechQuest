package com.techquest.model;
import jakarta.validation.constraints.*;

// import lombok.Data;
// import lombok.NoArgsConstructor;
// import lombok.AllArgsConstructor;
// import jakarta.validation.constraints.*;

// @Data
// @NoArgsConstructor
// @AllArgsConstructor
public class Employee {
    public Employee(int id, String name, double salary, String department) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.department = department;
    }
    
    
    @NotNull(message = "ID cannot be null")
    @Positive(message = "ID must be positive")
    private int id;
    
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;
    
    @NotNull(message = "Salary cannot be null")
    @Min(value = 0, message = "Salary must be positive")
    private double salary;
    
    @NotBlank(message = "Department cannot be blank")
    @Pattern(regexp = "IT|HR|Finance|Sales", message = "Department must be IT, HR, Finance, or Sales")
    private String department;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}