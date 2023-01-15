FROM openjdk:17-jdk-alpine3.14
RUN mkdir -p /var/log/jvmlogs
ARG JAR_FILE=build/libs/oome-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app.jar"]
