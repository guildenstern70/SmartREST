FROM adoptopenjdk:14-hotspot-bionic
MAINTAINER Alessio Saltarin <alessiosaltarin@gmail.com>
USER root

# Update
RUN apt-get update \
    && apt-get install -y curl \
    && apt-get install -y apt-utils \
    && apt-get -y autoclean

# Add jar and run app
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
