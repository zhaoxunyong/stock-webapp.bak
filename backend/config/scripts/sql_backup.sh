#!/bin/sh
SQLPATH=/works/app/stock-webapp/sql
mkdir -p $SQLPATH 
mysqldump -uroot -pAa654321 --default-character-set=utf8 --routines wenchun > $SQLPATH/db-`date +%Y%m%d%H%M`.sql
