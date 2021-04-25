#!/bin/sh
if [ $(docker ps -a -f name=dmit2015-webclient-demo-student | grep -w dmit2015-webclient-demo-student | wc -l) -eq 1 ]; then
  docker rm -f dmit2015-webclient-demo-student
fi
mvn clean package && docker build -t dmit2015/dmit2015-webclient-demo-student .
docker run -d -p 9080:9080 -p 9443:9443 --name dmit2015-webclient-demo-student dmit2015/dmit2015-webclient-demo-student
