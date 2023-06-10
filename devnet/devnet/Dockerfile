FROM eclipse-temurin:17.0.7_7-jdk-jammy

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} devnet.jar

ENTRYPOINT ["java", "-jar", "/devnet.jar"]

