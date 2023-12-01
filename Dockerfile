# Builder Stage
FROM eclipse-temurin:17-jdk AS build

WORKDIR /app

# Instala Maven manualmente
RUN apt-get update && \
    apt-get install -y maven

# Copia solo el archivo pom.xml para descargar las dependencias
COPY pom.xml .
COPY src ./src

# Build the application using Maven
RUN mvn clean package -DskipTests

# Imagen final
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file from the previous stage to the container
COPY --from=build /app/target/*.jar /app.jar

# Set the command to run the application
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]