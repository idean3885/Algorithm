from collections import deque

M, N = input().split()
M = int(M)
N = int(N)

map = [list(map(int, input().split())) for _ in range(N)]
que = deque()
targetCount = 0

# 익은 토마토 위치, 익지 않은 토마토 갯수 저장
for x in range(N):
    for y in range(M):
        if map[x][y] == 1:
            que.append((x, y))
        if map[x][y] == 0:
            targetCount += 1

dxy = [(-1, 0), (1, 0), (0, 1), (0, -1)]

def bfs():
    global que, targetCount

    if len(que) == 0:
        return False # 종료

    tmp = deque()
    for x, y in que:
        for dx, dy in dxy:
            mx = x + dx
            my = y + dy

            if 0 > mx or mx >= N or 0 > my or my >= M:
                continue
            
            if map[mx][my] == 0:
                map[mx][my] = 1
                targetCount -= 1
                tmp.append((mx, my))
    
    que = tmp
    return True # 계속 탐색

days = -1
while bfs():
    days += 1

print(days if targetCount == 0 else -1)
