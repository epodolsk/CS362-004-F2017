#include <stdio.h>

int assertTrue(int condition) {
    if(condition) {
        printf("TEST SUCCESSFUL\n");
    }
    else {
        printf("TEST FAILED\n");
    }

    return condition;
}