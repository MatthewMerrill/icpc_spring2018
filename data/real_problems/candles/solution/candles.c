#include <stdio.h>
#include <stdint.h>
#include <string.h>

int main() {
    const uint16_t board = 0b000110100;

    uint8_t min_moves = UINT8_MAX;

    for (uint16_t solution = 0; solution < 512; solution++) {
        uint16_t new_board = board;
        uint16_t solution_copy = solution;
        uint16_t move_count = 0;

        for (int i = 0; i < 9; i++) {
            if (solution_copy & 1 == 1) {
                move_count++;

                switch (i) {
                case 0: new_board ^= 0b000001011; break;
                case 1: new_board ^= 0b000010111; break;
                case 2: new_board ^= 0b000100110; break;
                case 3: new_board ^= 0b001011001; break;
                case 4: new_board ^= 0b010111010; break;
                case 5: new_board ^= 0b100110100; break;
                case 6: new_board ^= 0b011001000; break;
                case 7: new_board ^= 0b111010000; break;
                case 8: new_board ^= 0b110100000; break;
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
