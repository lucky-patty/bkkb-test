# Java Assignment

Backend API created with Spring Boot framework for technical assignment. 

## Task Requirement
```
1. These API should be written in Java language, with at least version 17 or higher.
2. Apply Spring Boot framework.
3. Create a RESTful API with the following endpoints:
- GET /users: Retrieve a list of all users.
- GET /users/{userId}: Retrieve details of a specific user.
- POST /users: Create a new user.
- PUT /users/{userId}: Update details of a specific user.
- DELETE /users/{userId}: Delete a specific user.
4. Use the user data from here as the data model.
5. Validation: Ensure that all required fields are provided when crating or updating a user.
6. Response Format: Use JSON for request and response bodies, include appropriate
HTTP status codes for success and errors scenarios.
```

## System Requirement
- Java 21
- Hope and prayer because this gradle image has so many critical vulnerabilities (DO NOT USE THIS IMAGE IN REAL WORLD)

## How to run
You can run from docker, docker compose or even your favourite IDE

### Script
```
gradlew bootRun
```

### Docker Compose
```
docker compose up --build
```

## How to test
```
gradlew test
```
