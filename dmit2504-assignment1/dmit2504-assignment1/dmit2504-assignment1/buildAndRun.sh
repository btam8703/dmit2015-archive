#!/bin/sh
if [ $(docker ps -a -f name=dmit2504-assignment1 | grep -w dmit2504-assignment1 | wc -l) -eq 1 ]; then
  docker rm -f dmit2504-assignment1
fi
mvn clean package && docker build -t dmit2504/dmit2504-assignment1 .
docker run -d -p 9080:9080 -p 9443:9443 --name dmit2504-assignment1 dmit2504/dmit2504-assignment1
