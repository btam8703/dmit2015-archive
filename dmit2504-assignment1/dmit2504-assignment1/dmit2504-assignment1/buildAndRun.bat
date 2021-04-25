@echo off
call mvn clean package
call docker build -t dmit2504/dmit2504-assignment1 .
call docker rm -f dmit2504-assignment1
call docker run -d -p 9080:9080 -p 9443:9443 --name dmit2504-assignment1 dmit2504/dmit2504-assignment1