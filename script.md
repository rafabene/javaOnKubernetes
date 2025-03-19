# Preparar para a apresentação

./compilar.sh

## SENHA:

export QUARKUS_CONTAINER_IMAGE_PASSWORD=

# Cluster

oc login
oc new-project mydemo

#### Demo alocação de memória

# Executar e analisar comportamento da execução

jconsole& ; java Main

# Mostrar os efeitos de ajuste de Xmx e Mms

jconsole& ; java -Xmx2G -Xms50m Main

#### Demo GC

# SerialGC

jconsole& ; java -Xmx512m -XX:+UseSerialGC Main

# ParalleGC

jconsole& ; java -Xmx512m -XX:+UseParallelGC Main

# G1

jconsole& ; java -Xmx512m -XX:+UseG1GC Main

# ZGC

jconsole& ; java -Xmx512m -XX:+UseZGC Main

#### Demo Java 21 em containers

## Mostrar como o Java se comporta com diferentes combinações de CPU e memória

# Java 21 - Funciona perfeitamente

./java21.sh 2 100m

# Java 8 - OQ mudou? GC, CPU e memória

./java8.sh 2 100m

# Java 9 - Mesmo problema. Só muda o GC para G1

./java9.sh 2 100m

### Teste de OOMKilled

./memory.sh

### Teste no Kubernetes

# Deploy

mvn package -Dquarkus.openshift.deploy=true
