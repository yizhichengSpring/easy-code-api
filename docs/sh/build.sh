#!/usr/bin/env bash
echo 'start docker build'
echo 'docker stop easy-code'
docker stop easy-code
sleep 6
echo 'docker rm  easy-code'
docker rm easy-code
sleep 5
echo 'docker build -t easy-code/easy-code:v1 .'
docker build -t easy-code/easy-code:v1 .
sleep 12
echo 'docker run --name=easy-code -d -p 8181:8181 -e TZ=Asia/Shanghai easy-code/easy-code:v1'
docker run --name=easy-code -d -p 8181:8181 -e TZ=Asia/Shanghai easy-code/easy-code:v1

echo 'success'