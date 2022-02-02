N, M = input().split()
N = int(N)
M = int(M)

map = [list(map(int, input())) for _ in range(N)]
visit = [[False]*M for _ in range(N)]
dx = [-1, 1, 0, 0]
dy = [0, 0, 1, -1]

def dfs(x, y):
    visit[x][y] = True
    for i in range(4):
        mx = x + dx[i]
        my = y + dy[i]

        if 0 <= mx and mx < N and 0 <= my and my < M:
            if map[mx][my] == 0 and visit[mx][my] == False:
                dfs(mx, my)

count = 0
for x in range(N):
    for y in range(M):
        if visit[x][y] == False and map[x][y] == 0:
            dfs(x, y)
            count+=1
print(count)