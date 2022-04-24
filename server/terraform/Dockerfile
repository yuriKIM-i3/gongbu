FROM node:14-bullseye-slim

RUN apt update && apt install -y sudo zip unzip sudo wget curl less adduser vim git

RUN curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
RUN unzip awscliv2.zip
RUN ./aws/install

ENV TF_VERSION 1.1.3
RUN wget https://releases.hashicorp.com/terraform/${TF_VERSION}/terraform_${TF_VERSION}_linux_amd64.zip
RUN unzip -o terraform_${TF_VERSION}_linux_amd64.zip

RUN mv terraform /usr/local/bin/
RUN terraform --version