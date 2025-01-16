# Étape 1: Utiliser une image de base avec Java installé (JDK 17)
FROM openjdk:17-jdk-slim AS build

# Étape 2: Définir le répertoire de travail à l'intérieur du conteneur
WORKDIR /app

# Étape 3: Copier le fichier JAR de l'application dans le conteneur
COPY target/spring-keycloak.jar /app/app.jar

# Étape 5: Démarrer l'application Spring Boot
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
