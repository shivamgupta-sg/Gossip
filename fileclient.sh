#!/bin/bash
#
# SCRIPT: fileclient.sh
# AUTHOR: shivam
# DATE:   09-may-21
# REV:    1.1.A (Valid are A, B, D, T and P)
#          (For Alpha, Beta, Dev, Test and Production)
#
#
# PLATFORM: Linux
# 
# PURPOSE: To build client image, create clientvol 
#          and run the container (running the application by default) 

################################################################
#          Beginning of Main                                   #
################################################################
cd client

docker build -t javaclient:test .

docker volume create clientvol

docker run -it --name javaclient --mount source=clientvol,target=/client/clientdata --network javabridge javaclient:test

# End of script
