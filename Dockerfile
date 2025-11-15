FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY target/futbolclub-1.0.0.jar app.jar

EXPOSE 8888

ENV PORT=8888

ENTRYPOINT ["java", "-jar", "app.jar"]