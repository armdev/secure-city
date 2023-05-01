#!/usr/bin/env bash

set -e

##mvn clean package -U -Dmaven.test.skip=true


docker-compose up -d --build
docker ps -a
docker logs --follow geo
