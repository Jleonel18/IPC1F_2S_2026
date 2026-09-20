#!/bin/bash
# Uso: ./compilar-y-correr.sh
mkdir -p out
javac -d out $(find src/demo -name "*.java") && java -cp out demo.controller.Main
