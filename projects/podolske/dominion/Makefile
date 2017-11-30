CFLAGS = -Wall -fpic -coverage -lm

default: unittestresults.out

unittestresults.out: dominion.o unittest1 unittest2 unittest3 unittest4 cardtest1 cardtest2 cardtest3 cardtest4
	echo "Result for running dominion tests:" > unittestresults.out
	
	echo "unittest1.c:" >> unittestresults.out
	./unittest1 >> unittestresults.out
	gcov dominion.c >> unittestresults.out
	
	echo "unittest2.c:" >> unittestresults.out
	./unittest2 >> unittestresults.out
	gcov dominion.c >> unittestresults.out
	
	echo "unittest3.c:" >> unittestresults.out
	./unittest3 >> unittestresults.out
	gcov dominion.c >> unittestresults.out
	
	echo "unittest4.c:" >> unittestresults.out
	./unittest4 >> unittestresults.out
	gcov dominion.c >> unittestresults.out

	echo "cardtest1.c:" >> unittestresults.out
	./cardtest1 >> unittestresults.out
	gcov dominion.c >> unittestresults.out
	
	echo "cardtest2.c:" >> unittestresults.out
	./cardtest2 >> unittestresults.out
	gcov dominion.c >> unittestresults.out
	
	echo "cardtest3.c:" >> unittestresults.out
	./cardtest3 >> unittestresults.out
	gcov dominion.c >> unittestresults.out
	
	echo "cardtest4.c:" >> unittestresults.out
	./cardtest4 >> unittestresults.out
	gcov dominion.c >> unittestresults.out
  
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

unittest1: unittest1.c dominion.o rngs.o
	gcc -o unittest1 -g  unittest1.c dominion.o rngs.o $(CFLAGS)

unittest2: unittest2.c dominion.o rngs.o
	gcc -o unittest2 -g  unittest2.c dominion.o rngs.o $(CFLAGS)

unittest3: unittest3.c dominion.o rngs.o
	gcc -o unittest3 -g  unittest3.c dominion.o rngs.o $(CFLAGS)

unittest4: unittest4.c dominion.o rngs.o
	gcc -o unittest4 -g  unittest4.c dominion.o rngs.o $(CFLAGS)

cardtest1: cardtest1.c dominion.o rngs.o
	gcc -o cardtest1 -g  cardtest1.c dominion.o rngs.o $(CFLAGS)	

cardtest2: cardtest2.c dominion.o rngs.o
	gcc -o cardtest2 -g  cardtest2.c dominion.o rngs.o $(CFLAGS)

cardtest3: cardtest3.c dominion.o rngs.o
	gcc -o cardtest3 -g  cardtest3.c dominion.o rngs.o $(CFLAGS)
	
cardtest4: cardtest4.c dominion.o rngs.o
	gcc -o cardtest4 -g  cardtest4.c dominion.o rngs.o $(CFLAGS)	

randomtestcard1: randomtestcard1.c dominion.o rngs.o
	gcc -o randomtestcard1 -g  randomtestcard1.c dominion.o rngs.o $(CFLAGS)

randomtestcard2: randomtestcard2.c dominion.o rngs.o
	gcc -o randomtestcard2 -g  randomtestcard2.c dominion.o rngs.o $(CFLAGS)

randomtestadventurer: randomtestadventurer.c dominion.o rngs.o
	gcc -o randomtestadventurer -g  randomtestadventurer.c dominion.o rngs.o $(CFLAGS)

clean:
	rm -f *.o playdom.exe playdom player player.exe  *.gcov *.gcda *.gcno *.so testDrawCard testDrawCard.exe unittest1 unittest2 unittest3 unittest4 cardtest1 cardtest2 cardtest3 cardtest4 randomtestcard1 randomtestcard2 randomtestadventurer
