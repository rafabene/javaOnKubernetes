#!/bin/bash

# Verifica se os parâmetros foram passados corretamente
if [ "$#" -ne 2 ]; then
    echo "Uso: $0 <NUM_CPUS> <MEMORIA>"
    echo "Exemplo: $0 2 512m (para 2 CPUs e 512MB de memória)"
    exit 1
fi

NUM_CPUS=$1
MEMORY=$2
IMAGE="docker.io/library/openjdk:21"

# Nome do contêiner
CONTAINER_NAME="openjdk21_memory"

# Executar o Podman com os parâmetros definidos
podman run -it  \
    -v `pwd`:/java  -w /java \
    --cpus=$NUM_CPUS \
    --memory=$MEMORY \
    --name $CONTAINER_NAME \
    $IMAGE  java -XX:+PrintFlagsFinal -Xmx500m Main
