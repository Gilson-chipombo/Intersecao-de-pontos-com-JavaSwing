# Nome da classe principal para executar
MAIN_CLASS = MainApp

# Compila todos os arquivos .java
compile:
	javac *.java

# Remove os arquivos .class gerados
clean:
	rm -f *.class

# Compila e executa o programa
run: compile
	java $(MAIN_CLASS)

.PHONY: compile clean run
