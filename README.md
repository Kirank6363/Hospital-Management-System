# Hospital Management System

A Spring Boot backend for managing hospital operations such as authentication, patients, doctors, and appointments.

## Tech Stack

- Java 21
- Spring Boot 4
- Spring Security
- Spring Data JPA
- PostgreSQL
- Maven
- JWT authentication

## Features

- User signup and login
- JWT-based authentication
- Current logged-in user endpoint
- Patient CRUD operations
- Doctor CRUD operations
- Appointment CRUD operations
- Search patients by name
- Search doctors by name

## Project Structure

```text
src/main/java/com/kiran/hospital
  config
  controller
  dto
  entity
  repository
  service
```

## Prerequisites

- Java 21 installed
- Maven installed
- PostgreSQL installed and running
- A database named `hospitalDB`

## Local Secret Configuration

This project uses a local file for secrets so you can keep real credentials out of GitHub.

Create this file in the project root:

`application-secret.properties`

Example:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/hospitalDB
spring.datasource.username=postgres
spring.datasource.password=123

jwt.secret-key=your-very-long-random-secret-key
jwt.expiration=86400000
```

Make sure this file is listed in `.gitignore`.

## Main Application Properties

Your `src/main/resources/application.properties` should contain the safe project config and import the local secret file:

```properties
spring.application.name=Hospital-management-system

server.port=8081
server.servlet.context-path=/api/v1

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

spring.config.import=optional:file:./application-secret.properties
```

## How To Run

1. Start PostgreSQL.
2. Create a database named `hospitalDB`.
3. Create `application-secret.properties` in the project root.
4. Run the project:

```bash
mvn spring-boot:run
```

The app will start at:

`http://localhost:8081/api/v1`

## API Endpoints

### Auth

- `POST /api/v1/auth/signup`
- `POST /api/v1/auth/login`
- `GET /api/v1/auth/me`

### Patients

- `POST /api/v1/patients`
- `GET /api/v1/patients`
- `GET /api/v1/patients/{id}`
- `PUT /api/v1/patients/{id}`
- `DELETE /api/v1/patients/{id}`
- `GET /api/v1/patients/search?name=...`
- `GET /api/v1/patients/me`

### Doctors

- `POST /api/v1/doctors`
- `GET /api/v1/doctors`
- `GET /api/v1/doctors/{id}`
- `PUT /api/v1/doctors/{id}`
- `DELETE /api/v1/doctors/{id}`
- `GET /api/v1/doctors/search?name=...`

### Appointments

- `POST /api/v1/appointments`
- `GET /api/v1/appointments`
- `GET /api/v1/appointments/{id}`
- `PUT /api/v1/appointments/{id}`
- `DELETE /api/v1/appointments/{id}`

## Authentication

After login, the API returns a JWT token. Send it in the `Authorization` header for protected routes:

```text
Authorization: Bearer <your-jwt-token>
```

Do not send an old or invalid token to `/auth/login`.

## Example Login Request

```json
{
  "username": "kiran",
  "password": "your-password"
}
```

## GitHub Push Checklist

- Do not commit `application-secret.properties`
- Do not commit `target/`
- Do not commit IDE files
- Make sure `README.md` is present
- Test the app before pushing

## Author

Kiran
