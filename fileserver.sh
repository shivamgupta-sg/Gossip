#!/bin/bash
#
# SCRIPT: fileserver.sh
# AUTHOR: shivam
# DATE:   09-may-21
# REV:    1.1.A (Valid are A, B, D, T and P)
#          (For Alpha, Beta, Dev, Test and Production)
#
#
# PLATFORM: Linux
# 
# PURPOSE: To build server image, create servervol, create user defined bridge network with subnet 
#          and run the container with particular ip (running the application by default) 

################################################################
#          Beginning of Main                                   #
################################################################
cd server

docker build -t javaserver:test .

docker volume create servervol

docker network create --driver=bridge --subnet=172.10.0.0/16 javabridge

docker run -it --name javaserver --mount source=servervol,target=/server/serverdata --network javabridge --ip 172.10.0.12 javaserver:test 

# End of script
