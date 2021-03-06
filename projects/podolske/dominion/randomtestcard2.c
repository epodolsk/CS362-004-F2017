#include "dominion.h"
#include "dominion_helpers.h"
#include "test_helpers.h"
#include <string.h>
#include <stdio.h>
#include <assert.h>
#include "rngs.h"
#include <stdlib.h>
#include <time.h>


int main() {
    int i, j;
    int runs;
    int seed, numPlayers;
    int p, r;
    int initDeckCounts[numPlayers];
    int initHandCounts[numPlayers];
    int initTopCards[numPlayers];
    int expectedTopDeck, expectedTopDiscard;
    int cardPosition;
    int k[10] = {adventurer, council_room, feast, gardens, mine
               , remodel, smithy, village, baron, great_hall};
    struct gameState G;

    srand(time(NULL));
    for(runs = 0; runs < 1000; ++runs) {
      printf("RUN %d\n", runs);
      seed = rand();
      numPlayers = rand() % 4 + 1;
      r = initializeGame(numPlayers, k, seed, &G);
      
      for(i = 0; i < numPlayers; ++i) {
          G.deckCount[i] = rand() % 60;
          for(j = 0; j < G.deckCount[i]; ++j) {
              G.deck[i][j] = rand() % (treasure_map + 1);
          }
          initDeckCounts[i] = G.deckCount[i];
          initTopCards[i] = G.deck[i][G.deckCount[i]-1];
          G.handCount[i] = rand() % 5 + 1;
          for(j = 0; j < G.handCount[i]; ++j) {
              G.hand[i][j] = rand() % (treasure_map + 1);
          }
          cardPosition = rand() % G.handCount[i];
          G.hand[i][cardPosition] = sea_hag;
          initHandCounts[i] = G.handCount[i];
          G.discardCount[i] = rand() % 60;
          for(j = 0; j < G.discardCount[i]; ++j) {
              G.discard[i][j] = rand() % (treasure_map + 1);
          }
      }
      playCard(cardPosition, -1, -1, -1, &G);
      for(i = 0; i < numPlayers; ++i) {
          if(i == 0) {
              expectedTopDeck = initTopCards[0];
          }
          else {
              expectedTopDeck = curse;
              expectedTopDiscard = initTopCards[i];
          }
          printf("Player %d: Expected deck count %d (got %d). Expected top card %d (got %d).\n", i, initDeckCounts[i], G.deckCount[i], expectedTopDeck, G.deck[i][G.deckCount[i]-1]);
          assertTrue(initDeckCounts[i] == G.deckCount[i] && expectedTopDeck == G.deck[i][G.deckCount[i]-1]);
          printf("Expected top of discard %d (got %d).\n", expectedTopDiscard, G.discard[i][G.discardCount[i]-1]);
          assertTrue(expectedTopDiscard == G.discard[i][G.discardCount[i]-1]);

      }
    }
    return 0;
}