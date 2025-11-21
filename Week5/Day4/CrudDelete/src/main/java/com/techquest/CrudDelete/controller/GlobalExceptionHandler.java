package com.techquest.CrudDelete.controller;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<?> handleEmployeeNotFound(EmployeeNotFoundException ex) {
		ErrorResponse error = new ErrorResponse(
			LocalDateTime.now(),
			HttpStatus.NOT_FOUND.value(),
			"Not Found",
			ex.getMessage()
		);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	public static class ErrorResponse {
		public LocalDateTime timestamp;
		public int status;
		public String error;
		public String message;
		
		public ErrorResponse(LocalDateTime timestamp, int status, String error, String message) {
			this.timestamp = timestamp;
			this.status = status;
			this.error = error;
			this.message = message;
		}
	}

}
