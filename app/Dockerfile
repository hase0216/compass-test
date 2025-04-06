FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /tmp
COPY ./src ./src
COPY ./pom.xml .
RUN mvn package

FROM openjdk:21-jdk
COPY target/app.jar /app/app.jar
EXPOSE 8080