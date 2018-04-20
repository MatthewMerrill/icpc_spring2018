#include <stdio.h>
#include <stdint.h>
#include <string.h>

#define FLIP(i) (new_board ^= 1 << i)

int main() {
    const uint16_t board = 0b111111111;

    uint8_t min_moves = UINT8_MAX;

    for (uint16_t solution = 0; solution < 512; solution++) {
        uint16_t new_board = board;
        uint16_t solution_copy = solution;
        uint16_t move_count = 0;

        for (int i = 0; i < 9; i++) {
            if (solution_copy & 1 == 1) {
                move_count++;

                switch (i) {
                case 0: FLIP(0); FLIP(1); FLIP(3); break;
                case 1: FLIP(0); FLIP(1); FLIP(2); FLIP(4); break;
                case 2: FLIP(1); FLIP(2); FLIP(5); break;
                case 3: FLIP(0); FLIP(3); FLIP(4); FLIP(6); break;
                case 4: FLIP(1); FLIP(3); FLIP(4); FLIP(5); FLIP(7); break;
                case 5: FLIP(2); FLIP(4); FLIP(5); FLIP(8); break;
                case 6: FLIP(3); FLIP(6); FLIP(7); break;
                case 7: FLIP(4); FLIP(6); FLIP(7); FLIP(8); break;
                case 8: FLIP(5); FLIP(7); FLIP(8); break;
                }
            }

            solution_copy >>= 1;
        }

        if (new_board == 0b111111111) {
            min_moves = (min_moves < move_count ? min_moves : move_count);
        }
    }

    printf("%d\n", min_moves);

    return 0;
}
