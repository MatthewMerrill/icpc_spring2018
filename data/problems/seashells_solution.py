N = int(input())
L = list(map(int, input().split()))

def ans(N, L):
    for i in range(1, N):
        if L[i] < L[i - 1]:
            return False
    return True

print("Sorting Successful" if ans(N, L) else "Sad Sorting, Sally")
