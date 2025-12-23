# Week 6 - Day 2: Repository & Service Layer

## Overview
This project demonstrates how to create Repository and Service layers for Employee management with Spring Data JPA.

## Topics Covered
1. **Repository Layer** - Created EmployeeRepository extending JpaRepository
2. **Custom Query Methods** - Implemented various query methods using method naming conventions
3. **JPQL Queries** - Custom queries using @Query annotation
4. **Service Layer** - Implemented business logic with EmployeeService
5. **Test Data** - DataLoader for testing repository operations

## Project Structure
```
EmployeeRepository/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/com/example/employeerepository/
│   │   │   ├── EmployeeRepositoryApplication.java
│   │   │   ├── config/
│   │   │   │   └── DataLoader.java
│   │   │   ├── entity/
│   │   │   │   └── Employee.java
│   │   │   ├── repository/
│   │   │   │   └── EmployeeRepository.java
│   │   │   └── service/
│   │   │       ├── EmployeeService.java
│   │   │       └── EmployeeServiceImpl.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/example/employeerepository/
│           └── EmployeeRepositoryTests.java
└── README.md
```

## JpaRepository Methods (Built-in)
| Method | Description |
|--------|-------------|
| `save(entity)` | Insert or update entity |
| `findById(id)` | Find by primary key |
| `findAll()` | Get all records |
| `deleteById(id)` | Delete by primary key |
| `count()` | Count total records |
| `existsById(id)` | Check if exists |

## Custom Query Methods
| Method | Description |
|--------|-------------|
| `findByEmail(email)` | Find employee by email |
| `findByDepartment(dept)` | Find employees by department |
| `findBySalaryGreaterThan(salary)` | Find high earners |
| `searchByName(name)` | Search by first/last name |

## Service Layer Methods
- **Create**: `createEmployee(employee)`
- **Read**: `getAllEmployees()`, `getEmployeeById(id)`, `getEmployeeByEmail(email)`
- **Update**: `updateEmployee(id, employee)`, `toggleEmployeeStatus(id)`
- **Delete**: `deleteEmployee(id)`, `deleteAllEmployees()`

## How to Run
```bash
cd EmployeeRepository
mvn spring-boot:run
```

## Run Tests
```bash
mvn test
```

## Expected Output
- Test data is loaded at startup
- Repository methods are tested and results printed
- All unit tests pass
