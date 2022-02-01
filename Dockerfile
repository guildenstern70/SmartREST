FROM eclipse-temurin:17

# Update
RUN apt-get update \
    && apt-get install --no-install-recommends -y curl \
    && apt-get install --no-install-recommends -y apt-utils \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

# Temp volume to save Tomcat temp files
VOLUME /tmp

# Entry point
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
