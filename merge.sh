#!/bin/sh
echo "Please exec from Git Bash"
branchFromVersion=$1
branchToVersion=$2
isPushBranch=$3

currentbranchVersion=$(git branch|grep "^\*"|sed "s;^\*\s*;;")

if [[ "$branchFromVersion" == "" || "$branchToVersion" == "" ]]; then
  # echo "branchFromVersion and branchToVersion must be not empty!"
  echo "Usage: $0 branchFromVersion branchToVersion isPushBranch"
  echo "Example: $0 1.0.0.release master true"
  exit -1
fi

if [[ "$isPushBranch" == "" ]]; then
  isPushBranch="true"
fi

echo "branchFromVersion: ${branchFromVersion}"
echo "branchToVersion: ${branchToVersion}"
echo "isPushBranch: ${isPushBranch}"

#Merge
git checkout master
git merge origin/${branchFromVersion}
if [[ "$isPushBranch" == "true" ]]; then
  git push -u origin master
fi
git checkout ${currentbranchVersion}

cd $PWD
echo "----------Successfully----------"
