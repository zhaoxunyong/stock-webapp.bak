#!/bin/sh
export PATH="/usr/local/bin:/usr/bin:$JAVA_HOME/bin:$MVN_HOME/bin:$PATH"

sedi() {
  case $(uname) in
    Darwin*) sedi=('-i' '') ;;
    *) sedi='-i' ;;
  esac

  sed "${sedi[@]}" "$@"
}

# script replace, don't delete.
#cd #{project}

NEW_BRANCH=$1

arr=(${NEW_BRANCH//./ })
NEW_VERSION=${arr[0]}.${arr[1]}.0-SNAPSHOT

function SwitchBranch() {
    branchVersions=$1
    git add .
    git commit -m "Commit by new branch:${NEW_BRANCH}."
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

function Push() {
    branchVersions=$1
    git add .
    git commit -m "Mod New branch version to ${branchVersions}"
    git push origin ${branchVersions}
    if [[ $? != 0 ]]; then
        echo "Push ${branchVersions} error."
        exit -1
    fi
    echo "Push ${branchVersions} successful."
}

SwitchBranch $NEW_BRANCH
bash changeVersion.sh $NEW_VERSION
Push $NEW_BRANCH