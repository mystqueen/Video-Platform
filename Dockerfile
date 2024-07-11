# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the executable jar file
COPY target/VideoPlatform-0.0.1-SNAPSHOT.jar app.jar

# Copy the .env file
COPY .env .env

# Expose the port the app runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java","-jar","app.jar"]
