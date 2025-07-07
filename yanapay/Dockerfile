FROM eclipse-temurin:21-jdk-alpine

ARG JAR_FILE=target/yanapay-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} yanapay_backend.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/yanapay_backend.jar"]