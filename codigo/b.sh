#!/bin/bash

echo
echo "=============================================================================="
echo "limpando arquivos Teste"
rm Teste.*

echo
echo "=============================================================================="
echo "antlr lendo gramatica.g e gerando arquivos"
java -cp antlr.jar antlr.Tool gramatica.g

echo
echo "=============================================================================="
echo "compilando arquivos java"
javac -cp antlr.jar:. AbstractOperand.java AbstractLogicOperand.java
javac -cp antlr.jar:. UnaryOperand.java UnaryLogicOperand.java
javac -cp antlr.jar:. BinaryOperand.java BinaryLogicOperand.java
javac -cp antlr.jar:. *.java

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