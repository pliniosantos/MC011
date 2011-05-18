#!/bin/sh
# Script para rodar os testes de MC011-P1
#

dir=$1

#numero de arquivos no diretorio
num=$(ls $dir | wc -l)

#percorre todos os arquivos
for (( i=1 ; i <= $num ; i++ ))
do
    if [ $i -lt 10 ]
    then
        #tem que adicionar um 0 no nome dos arquivos entre 1 e 9
        java minijava/main/Main $1/in/0$i > $1/g15/0$i.out
    else
        java minijava/main/Main $1/in/$i > $1/g15/$i.out
    fi
done

#concatena todos os arquivos .out em dir/gall/g15.out
cat $1/g15/*.out > $1/gall/g15.out
