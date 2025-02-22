# Use an official Maven image to build the app
FROM maven:3.8.6-openjdk-11 AS build

# Set the working directory
WORKDIR /workspace

# Copy the pom.xml file and source code
COPY ./pom.xml ./pom.xml
COPY ./src ./src

# Run Maven to build the project
RUN mvn clean install

# Use an OpenJDK image to run the app
FROM openjdk:11-jre-slim

# Set the working directory
WORKDIR /app

# Copy the jar file from the build stage
COPY --from=build /workspace/target/my-app.jar /app/my-app.jar

# Expose the port the app will run on
EXPOSE 8080

# Run the app
CMD ["java", "-jar", "my-app.jar"]
