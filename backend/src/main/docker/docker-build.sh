#!/bin/sh
registry=192.168.100.87:5000
version=1.4.0-SNAPSHOT

mvn clean install -Dmaven.test.skip=true

docker build -t "${registry}/hkcash/employee-server:${version}" .

docker push ${registry}/hkcash/employee-server:${version}
