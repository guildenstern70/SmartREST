## Smart REST 

A template project with

* SpringBoot
* JPA (H2 DB in memory)
* API REST with Swagger site
* Apache FreeMarker Template

### Build as Docker image

    docker build --build-arg JAR_FILE=build/libs/SmartRest.jar -t smartrest:1.0 .
    
### Run docker image

    docker run --publish 8080:8080 --name SmartRest smartrest:1.0   
    
### Install to Kubernetes using Helm

The provided Helm deploys on Kubernetes using one pod exposed by a service through NodePort

    helm install --generate-name ./helm
    
To browse application get your k8s provider public IP, example with IBM Cloud 

    ibmcloud ks worker ls --cluster <cluster_name>
    
and point browser to

    http(s)://<public_ip>:<node_port>
    
    
    

