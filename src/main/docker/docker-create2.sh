#!/bin/sh
registry=192.168.100.87:5000
version=1.0.0-SNAPSHOT

docker pull ${registry}/hkcash/employee-server:${version}

docker run -d --net=hkcash --name employee-server -p 8078:8078 \
 -v /works/app/hkapp/employee-server:/app \
 -v /works/app/hkapp/employee-server:/config \
 -v /var/log/aeasycredit/hkcash/employee-server:/works/log/hkcash/employee-server \
 ${registry}/hkcash/employee-server:${version}