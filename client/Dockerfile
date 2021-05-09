###########Compile Stage###########

FROM openjdk:8-jdk-slim as compile

# Copying file GossipClient.java
ADD ./GossipClient.java .

# Compiling the GossipClient
RUN javac GossipClient.java

###########RUN Stage###########

FROM openjdk:8u292-jre-slim-buster as execute

# Environment Variable for the server IP and PORT
ENV SERVERIP=172.10.0.12 PORT=3000

# Create working dir for the client
WORKDIR /client

# Copying the GossipClient.class file from compile stage
COPY --from=compile GossipClient.class .

# Point to mount the volume
VOLUME [ "/client/clientdata" ]

# Run the GossipClient by default
CMD java GossipClient ${SERVERIP} ${PORT}
