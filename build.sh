#!/bin/sh
cd frontend
mvn clean install -Dmaven.test.skip=true
if [[ $? == 0 ]]; then
  echo "frontend build error."
  exit -1
fi
cd ../backend
mvn clean install -Dmaven.test.skip=true
