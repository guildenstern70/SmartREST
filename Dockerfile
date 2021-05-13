FROM adoptopenjdk:14-hotspot-bionic

# Update
RUN apt-get update \
    && apt-get install --no-install-recommends -y curl=7.58.* \
    && apt-get install --no-install-recommends -y apt-utils=1.6.* \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

# Add jar and run app
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
