#include <stdio.h>
#include <stdlib.h>

#define max(x,y) x>y?x:y

int ans(int R, int C, int** grid) {
  int maxPerim = grid[0][0];

  for (int r = 0; r < R; r++) {
    for (int c = 0; c < C; c++) {
      maxPerim = max(maxPerim, grid[r][c]);

      for (int h = 2; h < R - r + 1; h++) {
        for (int w = 2; w < C - c + 1; w++) {
          int perim = 0;
          // horizontal
          for (int d = c; d < c+w; d++) {
            perim += grid[r][d];
            perim += grid[r+h-1][d];
          }

          // vertical
          for (int d = r+1; d < r+h-1; d++) {
            perim += grid[d][c];
            perim += grid[d][c + w - 1];
          }

          // update max
          maxPerim = max(perim, maxPerim);
        }
      }
    }
  }
  return maxPerim;
}

int main() {
  int R = 0, C = 0;
  scanf("%d %d", &R, &C);

  int** grid = malloc(R * sizeof(int*));

  for (int r = 0; r < R; r++) {
    grid[r] = malloc(C * sizeof(int));
    for (int c = 0; c < C; c++) {
      scanf("%d", &grid[r][c]);
    }
  }

  int maxPerim = ans(R, C, grid);

  for (int r = 0; r < R; r++) {
    free(grid[r]);
  }
  free(grid);

  printf("%d", maxPerim);
  return 0;
}
