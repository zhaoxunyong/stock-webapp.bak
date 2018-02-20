#!/bin/sh
registry=192.168.100.87:5000
version=1.4.0-SNAPSHOT

docker pull ${registry}/hkcash/employee-server:${version}

sudo mkdir -p /works/log/hkcash

docker run -d --net=hkcash --name employee-server -p 8078:8078 \
 -v /works/app/hkapp/employee-server:/works/app/employee-server \
 -v /works/app/hkapp/employee-server:/works/conf/employee-server \
 -v /works/log/hkcash/employee-server:/works/log/hkcash/employee-server \
 ${registry}/hkcash/employee-server:${version}
