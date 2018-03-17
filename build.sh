#!/bin/bash

MYIMAGE=lxchild/springboot2_0

# uncomment if you need push
#docker login 192.168.1.2:8082 -u admin -p admin123

# stop all container
docker kill $(docker ps -aq)

# remove all container
docker rm $(docker ps -aq)

# remove old images
docker images | grep ${MYIMAGE} | awk '{print $3}' | xargs docker rmi

# build jar and image
mvn package -e -X docker:build -DskipTest

# running container
docker run -dp 8080:8080 --name ${MYIMAGE} ${MYIMAGE}

# push image
#docker push ${MYIMAGE}