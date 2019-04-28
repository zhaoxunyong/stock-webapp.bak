#!/bin/sh
export PATH="/usr/local/bin:/usr/bin:$JAVA_HOME/bin:$MVN_HOME/bin:$PATH"

registry=192.168.100.87:5000
version=1.0.0-SNAPSHOT
appname=stock-webapp
port=8081

mvn clean install -Dmaven.test.skip=true

docker build -t "${registry}/${appname}:${version}" .

docker push ${registry}/${appname}:${version}

