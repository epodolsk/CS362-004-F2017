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
    printf("TESTING drawCard():\n");
    r = initializeGame(numPlayers, k, seed, &G);
    printf("Test both players with cards in deck\n");
    for (p = 0; p < numPlayers; p++) {
        initDeckCount = G.deckCount[p];
        initHandCount = G.handCount[p];
        printf("Test player %d with %d cards in deck and %d cards in hand\n", p, initDeckCount, initHandCount);
        drawCard(p, &G);
        printf("Player %d deck count: Expected %d, got %d\n", p, initDeckCount-1, G.deckCount[p]);
        assertTrue(initDeckCount-1 == G.deckCount[p]);
        printf("Player %d hand count: Expected %d, got %d\n", p, initHandCount+1, G.handCount[p]);
        assertTrue(initHandCount+1 == G.handCount[p]);
        endTurn(&G);
    }
    printf("Test both players with empty deck\n");
    for (p = 0; p < numPlayers; p++) {
        //move all deck cards to discard
        for (i = 0; i < G.deckCount[p]; i++){
            G.discard[p][i] = G.deck[p][i];
            G.discard[p][i] = -1;
        }
        G.discardCount[p] = G.deckCount[p];
        G.deckCount[p] = 0;

        initDeckCount = G.deckCount[p];
        initHandCount = G.handCount[p];
        initDiscardCount = G.discardCount[p];
        printf("Test player %d with %d cards in deck, %d cards in hand, and %d cards in discard\n", p, initDeckCount, initHandCount, initDiscardCount);
        drawCard(p, &G);

        printf("Player %d deck count: Expected %d, got %d\n", p, initDiscardCount-1, G.deckCount[p]);
        assertTrue(initDiscardCount-1 == G.deckCount[p]);
        printf("Player %d hand count: Expected %d, got %d\n", p, initHandCount+1, G.handCount[p]);
        assertTrue(initHandCount+1 == G.handCount[p]);
        endTurn(&G);
    }

    return 0;
}