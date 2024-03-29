## Smart REST (Kotlin Edition)

[![MIT license](http://img.shields.io/badge/license-MIT-brightgreen.svg)](http://opensource.org/licenses/MIT)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/f1c670fa6a0444919661480b82ab73cb)](https://www.codacy.com/gh/guildenstern70/SmartREST/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=guildenstern70/SmartREST&amp;utm_campaign=Badge_Grade)
![Actions CI](https://github.com/guildenstern70/SmartREST/actions/workflows/gradle.yml/badge.svg)


A template REST API project with

* SpringBoot (Kotlin)
* JPA (H2 DB in memory)
* API REST with OpenAPI 3.0 documentation
* Apache FreeMarker Template

Are you looking for a pure Java implementation? Try https://github.com/guildenstern70/smartrestjava

### Build as Docker image

    gradle clean assemble
    docker build --platform linux/amd64 --build-arg JAR_FILE=build/libs/SmartRest.jar -t smartrest:1.0 .

### Tag image to be uploaded to a repository

    docker tag smartrest:1.0 docker.io/[your_user]/smartrest:1.0
    
### Run docker image

    docker run --publish 8080:8080 --name SmartRest smartrest:1.0   
    
### Install to Kubernetes using Helm

The provided Helm deploys on Kubernetes using one pod exposed by a service through NodePort

    helm install --generate-name ./helm
    
To browse application get your k8s provider public IP, example with IBM Cloud 

    ibmcloud ks worker ls --cluster <cluster_name>
    
and point browser to

    http(s)://<public_ip>:<node_port>
    
    
    

