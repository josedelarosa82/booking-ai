FROM openjdk:11-slim

ARG JAR_FILE
COPY ${JAR_FILE} /app/app.jar

EXPOSE 9001
ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-Dspring.profiles.active=dev", "-jar","/app/app.jar"]