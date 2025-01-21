FROM openjdk:17-jdk
ARG JAR_FILE=./build/libs/*-SNAPSHOT.jar
WORKDIR /app
COPY ${JAR_FILE} app.jar

# application.yml 복사
COPY ./src/main/resources/application.yml /app/application.yml

ENTRYPOINT [ "java", "-jar", "/app/app.jar" ]