#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <stdlib.h>


// Define constants to make sure strings are not allocated at the top of the heap
#define HIT_ENTER  "Press Enter to continue...\n"
#define ALLOCATED  "500k 5KB chunks were just provisioned\n"
#define FREED      "The first allocations were just free()'d.\n"
#define FREED_NEXT "The 2nd allocations were just free()'d.\n"


void press_enter_to_continue(void) {
   printf(HIT_ENTER);
   getchar();
   return;
}

int main() {
    printf("Hello!  This program will fragment its process heap.  Run top -p %d to follow along!\n", getpid());
    press_enter_to_continue();

    int i;
    // Arbitrary value
    int ARRAY_SIZE = 5*1024*102;

    // Because we're asking for a large size at the get-go, the backing memory for these arrays will be mmap'd.
    // But the pointers stored here will be for
    // small allocations that we expect to be brk()'d.  We keep the mapping so we can free them later.
    char *p1[ARRAY_SIZE];
    char *p2[ARRAY_SIZE];

    int mallocSize = 6 * 1024;

    for(i=0; i < ARRAY_SIZE; i++){
        // malloc in small chunks such that we are always below the mmap threshold for these allocations.
        p1[i] = malloc(mallocSize);
        // Write something to make sure the page is backed by physical RAM
        *p1[i] = 'a';
    }

    printf(ALLOCATED);
    press_enter_to_continue();

    for(i=0; i < ARRAY_SIZE; i++){
        // Again, malloc in small chunks such that we are always below the mmap threshold for these allocations
        p2[i] = malloc(mallocSize);
       *p2[i] = 'a';
    }

    printf(ALLOCATED);
    press_enter_to_continue();

    // Free the allocations
    for(i=0; i < ARRAY_SIZE; i++){
        free(p1[i]);
    }

    printf(FREED);
    press_enter_to_continue();

    // Free the allocations
    for(i=0; i < ARRAY_SIZE; i++){
        free(p2[i]);
    }

    printf(FREED_NEXT);
    press_enter_to_continue();

    return 0;
}