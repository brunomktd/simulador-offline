FROM maven:3.6.0-jdk-11-slim AS builder

ARG ENVIRONMENT

RUN mkdir app
WORKDIR /app
COPY . /app
RUN mvn clean package -P ${ENVIRONMENT}

FROM openjdk:11.0.1-jre-slim-stretch

ARG ENVIRONMENT

RUN mkdir app
WORKDIR /app

COPY --from=builder /app/target/offline-0.0.1-SNAPSHOT.jar /app/offline-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "-Dserver.port=80", "offline-0.0.1-SNAPSHOT.jar"]