'''
시간: 15:43 ~ 16:29(46분) / 30분 기준 실패
사유
1. 결과값 오름차순 정렬 안함
2. 첫 시작 지점 방문처리하지 않음
'''

import sys
from collections import deque
sys.setrecursionlimit(100001)

N = int(input())
map = [list(map(int, input())) for _ in range(N)]
visit = [[False] * N for _ in range(N)]
que = deque()

dxy = [(1, 0), (-1, 0), (0, 1), (0, -1)]

# 각 단지 별 숫자 저장
complexes = []

# que 호출횟수 = 집 갯수
def bfs(count):
    x, y = que.popleft()
    
    for dx, dy in dxy:
        mx = dx + x
        my = dy + y

        if 0 > mx or mx >= N or 0 > my or my >= N:
            continue

        if map[mx][my] == 1 and visit[mx][my] == False:
            visit[mx][my] = True
            que.append((mx, my))

    if que:
        return bfs(count + 1) # 마지막 탐색 지점으로부터 리턴된 값 전달
    else:
        return count # BFS 특성 상, 갈 곳이 아예 없는 최종 한번만 호출된다.

for x in range(N):
    for y in range(N):
        if map[x][y] == 1 and visit[x][y] == False:
            visit[x][y] = True
            que.append((x, y))
            complexes.append(bfs(1))

complexes.sort()

print(len(complexes))
for c in complexes:
    print(c)
