FROM eclipse-temurin:21-jdk-alpine

ARG JAR_FILE=target/monitoring_service-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} monitoring_service.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/monitoring_service.jar"]