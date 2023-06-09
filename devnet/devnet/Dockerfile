FROM maven:3.9.2-eclipse-temurin-17

LABEL maintainer="Junior Martins"

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /src/tech/devnet.jar

ENTRYPOINT ["java", "-jar", "/src/tech/devnet.jar"]

