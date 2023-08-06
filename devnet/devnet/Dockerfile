FROM maven:3.8.3-openjdk-17-slim AS build
WORKDIR /workspace
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17.0.7_7-jdk-jammy
WORKDIR /techchallenge
COPY --from=build /workspace/target/devnet-0.0.2.jar devnet.jar
VOLUME /app/armazem
ENTRYPOINT ["java", "-jar", "/techchallenge/devnet.jar"]

