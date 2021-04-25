#!/bin/sh
if [ $(docker ps -a -f name=dmit2015-assignment05-bentam | grep -w dmit2015-assignment05-bentam | wc -l) -eq 1 ]; then
  docker rm -f dmit2015-assignment05-bentam
fi
mvn clean package && docker build -t ca.nait.dmit/dmit2015-assignment05-bentam .
docker run -d -p 9080:9080 -p 9443:9443 --name dmit2015-assignment05-bentam ca.nait.dmit/dmit2015-assignment05-bentam
