@echo off
call mvn clean package
call docker build -t dmit2015/dmit-2015 .
call docker rm -f dmit-2015
call docker run -d -p 9080:9080 -p 9443:9443 --name dmit-2015 dmit2015/dmit-2015