FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY target/atiko-0.0.1-SNAPSHOT.war cafej.war
EXPOSE 3000
ENTRYPOINT exec java $JAVA_OPTS -war cafej.war
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar cafej.jar
