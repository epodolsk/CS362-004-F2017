#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <stdio.h>
#include <assert.h>
#include "rngs.h"
#include <stdlib.h>
#include "test_helpers.h"


int main() {
    int seed = 1000;
    int numPlayers = 2;
    int result;
    int r, initHandCount, initDeckCount, initDiscardCount, initAdventurerCount;
    int k[10] = {adventurer, council_room, feast, gardens, mine
               , remodel, smithy, village, baron, great_hall};
    struct gameState G;
    printf("TESTING gainCard():\n");
    
    r = initializeGame(numPlayers, k, seed, &G);
    initHandCount = G.handCount[0];
    initDeckCount = G.deckCount[0];
    initDiscardCount = G.discardCount[0];
    initAdventurerCount = G.supplyCount[adventurer];
    result = gainCard(adventurer, &G, 0, 0);
    printf("Gain adventurer card to discard. Expect hand count %d (got %d), deck count %d (got %d), and discard count %d (got %d). Expect return value 0 (got %d). Expect final card in discard to be %d (got %d). Expect supply pile %d (got %d).\n", 
        initHandCount, G.handCount[0], initDeckCount, G.deckCount[0], initDiscardCount+1, G.discardCount[0], result, adventurer, G.discard[0][G.discardCount[0]-1], initAdventurerCount-1, G.supplyCount[adventurer]);
    assertTrue(initHandCount == G.handCount[0] && initDeckCount == G.deckCount[0] && initDiscardCount+1 == G.discardCount[0] && result == 0 && G.discard[0][G.discardCount[0]-1]==adventurer && initAdventurerCount-1 == G.supplyCount[adventurer]);
    
    initHandCount = G.handCount[0];
    initDeckCount = G.deckCount[0];
    initDiscardCount = G.discardCount[0];
    initAdventurerCount = G.supplyCount[adventurer];
    result = gainCard(adventurer, &G, 1, 0);
    printf("Gain adventurer card to deck. Expect hand count %d (got %d), deck count %d (got %d), and discard count %d (got %d). Expect return value 0 (got %d). Expect final card in deck to be %d (got %d). Expect supply pile %d (got %d).\n", 
        initHandCount, G.handCount[0], initDeckCount+1, G.deckCount[0], initDiscardCount, G.discardCount[0], result, adventurer, G.deck[0][G.deckCount[0]-1], initAdventurerCount-1, G.supplyCount[adventurer]);
    assertTrue(initHandCount == G.handCount[0] && initDeckCount+1 == G.deckCount[0] && initDiscardCount == G.discardCount[0] && result == 0 && G.deck[0][G.deckCount[0]-1]==adventurer && initAdventurerCount-1 == G.supplyCount[adventurer]);

    initHandCount = G.handCount[0];
    initDeckCount = G.deckCount[0];
    initDiscardCount = G.discardCount[0];
    initAdventurerCount = G.supplyCount[adventurer];
    result = gainCard(adventurer, &G, 2, 0);
    printf("Gain adventurer card to hand. Expect hand count %d (got %d), deck count %d (got %d), and discard count %d (got %d). Expect return value 0 (got %d). Expect final card in hand to be %d (got %d). Expect supply pile %d (got %d).\n", 
        initHandCount+1, G.handCount[0], initDeckCount, G.deckCount[0], initDiscardCount, G.discardCount[0], result, adventurer, G.deck[0][G.deckCount[0]-1], initAdventurerCount-1, G.supplyCount[adventurer]);
    assertTrue(initHandCount+1 == G.handCount[0] && initDeckCount == G.deckCount[0] && initDiscardCount == G.discardCount[0] && result == 0 && G.deck[0][G.deckCount[0]-1]==adventurer && initAdventurerCount-1 == G.supplyCount[adventurer]);

    initHandCount = G.handCount[0];
    initDeckCount = G.deckCount[0];
    initDiscardCount = G.discardCount[0];
    G.supplyCount[adventurer] = 0;
    initAdventurerCount = G.supplyCount[adventurer];
    result = gainCard(adventurer, &G, 2, 0);
    printf("Empty adventurer pile. Attempt to gain adventurer card to hand. Expect hand count %d (got %d), deck count %d (got %d), and discard count %d (got %d). Expect return value -1 (got %d). Expect supply pile %d (got %d).\n", 
        initHandCount, G.handCount[0], initDeckCount, G.deckCount[0], initDiscardCount, G.discardCount[0], result, initAdventurerCount, G.supplyCount[adventurer]);
    assertTrue(initHandCount == G.handCount[0] && initDeckCount == G.deckCount[0] && initDiscardCount == G.discardCount[0] && result == -1 && initAdventurerCount == G.supplyCount[adventurer]);
    return 0;
}