#!/bin/sh
NEW_VERSION=$1
mvn versions:set -DnewVersion=${NEW_VERSION}
mvn versions:commit

sed -i "s;^version=.*;version=${NEW_VERSION};" docker-build.sh
sed -i "s;^version=.*;version=${NEW_VERSION};" docker-create.sh
sed -i "s;^ENV VERSION .*;ENV VERSION ${NEW_VERSION};" Dockerfile
sed -i "s;version=.*;version=${NEW_VERSION};" src/main/resources/version.properties