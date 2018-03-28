#!/bin/sh
mkdir -p /data/sql/
mysqldump -uroot -pAa654321 --default-character-set=utf8 --routines wenchun > /data/sql/db-`date +%Y%m%d%H%M`.sql
