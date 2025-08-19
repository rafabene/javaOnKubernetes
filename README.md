# Java em Containers - Guia Prático

Este guia tem como objetivo demonstrar como o Java se comporta dentro de containers, abordando alocação de memória, diferentes Garbage Collectors (GCs), e o impacto de diferentes versões do Java.

---

## 1. Preparação para a Apresentação

### Preparação do Ambiente

Antes de iniciar os testes, prepare o ambiente Podman:

```sh
./prepare-env.sh
```

### Compilação do Projeto

Antes de iniciar os testes, compile o projeto:

```sh
./compilar.sh
```

### Definir Senha para o Container

Configure a senha do container:

```sh
export QUARKUS_CONTAINER_IMAGE_PASSWORD=<SUA_SENHA>
```

---

## 2. Configuração do Cluster Kubernetes

Para rodar os testes no OpenShift/Kubernetes:

### Login no Cluster

```sh
oc login
```

### Criar um Novo Projeto

```sh
oc new-project mydemo
```

---

## 3. Demonstração de Alocação de Memória

Testes de comportamento da JVM com diferentes alocações de memória:

### Executar e Analisar o Comportamento Padrão

```sh
jconsole& ; java Main
```

### Testar Ajustes de Xmx e Xms

```sh
jconsole& ; java -Xmx2G -Xms50m Main
```

---

## 4. Demonstração de Garbage Collectors (GCs)

### Serial GC (Monothread, adequado para pequenas aplicações)

```sh
jconsole& ; java -Xmx512m -XX:+UseSerialGC Main
```

### Parallel GC (Paraleliza a coleta de lixo, melhor para aplicações multi-thread)

```sh
jconsole& ; java -Xmx512m -XX:+UseParallelGC Main
```

### G1 GC (Equilibra tempo de pausa e throughput, padrão a partir do Java 9)

```sh
jconsole& ; java -Xmx512m -XX:+UseG1GC Main
```

### ZGC (GC de baixa latência, ideal para aplicações que exigem baixa interrupção)

```sh
jconsole& ; java -Xmx512m -XX:+UseZGC Main
```

---

## 5. Testando Diferentes Versões do Java em Containers

### Java 25 - Execução sem problemas

```sh
./java25.sh 2 100m
```


### Java 21 - Execução sem problemas

```sh
./java21.sh 2 100m
```

### Java 8 - Mudanças em GC, CPU e memória

```sh
./java8.sh 2 100m
```

### Java 9 - Mesmo problema que o Java 8, mas com GC G1

```sh
./java9.sh 2 100m
```

---

## 6. Testando OOMKilled (Out of Memory)

Simular um cenário de estouro de memória:

```sh
./memory.sh
```

---

## 7. Testes no Kubernetes

### Deploy da Aplicação com Quarkus no OpenShift

```sh
cd demo/
mvn package -Dquarkus.openshift.deploy=true
```
