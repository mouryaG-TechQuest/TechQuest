# Week 6 - Day 3: Controller Layer & DTOs

## Overview
This project demonstrates how to create REST Controller layer with DTOs for clean data transfer between layers.

## Topics Covered
1. **REST Controller** - EmployeeController with all CRUD endpoints
2. **Request DTO** - EmployeeRequestDTO for incoming data
3. **Response DTO** - EmployeeResponseDTO for outgoing data
4. **API Response Wrapper** - ApiResponseDTO for standardized responses
5. **Mapper** - EmployeeMapper for converting between Entity and DTOs

## Project Structure
```
EmployeeController/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/com/example/employeecontroller/
│   │   │   ├── EmployeeControllerApplication.java
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
│       └── java/com/example/employeecontroller/
│           └── EmployeeControllerApplicationTests.java
└── README.md
```

## REST API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/employees` | Create new employee |
| GET | `/api/employees` | Get all employees |
| GET | `/api/employees/{id}` | Get employee by ID |
| GET | `/api/employees/email/{email}` | Get employee by email |
| GET | `/api/employees/department/{dept}` | Get employees by department |
| GET | `/api/employees/active` | Get active employees |
| GET | `/api/employees/search?name=John` | Search by name |
| GET | `/api/employees/count` | Get total count |
| PUT | `/api/employees/{id}` | Update employee |
| PATCH | `/api/employees/{id}` | Partial update |
| DELETE | `/api/employees/{id}` | Delete employee |

## DTOs Explained

### EmployeeRequestDTO
- Used for incoming POST/PUT requests
- Contains only fields that can be set by client

### EmployeeResponseDTO
- Used for outgoing responses
- Contains additional computed fields (fullName, status)

### ApiResponseDTO
- Standard wrapper for all API responses
- Contains: success, message, data, timestamp, statusCode

## How to Run
```bash
cd EmployeeController
mvn spring-boot:run
```

## Test with cURL

```bash
# Get all employees
curl http://localhost:8080/api/employees

# Get employee by ID
curl http://localhost:8080/api/employees/1

# Create employee
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{"firstName":"Test","lastName":"User","email":"test@example.com","department":"IT","salary":50000}'

# Update employee
curl -X PUT http://localhost:8080/api/employees/1 \
  -H "Content-Type: application/json" \
  -d '{"firstName":"Updated","lastName":"Name","email":"updated@example.com","department":"HR","salary":60000}'

# Delete employee
curl -X DELETE http://localhost:8080/api/employees/1
```
