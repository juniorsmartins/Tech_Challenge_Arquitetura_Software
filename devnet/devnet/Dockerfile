FROM eclipse-temurin:17.0.7_7-jdk-jammy
RUN apt-get update

MAINTAINER "Junior Martins"

WORKDIR /tech/challenge
COPY target/devnet.jar .

ENV TZ=UTC
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/mydatabase?serverTimezone=UTC
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=postgres

EXPOSE 8081

CMD ["java", "-jar", "devnet.jar"]



