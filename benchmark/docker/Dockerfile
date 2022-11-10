FROM python:3.8

COPY rootfs /

RUN set -ex; apt-get update \
    && apt-get install -y --no-install-recommends \
        git \
        openjdk-11-jdk \
    && pip install numpy

# install vncserver
RUN set -ex; apt-get update \
 && export DEBIAN_FRONTEND=noninteractive \
 && apt-get install -y \
      supervisor \
      vnc4server \
      xfce4 \
      xfce4-goodies \
      xfce4-terminal \
      xserver-xorg-core \
      xterm \
      xvfb \
      x11vnc \
 && apt-get clean \
 && rm -rf /var/lib/apt/lists/* \
 && mkdir ~/.vnc \
 && /bin/bash -c "echo -e 'password\npassword\nn' | vncpasswd"
ENV TINI_VERSION v0.9.0
ADD https://github.com/krallin/tini/releases/download/${TINI_VERSION}/tini /bin/tini
RUN chmod +x /bin/tini

# clone AlloyMax
RUN git clone https://github.com/SteveZhangBit/alloy-maxsat-benchmark.git

WORKDIR /alloy-maxsat-benchmark

CMD ["bash"]
