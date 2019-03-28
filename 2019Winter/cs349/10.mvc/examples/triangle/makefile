# super simple makefile
# call it using 'make NAME=name_of_code_file_without_extension'
# (assumes a .java extension)
NAME = "Main1"

all:
	# (a bit of a hack to compile everything each time ...)
	@echo "Compiling..."
	javac *.java model/*.java view/*.java

run: all
	@echo "Running..."
	java $(NAME)

clean:
	rm -rf *.class
	rm -rf view/*.class
	rm -rf model/*.class
