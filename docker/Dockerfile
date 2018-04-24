FROM alpine

RUN apk update
RUN apk add \
  sl \
  openjdk8 \
  python2 python3 \
  gcc libc-dev linux-headers \
  g++ \
  rust cargo \
  bash

ENV PATH="/usr/lib/jvm/java-1.8-openjdk/bin:${PATH}"

# Define working directory.
WORKDIR /sandbox

# Define default command.
CMD ["bash"]
