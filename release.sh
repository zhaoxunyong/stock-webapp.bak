#!/bin/sh
echo "Please exec from Git Bash"
branchVersion=$1

if [[ "$branchVersion" == "" ]]; then
  # echo "branchVersion must be not empty!"
  echo "Usage: $0 BranchVersion TagFlag"
  echo "Branch only: $0 1.0.0"
  echo "Deploy test: $0 1.0.0.release test"
  echo "Deploy prod: $0 1.0.0.release release"
  exit -1
fi

tagFlag=$2
pushBranch=true
pushTag=true
mvnVersion=$(echo $branchVersion|sed 's;\.test;;'|sed 's;\.release;;'|sed 's;\.hotfix;;')
#release or test
newTag=
if [[ "$tagFlag" == "" ]]; then
	tagFlag=$(echo $branchVersion|sed "s;$mvnVersion;;"|sed "s;^\.;;")
fi

PWD=`pwd`

if [[ "$tagFlag" != "" ]]; then
	newTag=${mvnVersion}.${tagFlag}-`date +%Y%m%d%H%M`
fi

echo "branchVersion: ${branchVersion}"
echo "mvnVersion: ${mvnVersion}"
echo "tagFlag: ${tagFlag}"
echo "newTag: ${newTag}"

function ChangeVersion() {
    mvnVersion=$1
    echo "Changing version to ${mvnVersion}..."
    mvn versions:set -DnewVersion=${mvnVersion} > /dev/null
    if [[ $? != 0 ]]; then
      echo "Change version error!"
      exit -1
    else
      #Modify version
  	  sed -i "s;^version=.*;version=${mvnVersion};" docker-build.sh
      sed -i "s;^version=.*;version=${mvnVersion};" docker-create.sh
      sed -i "s;^ENV VERSION .*;ENV VERSION ${mvnVersion};" Dockerfile
      echo "Change version to ${mvnVersion} successful!"
    fi
}

function SwitchBranch() {
    branchVersion=$1
    git checkout -b ${branchVersion} > /dev/null
    if [[ $? != 0 ]]; then
        git checkout ${branchVersion} > /dev/null
        if [[ $? != 0 ]]; then
            echo "Switch branch to ${branchVersion} error."
            exit -1
        fi
    fi
    echo "Switch branch to ${branchVersion} successful."
    git branch
}

function Tag() {
	if [[ "$tagFlag" != "" ]]; then
		pushTag=$1
	    git tag -a $newTag -m "version ${newTag}"
	    if [[ $? != 0 ]]; then
	      echo "Tag error!"
	      exit -1
	    else
	      echo "Tag to ${newTag} successful!"
	      if [[ "${pushTag}" == "true" ]]; then
	        git push origin ${newTag}
	      fi
	    fi
	fi
    
}

function Push() {
    branchVersion=$1
    pushBranch=$2
    if [[ "${pushBranch}" == "true" ]]; then
        git add .
        git commit -m "Mod Release version to ${branchVersion}"
        git push origin ${branchVersion}
        if [[ $? != 0 ]]; then
            echo "Push ${branchVersion} error."
            exit -1
        fi
        echo "Push ${branchVersion} successful."
    fi
}

#Switch branch to ${branchVersion}
SwitchBranch ${branchVersion}
# Change version
oldVersion=`cat pom.xml|grep "<version>"|sed -n "1p"|sed "s;^\s<version>;;"|sed "s;</version>;;"`
echo "old version is ${oldVersion}"
if [[ "${mvnVersion}" == "${oldVersion}" ]]; then
  echo "${mvnVersion} = ${oldVersion}, do nothing."
else
  ChangeVersion ${mvnVersion}
  mvn versions:commit > /dev/null
  sed -i "s;version=.*;version=${newTag};" src/main/resources/version.properties
  #Modify version
  Push ${branchVersion} ${pushBranch}
  Tag ${pushTag}
fi
echo "--------------------------------------------------"

cd $PWD
echo "---------->result<----------"
echo "Branch version: ${branchVersion}"
echo "Tag version: ${newTag}"
echo "Project version: ${mvnVersion}"
