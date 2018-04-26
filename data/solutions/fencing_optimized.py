R, C = map(int, input().split())

grid = tuple(tuple(map(int, input().split())) for r in range(R))

maxPerim = 0

cumulH = [ [0 for c in range(C) ] for r in range(R) ]
cumulV = [ [0 for c in range(C) ] for r in range(R) ]

cumulH[0][0] = grid[0][0]
cumulV[0][0] = grid[0][0]

for r in range(0, R):
    for c in range(0, C):
        if r is not 0 or c is not 0:
            cumulH[r][c] = cumulH[r][c-1] + grid[r][c]
            cumulV[r][c] = cumulV[r-1][c] + grid[r][c]

# print(cumulH)
# print(cumulV)

for r in range(R):
    for c in range(C):
        maxPerim = max(maxPerim, grid[r][c])
        for h in range(2, R - r + 1):
            for w in range(2, C - c + 1):
                try:
                    perim = grid[r][c] + grid[r+h-1][c]
                    # horizontal
                    perim += cumulH[r][c+w-1] - cumulH[r][c]
                    perim += cumulH[r+h-1][c+w-1] - cumulH[r+h-1][c]
                    # vertical
                    perim += cumulV[r+h-2][c] - cumulV[r][c]
                    perim += cumulV[r+h-2][c+w-1] - cumulV[r][c+w-1]
                    # update max
                    maxPerim = max(perim, maxPerim)
                except IndexError as e:
                    # print(r, c, w, h)
                    raise e

print(maxPerim)
