package com.techquest.CrudDelete.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
	
	private Integer id;
	
	@NotBlank(message = "Name is required")
	@Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
	private String name;
	
	@Min(value = 0, message = "Salary must be greater than or equal to 0")
	private Double salary;
	
	@NotBlank(message = "Department is required")
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Department must contain only letters and spaces")
	private String department;

}
