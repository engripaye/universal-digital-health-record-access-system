FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY target/universal-digital-health-record-access-system-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
