#!/bin/sh
export PATH="/usr/local/bin:/usr/bin:$JAVA_HOME/bin:$MVN_HOME/bin:$PATH"

registry=192.168.100.87:5000
version=1.0.0-SNAPSHOT
appname=stock-webapp
port=8081

docker pull ${registry}/${appname}:${version}

docker run --log-driver json-file --log-opt max-size=100m --log-opt max-file=3 \
 -h ${appname} --name ${appname}-${version} -p ${port}:${port} \
 -v /works/log/stock-webapp:/works/log/stock-webapp \
 ${registry}/${appname}:${version}

