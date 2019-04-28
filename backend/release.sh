#!/bin/sh
export PATH="/usr/local/bin:/usr/bin:$JAVA_HOME/bin:$MVN_HOME/bin:$PATH"


sedi() {
  case $(uname) in
    Darwin*) sedi=('-i' '') ;;
    *) sedi='-i' ;;
  esac

  sed "${sedi[@]}" "$@"
}

echo "Please exec from Git Bash"
branchVersion=$1
newTagDate=$2
if [[ "$newTagDate" == "" ]]; then
	newTagDate=`date +%Y%m%d%H%M`
fi

if [[ "$branchVersion" == "" || ($branchVersion != *.release && $branchVersion != *.test && $branchVersion != *.hotfix) ]]; then
  # echo "branchVersion must be not empty!"
  echo "Usage: $0 BranchVersion newTagDate"
  echo "$0 1.0.0.release 201802271230"
  echo "$0 1.0.0.htofix 201802271230"
  exit -1
fi

# script replace, don't delete.
#cd #{project}

pushBranch=true
pushTag=true
mvnVersion=$(echo $branchVersion|sed 's;\.test;;'|sed 's;\.release;;'|sed 's;\.hotfix;;')
#release or test
tagFlag=$(echo $branchVersion|sed "s;$mvnVersion;;"|sed "s;^\.;;")

CURR_PWD=`pwd`

#newTag=${mvnVersion}.${tagFlag}-`date +%Y%m%d%H%M`
newTag=${mvnVersion}.${tagFlag}-${newTagDate}

echo "branchVersion: ${branchVersion}"
echo "mvnVersion: ${mvnVersion}"
echo "tagFlag: ${tagFlag}"
echo "newTag: ${newTag}"

function SwitchBranch() {
    branchVersions=$1
    git add .
    git commit -m "Commit by switch branch."
    git pull &> /dev/null
    git checkout -b ${branchVersions} > /dev/null
    if [[ $? != 0 ]]; then
        git checkout ${branchVersions} > /dev/null
        if [[ $? != 0 ]]; then
            echo "Switch branch to ${branchVersions} error."
            exit -1
        fi
    fi
    echo "Switch branch to ${branchVersions} successful."
    git branch
}

function Tag() {
	if [[ "$tagFlag" != "" ]]; then
		pushTags=$1
	    git tag -a $newTag -m "version ${newTag}"
	    if [[ $? != 0 ]]; then
	      echo "Tag error!"
	      exit -1
	    else
	      echo "Tag to ${newTag} successful!"
	      if [[ "${pushTags}" == "true" ]]; then
	        git push origin ${newTag}
	      fi
	    fi
	fi
    
}

function Push() {
    branchVersions=$1
    pushBranchs=$2
    if [[ "${pushBranchs}" == "true" ]]; then
        git add .
        git commit -m "Mod Release version to ${branchVersions}"
        git push origin ${branchVersions}
        if [[ $? != 0 ]]; then
            echo "Push ${branchVersions} error."
            exit -1
        fi
        echo "Push ${branchVersions} successful."
    fi
}

function isNewBranchs() {
	oldVersions=$1
	mvnVersions=$2
	oldArr=(${oldVersions//./ })
	newArr=(${mvnVersions//./ })
	# 新的迭代或者大版本变更，需要切换分支到1.x.x
	if [[ "${oldArr[0]}" != "${newArr[0]}" || "${oldArr[1]}" != "${newArr[1]}" ]]; then
	  return 1
	else
	  return 0
	fi
}


# Change version
oldVersion=`cat pom.xml|grep "<version>"|sed -n "1p"|sed "s;^\s<version>;;"|sed "s;</version>;;"`
echo "old version is ${oldVersion}"
isNewBranchs ${oldVersion} ${mvnVersion}
isNewBranch=$?
if [[ "${mvnVersion}" == "${oldVersion}" ]]; then
  echo "${mvnVersion} = ${oldVersion}, do nothing."
  exit -1
else
  # 新的迭代或者大版本变更，需要切换分支到1.x.x
  if [[ ${isNewBranch} == 1 ]]; then
    newArr=(${mvnVersion//./ })
    newBranchVersion="${newArr[0]}.${newArr[1]}.x"
    #echo "Pushing current branch to gitlab..."
    #Push ${branchVersion} ${pushBranch}
    echo "Starting switch new branch to ${newBranchVersion}..."
    #Switch branch to ${newBranchVersion}
    SwitchBranch ${newBranchVersion}
  fi
  currentBranchVersion=`git branch|grep "*"|sed 's;^* ;;'`
  #Switch branch to ${branchVersion}
  SwitchBranch ${branchVersion}
  #Modify version
  bash changeVersion.sh ${mvnVersion}
  Push ${branchVersion} ${pushBranch}
  Tag ${pushTag}
fi
git checkout $currentBranchVersion
echo "--------------------------------------------------"

cd $CURR_PWD
echo "---------->result<----------"
echo "Branch version: ${branchVersion}"
echo "Tag version: ${newTag}"
echo "Project version: ${mvnVersion}"

#echo "--------------------------------------------------"
#echo "Starting merge code from $branchVersion to master......"
#cp -a merge.sh /tmp/
#sedi "s;#cd #{project};cd $CURR_PWD;" /tmp/merge.sh
#bash /tmp/merge.sh $branchVersion master

if [[ $? == 0 && "$tagFlag" != "hotfix" ]]; then
  echo "--------------------------------------------------"
  arr=(${mvnVersion//./ })
  newVersion=${arr[0]}.${arr[1]}.$((${arr[2]}+1))-SNAPSHOT
  echo "Starting change version to $newVersion....."
  cp -a changeVersion.sh /tmp/
  sedi "s;#cd #{project};cd $CURR_PWD;" /tmp/changeVersion.sh
  bash /tmp/changeVersion.sh $newVersion > /dev/null
  Push ${currentBranchVersion} ${pushBranch}
fi