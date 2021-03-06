#!/bin/sh
if [ $(docker ps -a -f name=dmit2015-assignment03-client-bentam | grep -w dmit2015-assignment03-client-bentam | wc -l) -eq 1 ]; then
  docker rm -f dmit2015-assignment03-client-bentam
fi
mvn clean package && docker build -t dmit2015/dmit2015-assignment03-client-bentam .
docker run -d -p 9080:9080 -p 9443:9443 --name dmit2015-assignment03-client-bentam dmit2015/dmit2015-assignment03-client-bentam
