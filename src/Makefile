TARGET = main/Main.class

SOURCE = assem/*.java canon/*.java errors/*.java flow_graph/*.java frame/*.java graph/*.java main/*.java semant/*.java symbol/*.java syntaxtree/*.java temp/*.java translate/*.java tree/*.java util/*.java util/conversor/*.java visitor/*.java

$(TARGET): $(SOURCE)
	java -jar sablecc.jar miniJava.sablecc
	javac -g main/Main.java

clean: 
	rm -rf assem/*.class
	rm -rf canon/*.class
	rm -rf errors/*.class
	rm -rf flow_graph/*.class
	rm -rf frame/*.class
	rm -rf graph/*.class
	rm -rf main/*.class
	rm -rf minijava/analysis/*.*
	rm -rf minijava/lexer/*.*
	rm -rf minijava/node/*.*
	rm -rf minijava/parser/*.*
	rm -rf semant/*.class
	rm -rf symbol/*.class
	rm -rf syntaxtree/*.class
	rm -rf temp/*.class
	rm -rf translate/*.class
	rm -rf tree/*.class
	rm -rf util/*.class
	rm -rf util/conversor/*.class
	rm -rf visitor/*.class
	rm -rf out

run:
	rm -rf out
	mkdir out
	mkdir out/semant
	mkdir out/semant/1
	mkdir out/semant/2
	mkdir out/semant/3
	java main.Main ../testes/semant/1/01 2>&1 >/dev/null | tee $f > out/semant/1/01.out
	java main.Main ../testes/semant/1/02 2>&1 >/dev/null | tee $f > out/semant/1/02.out
	java main.Main ../testes/semant/1/03 2>&1 >/dev/null | tee $f > out/semant/1/03.out
	java main.Main ../testes/semant/1/04 2>&1 >/dev/null | tee $f > out/semant/1/04.out
	java main.Main ../testes/semant/1/05 2>&1 >/dev/null | tee $f > out/semant/1/05.out
	java main.Main ../testes/semant/1/06 2>&1 >/dev/null | tee $f > out/semant/1/06.out
	java main.Main ../testes/semant/1/07 2>&1 >/dev/null | tee $f > out/semant/1/07.out
	java main.Main ../testes/semant/1/08 2>&1 >/dev/null | tee $f > out/semant/1/08.out
	java main.Main ../testes/semant/1/09 2>&1 >/dev/null | tee $f > out/semant/1/09.out
	java main.Main ../testes/semant/1/10 2>&1 >/dev/null | tee $f > out/semant/1/10.out
	java main.Main ../testes/semant/1/11 2>&1 >/dev/null | tee $f > out/semant/1/11.out
	java main.Main ../testes/semant/2/01 2>&1 >/dev/null | tee $f > out/semant/2/01.out
	java main.Main ../testes/semant/2/02 2>&1 >/dev/null | tee $f > out/semant/2/02.out
	java main.Main ../testes/semant/2/03 2>&1 >/dev/null | tee $f > out/semant/2/03.out
	java main.Main ../testes/semant/2/04 2>&1 >/dev/null | tee $f > out/semant/2/04.out
	java main.Main ../testes/semant/2/05 2>&1 >/dev/null | tee $f > out/semant/2/05.out
	java main.Main ../testes/semant/2/06 2>&1 >/dev/null | tee $f > out/semant/2/06.out
	java main.Main ../testes/semant/2/07 2>&1 >/dev/null | tee $f > out/semant/2/07.out
	java main.Main ../testes/semant/2/08 2>&1 >/dev/null | tee $f > out/semant/2/08.out
	java main.Main ../testes/semant/2/09 2>&1 >/dev/null | tee $f > out/semant/2/09.out
	java main.Main ../testes/semant/2/10 2>&1 >/dev/null | tee $f > out/semant/2/10.out
	java main.Main ../testes/semant/2/11 2>&1 >/dev/null | tee $f > out/semant/2/11.out
	java main.Main ../testes/semant/3/01 2>&1 >/dev/null | tee $f > out/semant/3/01.out
	java main.Main ../testes/semant/3/02 2>&1 >/dev/null | tee $f > out/semant/3/02.out
	java main.Main ../testes/semant/3/03 2>&1 >/dev/null | tee $f > out/semant/3/03.out
	java main.Main ../testes/semant/3/04 2>&1 >/dev/null | tee $f > out/semant/3/04.out
	java main.Main ../testes/semant/3/05 2>&1 >/dev/null | tee $f > out/semant/3/05.out
	java main.Main ../testes/semant/3/06 2>&1 >/dev/null | tee $f > out/semant/3/06.out
	java main.Main ../testes/semant/3/07 2>&1 >/dev/null | tee $f > out/semant/3/07.out
	java main.Main ../testes/semant/3/08 2>&1 >/dev/null | tee $f > out/semant/3/08.out
	java main.Main ../testes/semant/3/09 2>&1 >/dev/null | tee $f > out/semant/3/09.out
	java main.Main ../testes/semant/3/10 2>&1 >/dev/null | tee $f > out/semant/3/10.out
	java main.Main ../testes/semant/3/11 2>&1 >/dev/null | tee $f > out/semant/3/11.out
	java main.Main ../testes/semant/3/12 2>&1 >/dev/null | tee $f > out/semant/3/12.out
	java main.Main ../testes/semant/3/13 2>&1 >/dev/null | tee $f > out/semant/3/13.out
	java main.Main ../testes/semant/3/14 2>&1 >/dev/null | tee $f > out/semant/3/14.out
	java main.Main ../testes/semant/3/15 2>&1 >/dev/null | tee $f > out/semant/3/15.out
	java main.Main ../testes/semant/3/16 2>&1 >/dev/null | tee $f > out/semant/3/16.out
	java main.Main ../testes/semant/3/17 2>&1 >/dev/null | tee $f > out/semant/3/17.out
	java main.Main ../testes/semant/3/18 2>&1 >/dev/null | tee $f > out/semant/3/18.out
	java main.Main ../testes/semant/3/19 2>&1 >/dev/null | tee $f > out/semant/3/19.out
	java main.Main ../testes/semant/3/20 2>&1 >/dev/null | tee $f > out/semant/3/20.out
	java main.Main ../testes/semant/3/21 2>&1 >/dev/null | tee $f > out/semant/3/21.out
	java main.Main ../testes/semant/3/22 2>&1 >/dev/null | tee $f > out/semant/3/22.out
	java main.Main ../testes/semant/3/23 2>&1 >/dev/null | tee $f > out/semant/3/23.out
	java main.Main ../testes/semant/3/24 2>&1 >/dev/null | tee $f > out/semant/3/24.out
	java main.Main ../testes/semant/3/25 2>&1 >/dev/null | tee $f > out/semant/3/25.out
	java main.Main ../testes/semant/3/26 2>&1 >/dev/null | tee $f > out/semant/3/26.out
	java main.Main ../testes/semant/3/27 2>&1 >/dev/null | tee $f > out/semant/3/27.out
	java main.Main ../testes/semant/3/28 2>&1 >/dev/null | tee $f > out/semant/3/28.out
	java main.Main ../testes/semant/3/29 2>&1 >/dev/null | tee $f > out/semant/3/29.out
	java main.Main ../testes/semant/3/30 2>&1 >/dev/null | tee $f > out/semant/3/30.out
	java main.Main ../testes/semant/3/31 2>&1 >/dev/null | tee $f > out/semant/3/31.out
	java main.Main ../testes/semant/3/32 2>&1 >/dev/null | tee $f > out/semant/3/32.out
	java main.Main ../testes/semant/3/33 2>&1 >/dev/null | tee $f > out/semant/3/33.out
	java main.Main ../testes/semant/3/34 2>&1 >/dev/null | tee $f > out/semant/3/34.out
	java main.Main ../testes/semant/3/35 2>&1 >/dev/null | tee $f > out/semant/3/35.out
	java main.Main ../testes/semant/3/36 2>&1 >/dev/null | tee $f > out/semant/3/36.out
	java main.Main ../testes/semant/3/37 2>&1 >/dev/null | tee $f > out/semant/3/37.out
	java main.Main ../testes/BinarySearch.txt 2>&1 >/dev/null | tee $f > out/BinarySearch.out
	java main.Main ../testes/BinaryTree.txt 2>&1 >/dev/null | tee $f > out/BinaryTree.out
	java main.Main ../testes/BubbleSort.txt 2>&1 >/dev/null | tee $f > out/BubbleSort.out
	java main.Main ../testes/Factorial.txt 2>&1 >/dev/null | tee $f > out/Factorial.out
	java main.Main ../testes/LinearSearch.txt 2>&1 >/dev/null | tee $f > out/LinearSearch.out
	java main.Main ../testes/LinkedList.txt 2>&1 >/dev/null | tee $f > out/LinkedList.out
	java main.Main ../testes/QuickSort.txt 2>&1 >/dev/null | tee $f > out/QuickSort.out
	java main.Main ../testes/TreeVisitor.txt 2>&1 >/dev/null | tee $f > out/TreeVisitor.out
