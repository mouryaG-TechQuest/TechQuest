package com.techquest.CrudDelete.controller;

public class EmployeeNotFoundException extends RuntimeException {
	public EmployeeNotFoundException(String message) {
		super(message);
	}
}
