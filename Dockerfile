FROM maven:3.6.0-jdk-11-slim AS builder

RUN mkdir app
WORKDIR /app
COPY . /app
RUN mvn clean && mvn install

FROM openjdk:11.0.1-jre-slim-stretch

RUN mkdir app
WORKDIR /app

COPY --from=builder /app/target/offline-0.0.1.jar /app/offline.jar

CMD ["java", "-jar", "offline.jar"]