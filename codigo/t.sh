#!/bin/bash

echo
echo "=============================================================================="
echo "Compiler lendo prog.in e gerando Teste.java"
java -cp antlr.jar:. Compiler Teste < prog.in

echo
echo "=============================================================================="
echo "compilando Teste.java"
javac Teste.java

echo
echo "=============================================================================="
echo "executando Teste"
echo
echo
java Teste