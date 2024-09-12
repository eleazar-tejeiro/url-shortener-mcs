# Start with a base image containing Java runtime (AdoptOpenJDK)
FROM openjdk:17-jdk-slim AS build
WORKDIR /app
# Copy the jar file from the first stage
COPY target/*.jar app.jar
EXPOSE 8080
# Set the startup command to execute the jar
CMD ["java", "-jar", "/app/app.jar"]
