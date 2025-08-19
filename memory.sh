#!/bin/bash

NUM_CPUS=2
MEMORY=100m
IMAGE="docker.io/library/openjdk:21"

# Nome do contêiner
CONTAINER_NAME="openjdk21_memory"

# Executar o Podman com os parâmetros definidos
podman run -it  \
    -v `pwd`:/java  -w /java \
    --cpus=$NUM_CPUS \
    --cpuset-cpus=0-$((NUM_CPUS - 1)) \
    --memory=$MEMORY \
    --name $CONTAINER_NAME \
    $IMAGE  java -Xmx500m Main
