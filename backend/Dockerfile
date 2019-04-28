
FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
ADD target/stock-webapp-1.1.0-SNAPSHOT.jar backend.jar
EXPOSE 8082
#ENTRYPOINT exec java $JAVA_OPTS -jar backend.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar backend.jar
