# Use the OpenJDK 23 image as the base
FROM openjdk:23-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/*.jar app.jar

# Expose the port on which your Spring Boot app runs
EXPOSE 8080

# Set the entry point to run your Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]