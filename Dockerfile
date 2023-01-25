FROM openjdk:19-slim-buster

# Temp volume to save Tomcat temp files
VOLUME /tmp

# Entry point
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
