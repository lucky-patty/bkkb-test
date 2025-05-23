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
### Bonus Tasks
```
1. Add unit tests for those API endpoints. ✔️
2. Write a Dockerfile for wrapping up those services as a Docker container. ✔️
3. Write a CI/CD script for deployment in any preferred framework/platform. ❌ (It exceeds the assignment time's limit 😭)
```

## System Requirement
- Java 21
- Make sure to change `.env` file to your postgresql database connection
- Hope and prayer because this gradle image has so many critical vulnerabilities (DO NOT USE THIS IMAGE IN REAL WORLD)

### Env file
It is important since our database connection will use the config from this file.
```
#This is for app config
DB_URL=jdbc:postgresql://<YOUR_DB_URL>
DB_USER=<YOUR_DB_USERNAME>
DB_PASSWORD=<YOUR_DB_PASSWORD>

#This is for setup postgres database container
POSTGRES_DB: <YOUR_POSTGRESQL_DB>
POSTGRES_USER: <YOUR_POSTGRESQL_USERNAME>
POSTGRES_PASSWORD: <YOUR_POSTGRESQL_PASSWORD>
```

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
