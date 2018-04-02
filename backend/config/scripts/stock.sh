#!/bin/sh
APPNAME=stock-webapp
VERSION=1.0.0-SNAPSHOT
APPPATH=/works/app/stock-webapp
CONFIG=$APPPATH
PROFILES=prod
java -Xms512m -Xmx512m -Xmn128m -jar ${APPPATH}/${APPNAME}-${VERSION}.jar --spring.config.location=${CONFIG} --spring.profiles.active=${PROFILES} -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:SurvivorRatio=3 -XX:CMSInitiatingOccupancyFraction=80 -XX:+PrintPromotionFailure -XX:+UseCMSCompactAtFullCollection -XX:MaxTenuringThreshold=8 -XX:+CMSClassUnloadingEnabled -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/data/app/heapdump.bin -agentlib:jdwp=transport=dt_socket,address=18082,server=y,suspend=n -Djava.security.egd=file:/dev/./urandom

#--spring.config.location=/works/conf/notify-server/application.yml --spring.profiles.active=test -Xms512m -Xmx512m -Xmn128m -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:SurvivorRatio=3 -XX:CMSInitiatingOccupancyFraction=80 -XX:+PrintPromotionFailure -XX:+UseCMSCompactAtFullCollection -XX:MaxTenuringThreshold=8 -XX:+CMSClassUnloadingEnabled -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/works/log/hkcash/notify-server/heapdump.bin -agentlib:jdwp=transport=dt_socket,address=18086,server=y,suspend=n -Djava.security.egd=file:/dev/./urandom
