# ------------------------------
# Stage 1: Build the JAR
# ------------------------------
FROM maven:3.9.2-eclipse-temurin-17 AS build

# Set working directory
WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the fat jar
RUN mvn clean package -DskipTests

# ------------------------------
# Stage 2: Production image
# ------------------------------
FROM eclipse-temurin:17-jdk-jammy

# Set non-root user
RUN useradd -m taskuser
USER taskuser

# Create app directory
WORKDIR /home/taskuser/app

# Copy JAR from build stage
COPY --from=build /app/target/taskmanager-0.0.1-SNAPSHOT.jar ./taskmanager.jar

ENV SPRING_DATASOURCE_URL=jdbc:mysql://samiulenterprise.com:3306/taskmanager?useSSL=false&serverTimezone=UTC
ENV SPRING_DATASOURCE_USERNAME=soft
ENV SPRING_DATASOURCE_PASSWORD=wKvYNzARQ
ENV SPRING_DATASOURCE_DRIVER_CLASS_NAME=com.mysql.cj.jdbc.Driver

# Expose port (match application.properties)
EXPOSE 8080

# Environment variables (optional overrides)
ENV JAVA_OPTS="-Xms512m -Xmx1024m"

# Run the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar taskmanager.jar"]
