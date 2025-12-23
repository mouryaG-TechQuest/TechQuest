# Week 6 - Day 5: Mini Project - Employee Management System

## 🎯 Overview
A complete Employee Management System REST API with MySQL database integration. This mini project combines all concepts learned throughout Week 6.

## ✅ Features
- Full CRUD Operations for Employees and Departments
- Input Validation with custom error messages
- Global Exception Handling
- DTOs for clean data transfer
- Layered Architecture (Controller → Service → Repository)
- Pagination and Sorting
- Search and Filter capabilities
- Relationship between Employee and Department (Many-to-One)

## 📁 Project Structure
```
EmployeeManagementSystem/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/com/example/ems/
│   │   │   ├── EmployeeManagementSystemApplication.java
│   │   │   ├── config/
│   │   │   │   └── DataLoader.java
│   │   │   ├── controller/
│   │   │   │   ├── DepartmentController.java
│   │   │   │   └── EmployeeController.java
│   │   │   ├── dto/
│   │   │   │   ├── request/
│   │   │   │   │   ├── DepartmentRequestDTO.java
│   │   │   │   │   └── EmployeeRequestDTO.java
│   │   │   │   └── response/
│   │   │   │       ├── ApiResponse.java
│   │   │   │       ├── DepartmentResponseDTO.java
│   │   │   │       ├── EmployeeResponseDTO.java
│   │   │   │       └── PagedResponse.java
│   │   │   ├── entity/
│   │   │   │   ├── Department.java
│   │   │   │   └── Employee.java
│   │   │   ├── exception/
│   │   │   │   ├── BadRequestException.java
│   │   │   │   ├── DuplicateResourceException.java
│   │   │   │   ├── ErrorResponse.java
│   │   │   │   ├── GlobalExceptionHandler.java
│   │   │   │   └── ResourceNotFoundException.java
│   │   │   ├── mapper/
│   │   │   │   ├── DepartmentMapper.java
│   │   │   │   └── EmployeeMapper.java
│   │   │   ├── repository/
│   │   │   │   ├── DepartmentRepository.java
│   │   │   │   └── EmployeeRepository.java
│   │   │   └── service/
│   │   │       ├── DepartmentService.java
│   │   │       ├── EmployeeService.java
│   │   │       └── impl/
│   │   │           ├── DepartmentServiceImpl.java
│   │   │           └── EmployeeServiceImpl.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/example/ems/
│           └── EmployeeManagementSystemApplicationTests.java
└── README.md
```

## 🚀 How to Run

### Prerequisites
- Java 17+
- Maven
- MySQL Server running on localhost:3306

### Database Setup
1. Make sure MySQL is running
2. Update `application.properties` with your MySQL credentials:
   ```properties
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

### Run the Application
```bash
cd EmployeeManagementSystem
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## 📚 API Endpoints

### Department Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/departments` | Create new department |
| GET | `/api/departments` | Get all departments |
| GET | `/api/departments/{id}` | Get department by ID |
| GET | `/api/departments/name/{name}` | Get department by name |
| GET | `/api/departments/search?name=IT` | Search departments |
| GET | `/api/departments/location/{location}` | Get departments by location |
| GET | `/api/departments/count` | Get department count |
| GET | `/api/departments/{id}/employee-count` | Get employee count in department |
| PUT | `/api/departments/{id}` | Update department |
| PATCH | `/api/departments/{id}` | Partial update department |
| DELETE | `/api/departments/{id}` | Delete department |

### Employee Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/employees` | Create new employee |
| GET | `/api/employees` | Get all employees |
| GET | `/api/employees/paged` | Get employees with pagination |
| GET | `/api/employees/{id}` | Get employee by ID |
| GET | `/api/employees/email/{email}` | Get employee by email |
| GET | `/api/employees/department/{deptId}` | Get employees by department |
| GET | `/api/employees/active` | Get active employees |
| GET | `/api/employees/inactive` | Get inactive employees |
| GET | `/api/employees/search?name=John` | Search by name |
| GET | `/api/employees/salary?min=50000&max=100000` | Get by salary range |
| GET | `/api/employees/job-title/{title}` | Get by job title |
| GET | `/api/employees/count` | Get employee count |
| GET | `/api/employees/count/active` | Get active employee count |
| GET | `/api/employees/department/{id}/average-salary` | Get avg salary by dept |
| PUT | `/api/employees/{id}` | Update employee |
| PATCH | `/api/employees/{id}` | Partial update employee |
| PATCH | `/api/employees/{id}/toggle-status` | Toggle active status |
| PATCH | `/api/employees/{id}/department/{deptId}` | Change department |
| DELETE | `/api/employees/{id}` | Delete employee |

## 🧪 Test with cURL

### Department Operations

```bash
# Create department
curl -X POST http://localhost:8080/api/departments \
  -H "Content-Type: application/json" \
  -d '{"name":"Engineering","description":"Engineering department","location":"Building D"}'

# Get all departments
curl http://localhost:8080/api/departments

# Get department by ID
curl http://localhost:8080/api/departments/1

# Update department
curl -X PUT http://localhost:8080/api/departments/1 \
  -H "Content-Type: application/json" \
  -d '{"name":"IT Department","description":"Updated description","location":"Building A"}'

# Delete department
curl -X DELETE http://localhost:8080/api/departments/6
```

### Employee Operations

```bash
# Create employee
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{
    "firstName":"Test",
    "lastName":"Employee",
    "email":"test@company.com",
    "phone":"+1-555-9999",
    "salary":60000,
    "hireDate":"2024-01-15",
    "jobTitle":"Software Engineer",
    "departmentId":1
  }'

# Get all employees
curl http://localhost:8080/api/employees

# Get employees with pagination
curl "http://localhost:8080/api/employees/paged?page=0&size=5&sortBy=salary&sortDir=desc"

# Get employee by ID
curl http://localhost:8080/api/employees/1

# Search employees
curl "http://localhost:8080/api/employees/search?name=John"

# Get employees by salary range
curl "http://localhost:8080/api/employees/salary?min=70000&max=100000"

# Toggle employee status
curl -X PATCH http://localhost:8080/api/employees/1/toggle-status

# Change employee department
curl -X PATCH http://localhost:8080/api/employees/1/department/2

# Update employee
curl -X PUT http://localhost:8080/api/employees/1 \
  -H "Content-Type: application/json" \
  -d '{
    "firstName":"John",
    "lastName":"Doe",
    "email":"john.doe@company.com",
    "phone":"+1-555-0101",
    "salary":90000,
    "hireDate":"2020-01-15",
    "jobTitle":"Principal Engineer",
    "isActive":true,
    "departmentId":1
  }'

# Delete employee
curl -X DELETE http://localhost:8080/api/employees/14
```

### Test Validation Errors

```bash
# Test missing required fields
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{}'

# Test invalid email
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{"firstName":"Test","lastName":"User","email":"invalid","salary":50000,"hireDate":"2024-01-01"}'

# Test duplicate email
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{"firstName":"New","lastName":"User","email":"john.doe@company.com","salary":50000,"hireDate":"2024-01-01"}'

# Test not found
curl http://localhost:8080/api/employees/999
```

## 📊 Sample Data
When the application starts, it automatically loads sample data:
- **5 Departments**: IT, HR, Finance, Marketing, Operations
- **13 Employees**: Distributed across all departments

## 🔧 Technologies Used
- Spring Boot 3.2.0
- Spring Data JPA
- Spring Validation
- MySQL 8.0
- Lombok
- Maven

## 📝 Validation Rules

### Employee
- `firstName`: Required, 2-50 characters
- `lastName`: Required, 2-50 characters
- `email`: Required, valid email format, unique
- `salary`: Required, positive, min 1000
- `hireDate`: Required, must be past or present

### Department
- `name`: Required, 2-100 characters, unique
- `description`: Max 500 characters
- `location`: Max 100 characters

## ❌ Error Response Format
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

## ✅ Success Response Format
```json
{
  "success": true,
  "message": "Employee created successfully",
  "data": {
    "id": 1,
    "firstName": "John",
    "lastName": "Doe",
    "fullName": "John Doe",
    "email": "john.doe@company.com",
    "salary": 85000.0,
    "status": "Active"
  },
  "timestamp": "2024-01-15T10:30:00",
  "statusCode": 201
}
```
