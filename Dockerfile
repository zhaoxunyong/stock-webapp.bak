# MAINTAINER        Jerry.zhao <jerry.zhao@aeasycredit.com>
# DOCKER-VERSION    17.05.0-ce
#
# Dockerizing eureka: Dockerfile for building eureka images

FROM 192.168.100.87:5000/jdk8:v144

#Dockerfile.jdk
ENV APP_CONFIG /works/conf/employee-server/
ENV APP_HOME ${APP_BASE}/employee-server
ENV LANG en_US.UTF-8

#Dockerfile.app
ENV APP_NAME employee-server
ENV VERSION 1.4.0-SNAPSHOT
ENV PROFILES docker

#COPY target/${APP_NAME}-${VERSION}.jar ${APP_HOME}/

EXPOSE 8078

ENTRYPOINT ["/scripts/run"]
