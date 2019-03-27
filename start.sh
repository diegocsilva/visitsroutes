#!/bin/sh
path=$(pwd)
path_build=$path/docker-build
#INIT PROCESS
cd $path_build
echo 'BUILD ARTIFACTS'
docker-compose up
cd $path
echo 'INIT STACK VISITSROUTES'
docker-compose up -d