# Spring Boot JWT Template

This project provides a template and practical example for building secure REST APIs with Spring Boot, using JWT (JSON Web Tokens) based authentication. It includes user registration and login functionalities, role management (admin and user), and basic CRUD operations for products, demonstrating how to implement entity relationships.

## Features

* User registration with role-based authorization.
* JWT authentication for login.
* Secure endpoints for user management.
* Spring Security integration.
* Exception handling for common authentication and authorization errors.
* Examples of Entity relations.

## Technologies Used

* Java 21+
* Maven 3.x
* Spring Boot 3.4.3
* Spring Security
* JWT (JSON Web Token)
* Lombok
* Jakarta Validation
* JPA (Hibernate)
* H2

## Installation & Setup

### Prerequisites

* Java 21+
* Maven
* PostgreSQL (Optional, for production setup)

### Clone Repository

    git clone https://github.com/your-repo/jwt-spring-template.git
    cd jwt-spring-template

### Configure Database

Modify `application.properties` or `application.yml` to configure database settings.

Example (for H2 in-memory database):

    spring.datasource.url=jdbc:h2:mem:testdb
    spring.datasource.driverClassName=org.h2.Driver
    spring.datasource.username=sa
    spring.datasource.password=
    spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
    spring.jpa.hibernate.ddl-auto=create-drop
    spring.jpa.properties.hibernate.format_sql=true
    spring.jpa.defer-datasource-initialization=true
    spring.datasource.data=classpath:data.sql
    spring.h2.console.enabled=true
    spring.h2.console.path=/h2-console

This configuration allows you to execute an SQL script to insert data before the application starts, as the H2 in-memory database is cleared when the application shuts down.

### Build & Run

    mvn clean install
    mvn spring-boot:run

## API Endpoints

### Authentication

#### Register User

    POST /api/auth/register

**Request Body:**

    {
      "username": "testuser",
      "password": "securepassword",
      "role": "USER"
    }

#### Login

    POST /api/auth/login

**Request Body:**

    {
      "username": "testuser",
      "password": "securepassword"
    }

**Response:**

    {
      "token": "eyJhbGciOiJIUzI1NiIsIn...",
      "id": 1,
      "username": "testuser",
      "roles": ["USER"]
    }

### User Management

#### Get All Users

    GET /api/users

#### Get User by ID

    GET /api/users/{id}

## Security & JWT Implementation

* `AuthService` handles authentication logic and JWT token generation.
* `JwtUtil` is used to create and validate JWT tokens.
* `UserService` manages user registration and retrieval.
* `Spring Security` ensures authentication and authorization.

## Dependencies

The project uses the following dependencies:

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-api</artifactId>
            <version>0.11.5</version>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-impl</artifactId>
            <version>0.11.5</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-jackson</artifactId>
            <version>0.11.5</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
    </dependencies>

## License

This project is licensed under the MIT License.

---

Developed by Manuel J Sánchez García
