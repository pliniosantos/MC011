#!/bin/sh
# Script para rodar os testes de MC011-P1
#

num=20
dir=$1

for (( i=1 ; i <= $num ; i++ ))
do
    if [ $i -lt 10 ]
    then
        java minijava/main/Main $1/in/0$i.java > $1/out/0$i.out
    else
        java minijava/main/Main $1/in/$i.java > $1/out/$i.out
    fi
done
