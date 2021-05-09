###########Compile Stage###########

FROM openjdk:8-jdk-slim as compile

# Copying file GossipServer.java
ADD ./GossipServer.java .

# Compiling the GossipServer
RUN javac GossipServer.java

###########RUN Stage###########

FROM openjdk:8u292-jre-slim-buster as execute

# Environment Variable for the PORT
ENV PORT=3000

# Create working dir for the server
WORKDIR /server

# Copying the GossipServer.class file from compile stage
COPY --from=compile GossipServer.class .

# Point to mount the volume
VOLUME [ "/server/serverdata" ]

# Run the GossipServer by default
CMD java GossipServer ${PORT}
