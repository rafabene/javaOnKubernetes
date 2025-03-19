podman run -it -w /java -v `pwd`:/java --cpus 1 -m 100Mb openjdk:8 javac Main.java
