FROM amazoncorretto:17.0.8-al2023 as builder

ARG WORKSPACE=techchallenge
WORKDIR /$WORKSPACE

COPY ./.mvn ./.mvn
COPY ./mvnw ./
COPY ./pom.xml ./
RUN ./mvnw dependency:go-offline

COPY ./src ./src
RUN ./mvnw clean package -DskipTests


FROM amazoncorretto:17.0.8-al2023

ARG WORKSPACE=techchallenge
WORKDIR /$WORKSPACE
RUN mkdir ./logs

COPY --from=builder /$WORKSPACE/target/*.jar devnet.jar

ENV PORT 9001
EXPOSE $PORT

CMD ["java", "-jar", "devnet.jar"]

