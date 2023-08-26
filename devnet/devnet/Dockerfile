ARG WORKSPACE=techchallenge

FROM amazoncorretto:17.0.8-al2023 AS builder

ARG WORKSPACE
WORKDIR /$WORKSPACE

COPY ./.mvn ./.mvn
COPY ./mvnw ./
COPY ./pom.xml ./
RUN ./mvnw dependency:go-offline

COPY ./src ./src
RUN ./mvnw clean package -DskipTests


FROM amazoncorretto:17.0.8-al2023

ARG WORKSPACE
WORKDIR /$WORKSPACE
RUN mkdir ./logs

COPY --from=builder /$WORKSPACE/target/*.jar devnet.jar

ENV PORT 9001
EXPOSE $PORT

CMD ["java", "-jar", "devnet.jar"]

