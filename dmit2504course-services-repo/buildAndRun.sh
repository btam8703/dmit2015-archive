#!/bin/sh
if [ $(docker ps -a -f name=dmit2504course-services | grep -w dmit2504course-services | wc -l) -eq 1 ]; then
  docker rm -f dmit2504course-services
fi
mvn clean package && docker build -t dmit2504/dmit2504course-services .
docker run -d -p 9080:9080 -p 9443:9443 --name dmit2504course-services dmit2504/dmit2504course-services
