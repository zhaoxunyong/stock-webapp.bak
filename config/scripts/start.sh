#!/bin/sh
. /etc/profile
java -Xmx128m -Xms128m -Djava.security.egd=file:/dev/./urandom -jar employee-server-1.0.0-SNAPSHOT.jar --spring.profiles.active=dev > /var/log/aeasycredit/hkcash/employee-server-full.log 2>&1
