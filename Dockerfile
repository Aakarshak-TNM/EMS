FROM openjdk:17
COPY target/EmployeeManagementSystem.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]