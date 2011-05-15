TARGET = main/Main.class

SOURCE = 

$(TARGET): $(SOURCE)
	java -jar sablecc.jar miniJava.sablecc
	javac -g minijava/main/Main.java

clean: 
	rm -rf minijava/analysis/*.*
	rm -rf minijava/lexer/*.*
	rm -rf minijava/node/*.*
	rm -rf minijava/parser/*.*
	rm -rf minijava/main/*.class
	
cleanjava: 
	rm -rf minijava/analysis/*.java
	rm -rf minijava/lexer/*.java
	rm -rf minijava/node/*.java
	rm -rf minijava/parser/*.java

cleanclass: 
	rm -rf minijava/analysis/*.class
	rm -rf minijava/lexer/*.class
	rm -rf minijava/node/*.class
	rm -rf minijava/parser/*.class
	rm -rf minijava/main/*.class
