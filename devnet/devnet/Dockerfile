FROM adoptopenjdk:17-jdk-hotspot AS build
RUN mkdir -p /src/tech
WORKDIR /src/tech
COPY . .
RUN mvn package -DskipTests

FROM adoptopenjdk:17-jre-hotspot
WORKDIR /src/tech
COPY --from=build target/devnet-0.0.1-SNAPSHOT.jar /src/tech/devnet.jar

ENV SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/techchallenge
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=postgres

CMD ["java", "-jar", "/src/tech/devnet.jar"]

EXPOSE 8081

