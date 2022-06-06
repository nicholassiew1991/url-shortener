FROM maven:3.8.5-eclipse-temurin-17 AS build

WORKDIR /code
COPY pom.xml ./
COPY src ./src/

RUN mvn package spring-boot:repackage

FROM eclipse-temurin:17-jre-alpine

COPY --from=build /code/target/url-shortener-api-0.0.1.jar /app/

EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "/app/url-shortener-api-0.0.1.jar"]