# FROM openjdk:17-jdk-alpine
FROM openjdk:17-jdk

EXPOSE 8080

ENV JAVA_OPTS \
    -Xmx64m \
    -XX:+HeapDumpOnOutOfMemoryError \
    -XX:HeapDumpPath=/logs/

ADD ./build/libs/spring-outofmemory-test-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar app.jar" ]
