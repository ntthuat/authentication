FROM openjdk:8-jdk-alpine

WORKDIR /tmp
COPY ./build/libs/billing.jar app.jar

ENV JAVA_OPTS=""
ENV SPRING_PROFILE="default"
RUN apk add --no-cache ttf-dejavu
ENTRYPOINT exec java $JAVA_OPTS \
  -Djava.security.egd=file:/dev/./urandom \
  -Dspring.profiles.active=$SPRING_PROFILE \
  -jar app.jar
