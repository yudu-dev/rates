FROM openjdk:17-alpine3.13
ARG APP_HOME=/app
WORKDIR $APP_HOME
COPY build/libs/*.jar $APP_HOME/app.jar

ENTRYPOINT java -jar app.jar $JAVA_OPTS