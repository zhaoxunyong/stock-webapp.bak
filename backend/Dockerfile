FROM java:8-jdk
RUN mkdir /app
WORKDIR /app

ENV APPNAME=stock-webapp \
    VERSION=1.0.0-SNAPSHOT

RUN ln -snf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo Asia/Shanghai > /etc/timezone

COPY target/${APPNAME}-${VERSION}.jar /app/

#ENTRYPOINT ["sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -jar /app/${APPNAME}-${VERSION}.jar --spring.config.location=${CONFIG} --spring.profiles.active=docker"]
ENTRYPOINT ["sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -jar /app/${APPNAME}-${VERSION}.jar"]
EXPOSE 8100
