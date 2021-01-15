#!/usr/bin/env bash
echo 'cd ...'
cd ...
pwd
echo 'start mvn package'
mvn clean package -Dmaven.test.skip=true