#!/bin/sh
pids=$(ps aux|grep  employee-server-1.0.0-SNAPSHOT.jar|grep -v grep|awk '{print $2}')
for pid in $pids
do
   kill $pid
done
