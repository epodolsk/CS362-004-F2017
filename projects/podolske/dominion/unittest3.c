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
    int result;
    int p, r, initHandCount, initDeckCount, initDiscardCount;
    int k[10] = {adventurer, council_room, feast, gardens, mine
               , remodel, smithy, village, baron, great_hall};
    struct gameState G;
    printf("TESTING isGameOver():\n");
    r = initializeGame(numPlayers, k, seed, &G);
    result = isGameOver(&G);
    printf("Game just started. Expected 0, got %d\n", result);
    assertTrue(result == 0);
    G.supplyCount[province] = 0; //set province count to 0
    result = isGameOver(&G);
    printf("Is game over with province count 0? Expect 1, got %d\n", result);
    assertTrue(result == 1);
    G.supplyCount[province] = 1; //set province to positive
    //zero out last three supply piles
    for(i = 0; i < 3; ++i) {
        G.supplyCount[treasure_map-i] = 0;
    }
    result = isGameOver(&G);
    printf("Is game over with last three supply piles 0? Expect 1, got %d\n", result);
    assertTrue(result == 1);
    //reset last three supply piles to positive and set first three to zero
    for(i = 0; i < 3; ++i) {
        G.supplyCount[i] = 0;
        G.supplyCount[treasure_map-i] = 1;
    }
    result = isGameOver(&G);
    printf("Is game over with first three supply piles 0? Expect 1, got %d\n", result);
    assertTrue(result == 1);
    return 0;
}