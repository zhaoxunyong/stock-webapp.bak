#!/bin/sh
SQLPATH=/works/app/stock-webapp/sql
mkdir -p $SQLPATH 
SQL_FILE=db-`date +%Y%m%d%H%M`.sql
mysqldump -uroot -pAa654321 --default-character-set=utf8 --routines wenchun > $SQLPATH/${SQL_FILE}
cd $SQLPATH
tar czvf ${SQL_FILE}.tgz ${SQL_FILE} && rm -fr ${SQL_FILE}

