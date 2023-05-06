#!/bin/bash

DELAY=10

#docker compose --file mongo-replicaset.yml down
#docker rm -f $(docker ps -a -q)
#docker volume rm $(docker volume ls -q)

docker compose --file mongo-replicaset.yml up -d

echo "****** Waiting for ${DELAY} seconds for containers to go up ******"
sleep $DELAY

docker exec mongo1 /scripts/rs-init.sh

#mongodb://uber:uber123@localhost:27017/authSource=admin