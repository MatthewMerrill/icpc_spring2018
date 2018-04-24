
def sumRange(n):
    if n == 0:
        return 0
    elif n < 0:
       return -sumRange(-n) + 1
    else:
        return n * (n + 1) // 2

n = int(input())
while n != 0:
    print("N = %d\tSum = %d" % (n, sumRange(n)))
    n = int(input())
