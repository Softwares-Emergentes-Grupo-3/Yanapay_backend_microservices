FROM eclipse-temurin:21-jdk-alpine

ARG JAR_FILE=target/api-gateway-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} api-gateway.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/api-gateway.jar"]