@echo off
call mvn clean package
call docker build -t dmit2015/dmit2015-project-rest-client-bentam .
call docker rm -f dmit2015-project-rest-client-bentam
call docker run -d -p 9080:9080 -p 9443:9443 --name dmit2015-project-rest-client-bentam dmit2015/dmit2015-project-rest-client-bentam