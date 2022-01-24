n = int(input())
x, y = 1, 1
plans = input().split()

dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]
moveIdx = ['R', 'L', 'D', 'U']

for plan in plans:
    for i in range(len(moveIdx)):
        if plan == moveIdx[i]:
            mx = x + dx[i]
            my = y + dy[i]
            break
    
    if mx < 1 or n < mx or my < 1 or n < my:
        continue
    x, y = mx, my

print(x, y);