#!/bin/sh
registry=192.168.100.87:5000
version=$1
appname=$2
port=`cat Dockerfile |grep EXPOSE|sed "s;EXPOSE ;;"`
echo "appname=$appname"
echo "port=$port"
if [[ $version == ""  ]]; then
  echo "version must be not empty!"
  exit -1
fi

dockerPid=`docker ps -a |grep ${appname}-${version}|awk '{print $1}'`

if [[ $dockerPid == "" ]]; then
  # build docker
  sed -i "s;^ENV VERSION .*;ENV VERSION ${version};" Dockerfile
  docker build -t "${registry}/hkcash/${appname}:${version}" .
  docker push ${registry}/hkcash/${appname}:${version}

  # createdocker
  # stop old version
  allDockerPid=`docker ps |grep ${appname} | awk '{print $1}'`
  if [[ $allDockerPid != "" ]]; then
    for pid in $allDockerPid
    do
      docker stop $pid
    done
  fi
  sudo mkdir -p /works/log/hkcash

 docker run -d -h ${appname} --link eureka-server-${version}:eureka-server --name ${appname}-${version} -p ${port}:${port} \
 -v /works/app/hkapp/${appname}:/works/app/${appname} \
 -v /works/app/hkapp/${appname}:/works/conf/${appname} \
 -v /works/log/hkcash/${appname}:/works/log/hkcash/${appname} \
 ${registry}/hkcash/${appname}:${version}
else
  echo "docker restart ${appname}-${version}"
  docker restart ${appname}-${version}
fi
