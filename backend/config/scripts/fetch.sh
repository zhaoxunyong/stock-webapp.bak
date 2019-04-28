#!/bin/sh
#http://127.0.0.1:8082/api/stock/fetchAllHistory
#http://127.0.0.1:8082/api/stock/fetchHistory?no=2337

no=$1
if [[ $no == "" ]]; then
  curl -X POST "http://127.0.0.1:8082/api/stock/fetchAllHistory"
else
  curl -d "no=$no" "http://127.0.0.1:8082/api/stock/fetchHistory"
fi
