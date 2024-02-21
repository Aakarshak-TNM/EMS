#FROM openjdk:17
#COPY target/EmployeeManagementSystem.jar /app.jar
#ENTRYPOINT ["java", "-jar", "/app.jar"]


# syntax=docker/dockerfile:1

#FROM eclipse-temurin:17-jdk-jammy
#
#WORKDIR /app
#
#COPY .mvn/ .mvn
#COPY mvnw pom.xml ./
#COPY src ./src
#RUN ./mvnw dependency:resolve
#
#
#CMD ["./mvnw", "spring-boot:run"]

# Stage 1: Build
FROM maven:3.8.3-jdk-17 as build

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY src ./src

RUN ./mvnw dependency:resolve
RUN ./mvnw clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

COPY --from=build /app/target/ems-app.jar /app/ems-app.jar

CMD ["java", "-jar", "ems-app.jar"]
