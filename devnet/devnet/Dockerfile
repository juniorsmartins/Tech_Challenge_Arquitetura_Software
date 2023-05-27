FROM maven:3.8.5-openjdk-17 as buildado
RUN mkdir -p /src/app
WORKDIR /src/app
ADD . /src/app
RUN mvn package

FROM amazoncorretto:17.0.7
RUN mkdir -p /src/app
WORKDIR /src/app
COPY --from=buildado /src/app/target/devnet-0.0.1-SNAPSHOT.jar.original app.jar
CMD ["java", "-jar", "app.jar"]

EXPOSE 8080

