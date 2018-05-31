#!/bin/sh
echo "find ganesha-front-wap-0.0.1-SNAPSHOT-exec.jar process and kill"

PROCESS=`ps -ef|grep "ganesha-front-wap-0.0.1-SNAPSHOT-exec.jar"|grep -v grep|grep -v PPID|awk '{ print $2}'`
for i in $PROCESS
do
    echo "Kill process [ $i ]"
    kill -9 $i
done

echo "ganesha-front-wap start .... "
echo "please wait a moment...."

nohup java -Dspring.output.ansi.enabled=always -Djava.security.egd=file:/dev/./urandom -jar ./target/ganesha-front-wap-0.0.1-SNAPSHOT-exec.jar >> ganesha_front_wap_nohup.out &

echo "ganesha-front-wap end"
