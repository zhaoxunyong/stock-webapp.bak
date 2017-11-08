#!/bin/sh
. /etc/profile
java -Xmx128m -Xms128m -Djava.security.egd=file:/dev/./urandom -jar stock-webapp-1.0.0-SNAPSHOT.jar --spring.profiles.active=dev > stock-webapp.log 2>&1
