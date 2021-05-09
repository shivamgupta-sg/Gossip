# Simple Chat Application

A simple chat application written in Java, that allows the server and client to chat with each other. The server and client can chat with each other and that conversation history will be saved on both.

## Usage

### Server

Create the server volume

```
docker volume create servervol
```

Create the user-defined network

```
docker network create --driver=bridge --subnet=172.10.0.0/16 javabridge
```

Run Server Container with running application by default

```
docker run -it --name javaserver --mount source=servervol,target=/server/serverdata --network javabridge javaserver:test
```

Run Server Container to go in container shell (not running the application by default)

```
docker run -it --name javaserver --mount source=servervol,target=/server/serverdata --network javabridge --ip 172.10.0.12 javaserver:test /bin/bash
```

### Client

Create the client volume

```
docker volume create clientvol
```

Run Client Container with running application by default

```
docker run -it --name javaclient --mount source=clientvol,target=/client/clientdata --network javabridge javaclient:test
```

Run Client Container to go in container shell (not running the application by default)

```
docker run -it --name javaclient --mount source=clientvol,target=/client/clientdata --network javabridge javaclient:test /bin/bash
```

Used volumes as the mount in the server and client. The volume will mount to a particular point(/server/serverdata and /client/clientdata for server and client respectively) on the container and the history of the conversation (conversation.txt) will be saved in the volume.

Used bridge network for the user defined network for the server and client. Given a subnet in configration of network and assign a IP to the server on which client will connect.
