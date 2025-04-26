# Build
FROM gradle:8.5-jdk21 AS build
WORKDIR /app

# Copy Gradle build files
COPY build.gradle settings.gradle ./

# Pre-download dependencies (cache layer)
RUN gradle dependencies --no-daemon || true

# Copy the rest of the source code
COPY . .

# Build the JAR
RUN gradle bootJar --no-daemon

# Stage 2: Run the app with lightweight JDK 21 runtime
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy the JAR from the builder stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose your Spring Boot port
EXPOSE 8080

# Environment variables are passed from docker-compose (no need to hardcode)
ENTRYPOINT ["java", "-jar", "app.jar"]
