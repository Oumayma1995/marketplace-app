# ===== Stage 1: build Maven =====
FROM maven:3.9.1-eclipse-temurin-17 AS build
WORKDIR /app

# Copier pom.xml et src
COPY pom.xml .
COPY src ./src

# Compiler le projet (skip tests pour gagner du temps)
RUN mvn clean package -DskipTests

# ===== Stage 2: runtime =====
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copier le jar compilé depuis le stage build
COPY --from=build /app/target/*.jar backend.jar

# Exposer le port Spring Boot
EXPOSE 8080

# Commande pour lancer l'application
ENTRYPOINT ["java", "-jar", "backend.jar"]