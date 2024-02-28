# Use the Eclipse Temurin JDK 17 base image
FROM eclipse-temurin:17-jdk-focal

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven wrapper configuration directory and files to the container
COPY .mvn/ .mvn
# Copy the Maven wrapper script (mvnw) and the project's POM file (pom.xml) to the container
COPY mvnw pom.xml ./

# Set execute permissions for the Maven wrapper script (mvnw)
RUN chmod +x mvnw

# Download project dependencies and store them in the local repository
RUN ./mvnw dependency:go-offline

# Copy the project source code to the container
COPY src ./src

# Specify the default command to run the Spring Boot application using the Maven wrapper
CMD ["./mvnw", "spring-boot:run"]
