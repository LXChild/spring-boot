FROM openjdk:8-jdk-alpine

MAINTAINER lxchid@outlook.com
EXPOSE 18080
ADD target/springboot2_0-0.0.1.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar