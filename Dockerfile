# Базовый образ с Java
FROM openjdk:17-jdk-alpine

WORKDIR /UserInterface

COPY target/UserInterface-1.0-SNAPSHOT.jar /UserInterface/UserInterface-1.0-SNAPSHOT.jar
COPY .env /UserInterface/.env

ENTRYPOINT ["java", "-jar", "UserInterface-1.0-SNAPSHOT.jar"]