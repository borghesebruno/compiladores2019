#!/bin/bash
echo "=============================================================================="
echo "antlr lendo gramatica.g e gerando arquivos"
java -cp antlr.jar antlr.Tool gramatica.g

echo "=============================================================================="
echo "compilando arquivos java"
javac -cp antlr.jar:. *.java

echo "=============================================================================="
echo "Compiler lendo prog.in e gerando Teste.java"
java -cp antlr.jar:. Compiler Teste < prog.in

echo "=============================================================================="
echo "compilando Teste.java"
javac Teste.java

echo "=============================================================================="
echo "executando Teste"
java Teste