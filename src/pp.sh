#!/bin/sh
# Script para rodar os testes de MC011-P1
#

dir=$1

#numero de arquivos no diretorio
num=$(ls $dir`/in` | wc -l)
echo $num
#percorre todos os arquivos
for (( i=1 ; i <= $num ; i++ ))
do
    if [ $i -lt 10 ]
    then
        #tem que adicionar um 0 no nome dos arquivos entre 1 e 9
        java minijava/main/Main $1/in/0$i 2>&1 >/dev/null | tee $f > $1/g15/0$i.out
	cat $1/g15/0$i.out >> $1/gall/g15.out
    else
        java minijava/main/Main $1/in/$i 2>&1 >/dev/null | tee $f > $1/g15/$i.out
	cat $1/g15/$i.out >> $1/gall/g15.out
    fi
done

#concatena todos os arquivos .out em dir/gall/g15.out
#cat $1/g15/*.out > $1/gall/g15.out
