FROM eclipse-temurin:19-jdk-focal
MAINTAINER Alessio Saltarin <alessiosaltarin@gmail.com>

# Temp volume to save Tomcat temp files
VOLUME /tmp

# Entry point
ARG JAR_FILE=/build/libs/SmartRest.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
