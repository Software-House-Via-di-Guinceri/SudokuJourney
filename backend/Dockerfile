# the base image
FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} api-test-0.0.1-SNAPSHOT.jar

COPY secrets/serviceAccountKey.json secrets/serviceAccountKey.json
EXPOSE 8080

ENTRYPOINT ["java","-jar","/api-test-0.0.1-SNAPSHOT.jar"]