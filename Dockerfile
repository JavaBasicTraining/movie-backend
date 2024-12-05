# Build stage
FROM gradle:8.5-jdk17 AS builder

# Copy source code
WORKDIR /app
COPY . .

# Build application
RUN gradle build -x test

# Runtime stage
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy built artifact from builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose port
EXPOSE 8081

# Run application with environment variable support
ENTRYPOINT ["java", \
           "-Dspring.profiles.active=docker", \
           "-jar", \
           "app.jar"] 