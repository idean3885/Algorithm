N, M = input().split()
N = int(N)
M = int(M)

startX, startY, startD = input().split()
startX = int(startX)
startY = int(startY)
startD = int(startD)

map = [list(map(int, input().split())) for _ in range(N)]

dxys = [(-1, 0), (0, 1), (1, 0), (0, -1)]
visit = [[False]*M for _ in range(N)]
count = 0

def find(x, y, d):
    global count

    for i in range(1, 4):
        md = d - i
        mx = x + dxys[md%4][0]
        my = y + dxys[md%4][1]

        if mx < 0 or N <= mx or my < 0 or M <= my:
            continue
        
        if visit[mx][my] or map[mx][my] == 1:
            continue
        
        visit[mx][my] = True
        count += 1
        find(mx, my, md)

find(startX, startY, startD)
print(count)