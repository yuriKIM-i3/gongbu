FROM gradle:7.2.0-jdk16-openj9 
ENV APP_HOME=/usr/app/

#디폴트, 만약 도커컴포즈 실행시 build.arg에 정의한 ARG가 없을때
ARG UID=1001
ARG GID=1001
ARG UNAME=1001

ENV UID ${UID}
ENV GID ${GID}
ENV UNAME ${UNAME}

RUN useradd --uid $UID --create-home --shell /bin/bash -G sudo,root $UNAME
RUN echo '%sudo ALL=(ALL) NOPASSWD:ALL' >> /etc/sudoers
    