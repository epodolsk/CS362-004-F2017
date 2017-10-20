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
    int initTopCards[numPlayers];
    int expectedTopDeck, expectedTopDiscard;
    int k[10] = {adventurer, council_room, feast, gardens, mine
               , remodel, smithy, village, baron, great_hall};
    struct gameState G;
    printf("TESTING sea hag card:\n");
    r = initializeGame(numPlayers, k, seed, &G);
    
    for(i = 0; i < numPlayers; ++i) {
        initDeckCounts[i] = G.deckCount[i];
        initTopCards[i] = G.deck[i][G.deckCount[i]-1];
    }
    G.hand[0][G.handCount[0]] = sea_hag;
    playCard(G.handCount[0], -1, -1, -1, &G);
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
    return 0;
}