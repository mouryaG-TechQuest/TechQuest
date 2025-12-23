# Week 6 - Day 1: MySQL Database Connection & Employee Entity

## Overview
This project demonstrates how to connect a Spring Boot application to MySQL database and create a JPA Entity.

## Topics Covered
1. **MySQL Dependency** - Added MySQL connector and Spring Data JPA dependencies
2. **Database Configuration** - Configured application.properties for MySQL connection
3. **Employee Entity** - Created JPA entity with proper annotations for table mapping
4. **Connection Test** - Implemented database connection test at startup

## Project Structure
```
EmployeeDB/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/com/example/employeedb/
│   │   │   ├── EmployeeDbApplication.java
│   │   │   ├── config/
│   │   │   │   └── DatabaseConnectionTest.java
│   │   │   └── entity/
│   │   │       └── Employee.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/example/employeedb/
│           └── EmployeeDbApplicationTests.java
└── README.md
```

## Prerequisites
- Java 17+
- MySQL Server running on localhost:3306
- Maven

## Database Setup
1. Make sure MySQL is running
2. Update `application.properties` with your MySQL credentials:
   ```properties
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

## JPA Annotations Used
- `@Entity` - Marks class as a JPA entity
- `@Table` - Specifies the table name
- `@Id` - Marks the primary key field
- `@GeneratedValue` - Auto-generates ID values
- `@Column` - Customizes column properties

## How to Run
```bash
cd EmployeeDB
mvn spring-boot:run
```

## Expected Output
- Application starts on port 8080
- Database connection test runs automatically
- 'employees' table is created in MySQL database
