# Week 6 - Day 4: Exception Handling & Validation

## Overview
This project demonstrates how to implement global exception handling with @ControllerAdvice and input validation with validation annotations.

## Topics Covered
1. **Global Exception Handler** - Centralized exception handling with @ControllerAdvice
2. **Custom Exceptions** - ResourceNotFoundException, DuplicateResourceException, BadRequestException
3. **Validation Annotations** - @NotBlank, @Email, @Size, @Min, @Max, @Positive, etc.
4. **Error Response** - Standardized error response structure

## Project Structure
```
EmployeeValidation/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/com/example/employeevalidation/
│   │   │   ├── EmployeeValidationApplication.java
│   │   │   ├── config/
│   │   │   │   └── DataLoader.java
│   │   │   ├── controller/
│   │   │   │   └── EmployeeController.java
│   │   │   ├── dto/
│   │   │   │   ├── ApiResponseDTO.java
│   │   │   │   ├── EmployeeRequestDTO.java
│   │   │   │   └── EmployeeResponseDTO.java
│   │   │   ├── entity/
│   │   │   │   └── Employee.java
│   │   │   ├── exception/
│   │   │   │   ├── BadRequestException.java
│   │   │   │   ├── DuplicateResourceException.java
│   │   │   │   ├── ErrorResponse.java
│   │   │   │   ├── GlobalExceptionHandler.java
│   │   │   │   └── ResourceNotFoundException.java
│   │   │   ├── mapper/
│   │   │   │   └── EmployeeMapper.java
│   │   │   ├── repository/
│   │   │   │   └── EmployeeRepository.java
│   │   │   └── service/
│   │   │       ├── EmployeeService.java
│   │   │       └── EmployeeServiceImpl.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/example/employeevalidation/
│           └── EmployeeValidationApplicationTests.java
└── README.md
```

## Validation Annotations Used

| Annotation | Description |
|------------|-------------|
| `@NotBlank` | Field must not be null and must contain at least one non-whitespace character |
| `@NotNull` | Field must not be null |
| `@Size` | String length must be within specified range |
| `@Email` | Field must be a valid email address |
| `@Min` | Numeric field must be >= specified value |
| `@Max` | Numeric field must be <= specified value |
| `@Positive` | Numeric field must be positive |
| `@PastOrPresent` | Date must be in past or present |
| `@Pattern` | String must match specified regex pattern |

## Exception Handling

### Custom Exceptions
- **ResourceNotFoundException**: 404 - Resource not found
- **DuplicateResourceException**: 409 - Conflict (duplicate resource)
- **BadRequestException**: 400 - Invalid request

### Global Exception Handler
The `GlobalExceptionHandler` class uses `@RestControllerAdvice` to handle exceptions globally:
- Catches and transforms exceptions to consistent error responses
- Handles validation errors from `@Valid` annotation
- Returns appropriate HTTP status codes

## Error Response Format
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 400,
  "error": "Validation Failed",
  "message": "Input validation failed. Please check the field errors.",
  "path": "/api/employees",
  "fieldErrors": [
    {
      "field": "email",
      "message": "Please provide a valid email address",
      "rejectedValue": "invalid-email"
    }
  ]
}
```

## How to Run
```bash
cd EmployeeValidation
mvn spring-boot:run
```

## Test Validation Errors

```bash
# Test validation - missing required fields
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{}'

# Test validation - invalid email
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{"firstName":"Test","lastName":"User","email":"invalid","department":"IT","salary":50000,"hireDate":"2024-01-01"}'

# Test validation - salary too low
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{"firstName":"Test","lastName":"User","email":"test@example.com","department":"IT","salary":100,"hireDate":"2024-01-01"}'

# Test not found error
curl http://localhost:8080/api/employees/999

# Test duplicate email
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{"firstName":"Test","lastName":"User","email":"john.doe@example.com","department":"IT","salary":50000,"hireDate":"2024-01-01"}'
```
