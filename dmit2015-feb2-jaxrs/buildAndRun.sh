#!/bin/sh
if [ $(docker ps -a -f name=dmit-2015 | grep -w dmit-2015 | wc -l) -eq 1 ]; then
  docker rm -f dmit-2015
fi
mvn clean package && docker build -t dmit2015/dmit-2015 .
docker run -d -p 9080:9080 -p 9443:9443 --name dmit-2015 dmit2015/dmit-2015
