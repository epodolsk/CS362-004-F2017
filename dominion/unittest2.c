#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <stdio.h>
#include <assert.h>
#include "rngs.h"
#include <stdlib.h>
#include "test_helpers.h"


int main() {
    int i;
    int seed = 1000;
    int numPlayers = 2;
    int p, r, initHandCount, initDeckCount, initDiscardCount;
    int k[10] = {adventurer, council_room, feast, gardens, mine
               , remodel, smithy, village, baron, great_hall};
    struct gameState G;
    printf("TESTING fullDeckCount():\n");
    r = initializeGame(numPlayers, k, seed, &G);
    for (p = 0; p < numPlayers; p++) {
        initDeckCount = G.deckCount[p];
        initHandCount = G.handCount[p];
        initDiscardCount = G.discardCount[p];
        printf("Test player %d with %d cards in deck, %d cards in hand, and %d cards in discard\n", p, initDeckCount, initHandCount, initDiscardCount);

        printf("Player %d deck count: Expected %d, got %d\n", p, initDeckCount+initHandCount+initDiscardCount, fullDeckCount(p, 0, &G));
        assertTrue(initDeckCount+initHandCount+initDiscardCount==fullDeckCount(p, 0, &G));
        endTurn(&G);
    }

    return 0;
}