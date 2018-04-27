N, T, S = map(int, input().split())

paths = []
edges = {}

line = input().strip()

while line != '-1':
    paths = paths + [list(map(int, line.split()))]
    line = input().strip()
    
