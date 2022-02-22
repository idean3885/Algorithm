'''
시간: 17:16 ~ 17:29(13분) / 성공

기억할 점
1. bfs 내에서 재귀 대신 while 루프 사용 시, 실행시간이 절반으로 단축된다.
  430ms -> 224ms
  추후 시간초과 발생 시, 고려해볼수도?
'''

import sys
from collections import deque

sys.setrecursionlimit(100001)

M, N = map(int, input().split())
banner = [list(map(int, input().split())) for _ in range(M)]
visit = [[False] * N for _ in range(M)]

dxy = [(1, 1), (1, 0), (1, -1), (0, 1), (0, -1), (-1, 1), (-1, 0), (-1, -1)]

que = deque()

# 글자 탐색
def bfs():
    x, y = que.popleft()

    for dx, dy in dxy:
        mx = dx + x
        my = dy + y

        if 0 > mx or mx >= M or 0 > my or my >= N:
            continue
        
        if banner[mx][my] == 1 and visit[mx][my] == False:
            visit[mx][my] = True
            que.append((mx, my))
    
    if que:
        bfs()

result = 0
for x in range(M):
    for y in range(N):
        if banner[x][y] == 1 and visit[x][y] == False:
            visit[x][y] = True
            que.append((x, y))
            result += 1
            bfs()

print(result)
