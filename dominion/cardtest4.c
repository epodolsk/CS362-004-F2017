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
    int numPlayers = 3;
    int p, r;
    int initHandCounts[numPlayers];
    int initTopCards[numPlayers];
    int initCoins;
    int expectedTopDeck, expectedTopDiscard;
    int k[10] = {adventurer, council_room, feast, gardens, mine
               , remodel, smithy, village, baron, great_hall};
    struct gameState G;
    printf("TESTING cutpurse card:\n");
    r = initializeGame(numPlayers, k, seed, &G);
    G.handCount[1] = 5;
    G.handCount[2] = 5;
    for(i = 0; i < numPlayers; ++i) {
        initHandCounts[i] = G.handCount[i];
    }
    G.hand[1][0] = copper;
    for(i = 0; i < G.handCount[2]; ++i) {
        G.hand[2][i] = 0;
    }
    G.hand[0][G.handCount[0]] = cutpurse;
    playCard(G.handCount[0], -1, -1, -1, &G);
    printf("Player 0: Expected hand count %d (got %d).\n", initHandCounts[0]-1, G.handCount[0]);
    assertTrue(initHandCounts[0]-1 == G.handCount[0]);
    printf("Player 1 (1 copper): Expected hand count %d (got %d).\n", initHandCounts[1]-1, G.handCount[1]);
    assertTrue(initHandCounts[1]-1 == G.handCount[1]);
    printf("Player 2 (no coppers): Expected hand count %d (got %d).\n", initHandCounts[2], G.handCount[2]);
    assertTrue(initHandCounts[2] == G.handCount[2]);
    printf("Top of discard %d (expected %d).\n", G.playedCards[G.playedCardCount-1], cutpurse);
    assertTrue(G.playedCards[G.playedCardCount-1] == cutpurse);
    printf("Second on discard %d (expected %d).\n", G.playedCards[G.playedCardCount-2], copper);
    assertTrue(G.playedCards[G.playedCardCount-2] == copper);
    return 0;
}