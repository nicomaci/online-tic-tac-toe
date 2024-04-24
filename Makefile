JAVAC = javac
JAVA_FILES = Client.java Connect4.java Server.java TicTacToe.java

default: compile

compile: $(JAVA_FILES)
	$(JAVAC) $^

clean:
	rm -f *.class

.PHONY: default compile clean
