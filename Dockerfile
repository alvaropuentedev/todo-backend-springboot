# Builder Stage
FROM eclipse-temurin:17-jdk AS build

WORKDIR /app

# Install maven
RUN apt-get update && \
    apt-get install -y maven

# Copy only pom.xml file for download the dependency
COPY pom.xml .
COPY src ./src

# Build the application using Maven
RUN mvn clean package -DskipTests

# Final imagen
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file from the previous stage to the container
COPY --from=build /app/target/*.jar /app.jar

# Set the command to run the application
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]