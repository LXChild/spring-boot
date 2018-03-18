#!/bin/bash

MYIMAGE=lxchild/springboot2_0
MY_CONTAINER=lxchild
# uncomment if you need push
#docker login 192.168.1.2:8082 -u admin -p admin123

# stop all container
if [docker ps -a | grep -i ${MY_CONTAINER}]
then
    docker kill ${MY_CONTAINER}
fi
# remove all container
docker rm ${MY_CONTAINER}

# remove old images
docker images | grep ${MYIMAGE} | awk '{print $3}' | xargs docker rmi -f

# build jar and image
mvn clean package -e -X docker:build -DskipTest

# running container
docker run -it -dp 18080:18080 --name ${MY_CONTAINER} ${MYIMAGE}

# push image
# docker push ${MYIMAGE}