FROM openjdk:21-slim
VOLUME /tmp
ARG JAR_FILE=target/Ryde-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]