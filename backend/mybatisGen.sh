#!/bin/sh
export PATH="/usr/local/bin:/usr/bin:$JAVA_HOME/bin:$MVN_HOME/bin:$PATH"

sedi() {
  case $(uname) in
    Darwin*) sedi=('-i' '') ;;
    *) sedi='-i' ;;
  esac

  sed "${sedi[@]}" "$@"
}

mvn mybatis-generator:generate