from collections import deque

dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]

def bfs():
    x, y = que.popleft()
    
    for i in range(4):
        mx = x + dx[i]
        my = y + dy[i]
        
        if 0 > mx or M <= mx or 0 > my or N <= my:
            continue
        if map[mx][my] == 1 and visit[mx][my] == False:
            visit[mx][my] = True
            que.append((mx, my))
                
    if len(que) > 0:
        bfs()

T = input()
T = int(T)

results = []
for _ in range(T):
    M, N, K = input().split()
    M = int(M)
    N = int(N)
    K = int(K)
    
    map = [[0] * N for _ in range(M)]
    visit = [[False] * N for _ in range(M)]
    xy = deque()

    for i in range(K):
        x, y = input().split()
        x = int(x)
        y = int(y)
        xy.append((x, y))
        map[x][y] = 1

    que = deque()
    count = 0
    for x, y in xy:
        if visit[x][y]:
            continue

        visit[x][y] = True
        que.append((x, y))
        count += 1
        bfs()

    results.append(count)
    
for result in results:
    print(result)
