#include "dominion.h"
#include "dominion_helpers.h"
#include "test_helpers.h"
#include <string.h>
#include <stdio.h>
#include <assert.h>
#include "rngs.h"
#include <stdlib.h>


int main() {
    int i;
    int seed = 1000;
    int numPlayers = 4;
    int p, r;
    int initDeckCounts[numPlayers];
    int initHandCounts[numPlayers];
    int expectedHandCount, expectedDeckCount;
    int k[10] = {adventurer, council_room, feast, gardens, mine
               , remodel, smithy, village, baron, great_hall};
    struct gameState G;
    printf("TESTING smithy card:\n");
    r = initializeGame(numPlayers, k, seed, &G);
    
    for(i = 0; i < numPlayers; ++i) {
        initDeckCounts[i] = G.deckCount[i];
        initHandCounts[i] = G.handCount[i];
    }
    G.hand[0][G.handCount[0]] = smithy;
    playCard(G.handCount[0], -1, -1, -1, &G);
    for(i = 0; i < numPlayers; ++i) {
        if(i == 0) {
            expectedHandCount = initHandCounts[i] + 3;
            expectedDeckCount = initDeckCounts[i] - 3;
        }
        else {
            expectedHandCount = initHandCounts[i];
            expectedDeckCount = initDeckCounts[i];
        }
        printf("Player %d: Expected deck count %d (got %d).", i, expectedDeckCount, G.deckCount[i]);
        assertTrue(expectedDeckCount == G.deckCount[i]);
        printf("Player %d: Expected hand count %d (got %d).", i, expectedHandCount, G.handCount[i]);
        assertTrue(expectedHandCount == G.handCount[i]);


    }
    return 0;
}