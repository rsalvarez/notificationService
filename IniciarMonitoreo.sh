#!/bin/bash

# Cargar variables del archivo y ejecutar java en una sola línea
#set -a
source ./configmap.env
#set +a



#java -jar target/replicacion-service-1.0.0.jar
java -jar -Dspring.profiles.active=prod target/replicacion-service-1.0.0.jar

