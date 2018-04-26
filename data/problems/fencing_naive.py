R, C = map(int, input().split())

grid = tuple(tuple(map(int, input().split())) for r in range(R))

maxPerim = 0

for r in range(R):
    for c in range(C):
        maxPerim = max(maxPerim, grid[r][c])
        for h in range(2, R - r + 1):
            for w in range(2, C - c + 1):
                try:
                    perim = 0
                    # horizontal
                    perim += sum(grid[r][c:c+w])
                    perim += sum(grid[r+h-1][c:c+w])
                    # vertical
                    perim += sum([ grid[r2][c] for r2 in range(r+1, r+h-1) ])
                    perim += sum([ grid[r2][c + w - 1] for r2 in range(r+1, r+h-1) ])
                    # update max
                    maxPerim = max(perim, maxPerim)
                except IndexError as e:
                    print(r, c, w, h)
                    raise e

print(maxPerim)
