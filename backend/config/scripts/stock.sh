#!/bin/sh
APPNAME=stock-webapp
VERSION=1.0.0-SNAPSHOT
CONFIG=/data/app/
PROFILES=prod
java -Xms1024m -Xmx1024m -XX:MaxNewSize=128m -XX:MaxPermSize=128m -Djava.security.egd=file:/dev/./urandom -jar /data/app/${APPNAME}-${VERSION}.jar --spring.config.location=${CONFIG} --spring.profiles.active=${PROFILES}
