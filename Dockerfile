# Start with a base image containing Java runtime and Maven
FROM maven:3.8.4-openjdk-17-slim AS build

# Set the working directory in the image to "/app"
WORKDIR /app

# Copy the Maven configuration files
COPY pom.xml .
COPY src ./src

# Build the application using Maven
RUN mvn clean package -DskipTests

# Set up a second stage, which will only keep the compiled application
FROM openjdk:17-jdk-slim

# Set the working directory to '/app'
WORKDIR /app

# Copy the jar file from the build stage
COPY --from=build /app/target/*.jar app.jar
COPY .env .env
# Add this line before the CMD instruction
EXPOSE 8080
# Set the startup command to execute the jar
CMD ["java", "-jar", "/app/app.jar"]