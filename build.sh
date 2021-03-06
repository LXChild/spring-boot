#!/bin/bash

MYIMAGE=lxchild/springboot2_0
MY_CONTAINER=lxchild
# uncomment if you need push
#docker login 192.168.1.2:8082 -u admin -p admin123

echo "stop all container"
if docker ps -a | grep -i ${MY_CONTAINER}
then
    echo "find" ${MY_CONTAINER}
    docker kill ${MY_CONTAINER}
fi
echo "remove all container"
docker rm ${MY_CONTAINER}

echo "remove old images"
#docker images | grep ${MYIMAGE} | awk '{print $3}' | xargs docker rmi -f
docker rmi ${MYIMAGE}

echo "build jar and image"
docker build . -t ${MYIMAGE}

echo "running container"
docker run -it -dp 18080:18080 --name ${MY_CONTAINER} ${MYIMAGE}

# push image
# docker push ${MYIMAGE}