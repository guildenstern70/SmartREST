## Smart REST 

A template project with

* SpringBoot
* JPA (H2 DB in memory)
* REST
* Apache FreeMarker Template

### Build as Docker image

    docker build --build-arg JAR_FILE=build/libs/SmartRest.jar -t smartrest:1.0 .
    
### Run docker image

    docker run --publish 8080:8080 --name SmartRest smartrest:1.0   
    
### Install to Kubernetes using Helm

    helm install --generate-name ./helm
