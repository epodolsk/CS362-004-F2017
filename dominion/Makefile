CFLAGS = -Wall -fpic -coverage -lm

default: results

results: dominion.o randomtestcard1 randomtestcard2 randomtestadventurer
	echo "Smithy:" >> randomtestcard1.out
	./randomtestcard1 >> randomtestcard1.out
	gcov -b -c dominion.c >> randomtestcard1.out

	echo "Sea Hag:" >> randomtestcard2.out
	./randomtestcard2 >> randomtestcard2.out
	gcov -b -c dominion.c >> randomtestcard2.out
  
	echo "Adventurer:" >> randomtestadventurer.out
	./randomtestadventurer >> randomtestadventurer.out
	gcov -b -c dominion.c >> randomtestadventurer.out

rngs.o: rngs.h rngs.c
	gcc -c rngs.c -g  $(CFLAGS)

dominion.o: dominion.h dominion.c rngs.o
	gcc -c dominion.c -g  $(CFLAGS)

randomtestcard1: cardtest1.c dominion.o rngs.o
	gcc -o randomtestcard1 -g  randomtestcard1.c dominion.o rngs.o $(CFLAGS)

randomtestcard2: cardtest2.c dominion.o rngs.o
	gcc -o randomtestcard2 -g  randomtestcard2.c dominion.o rngs.o $(CFLAGS)

randomtestadventurer: cardtest3.c dominion.o rngs.o
	gcc -o randomtestadventurer -g  randomtestadventurer.c dominion.o rngs.o $(CFLAGS)

clean:
	rm -f *.o *.out playdom.exe playdom player player.exe *.gcov *.gcda *.gcno *.so testDrawCard testDrawCard.exe randomtestcard1 randomtestcard2 randomtestadventurer