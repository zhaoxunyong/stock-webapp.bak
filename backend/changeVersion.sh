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

NEW_VERSION=$1
mvn versions:set -DnewVersion=${NEW_VERSION}
mvn versions:commit

#sedi "s;^version=.*;version=${NEW_VERSION};" docker-build.sh
#sedi "s;^version=.*;version=${NEW_VERSION};" docker-create.sh
sedi "s;^ENV VERSION .*;ENV VERSION ${NEW_VERSION};" Dockerfile
sedi "s;version=.*;version=${NEW_VERSION};" src/main/resources/version.properties