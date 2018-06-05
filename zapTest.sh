#!/usr/bin/env bash

docker run -ti --rm -v /var/run/docker.sock:/var/run/docker.sock -e "IMAGE=dvsba:latest" -e "PORT=80" -e "WAIT_LOG_MESSAGE=(?s).*Started DamnVulnerableSpringBootAppApplication.*" testcontainers-zap:latest