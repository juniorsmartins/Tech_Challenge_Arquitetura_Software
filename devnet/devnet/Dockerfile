FROM eclipse-temurin:17.0.7_7-jdk-jammy

WORKDIR /tech/challenge

COPY target/devnet-0.0.1.jar devnet.jar

VOLUME /app/armazem

ENTRYPOINT ["java", "-jar", "/tech/challenge/devnet.jar"]

