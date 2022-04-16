FROM maven:3.8.5-eclipse-temurin-18 AS build

RUN jlink --add-modules ALL-MODULE-PATH --strip-debug --no-man-pages --no-header-files --compress=2 --output /jre

WORKDIR /code
COPY pom.xml ./
COPY src ./src/

RUN mvn package spring-boot:repackage -DskipTests

FROM frolvlad/alpine-glibc:alpine-3.15

COPY --from=build /jre /jre
COPY --from=build /code/target/url-shortener-api-0.0.1.jar /app/

EXPOSE 8080
ENTRYPOINT [ "/jre/bin/java", "-jar", "-Dspring.profiles.active=dev", "/app/url-shortener-api-0.0.1.jar"]