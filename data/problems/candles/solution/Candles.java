import java.util.Scanner;

public class Candles {
    private static int[][] moves = {
            {0, 1, 3},
            {0, 1, 2, 4},
            {1, 2, 5},
            {0, 3, 4, 6},
            {1, 3, 4, 5, 7},
            {2, 4, 5, 8},
            {3, 6, 7},
            {4, 6, 7, 8},
            {5, 7, 8},
    };

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int board = 0;

        for (int i = 0; i < 9; i++) {
            board <<= 1;

            if (in.next().equals("+")) {
                board |= 1;
            }
        }

        int minMoves = Integer.MAX_VALUE;

        for (int solution = 0; solution < 512; solution++) {
            int boardCopy = board;
            int solutionCopy = solution;
            int moveCount = 0;

            for (int boardMove = 0; boardMove < 9; boardMove++) {
                if ((solutionCopy & 1) == 1) {
                    moveCount++;
                    boardCopy = flip(boardCopy, moves[boardMove]);
                }

                solutionCopy >>= 1;
            }

            if (boardCopy == 0b111111111) {
                minMoves = Math.min(minMoves, moveCount);
            }
        }

        System.out.println(minMoves);
    }

    private static int flip(int board, int... boardMoves) {
        for (int move : boardMoves) {
            board ^= (1 << move);
        }

        return board;
    }
}
