#!/bin/sh
pids=$(ps aux|grep  stock-webapp|grep -v grep|awk '{print $2}')
for pid in $pids
do
   kill $pid
done
