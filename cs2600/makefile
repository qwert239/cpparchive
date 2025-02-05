all: program
program: maketest
maketest: maketest.c
	gcc -o maketest -std=c99 -Wall -O1 maketest.c
clean:
	rm -f maketest