'''
시간: 20:48 ~ 21:41(53분)

문제점: 파이썬 내장함수 max 를 변수 이름으로 사용하다가 변경함.
이로 인해 print(max) 해서 값이 출력되지 않아 찾아 해멤.
기존 예약어로 설정된 변수이름은 사용하지 않도록 조심하자.
'''
import sys
from collections import deque

# 최대 반복횟수 제한 10만번(이상 넘어가는 경우는 거의 없다. 무한루프 방지용)
sys.setrecursionlimit(100001)

X, Y = map(int, input().split())
map = [list(input()) for _ in range(X)]
visit = [[False] * Y for _ in range(X)]
dxy = [(1, 0), (-1, 0), (0, 1), (0,-1)]

que = deque()
result = -1

# 최단거리 구하기
def bfs():
    global result
    x, y, d = que.popleft()

    for dx, dy in dxy:
        mx = dx + x
        my = dy + y

        if 0 > mx or mx >= X or 0 > my or my >= Y:
            continue
        if map[mx][my] == 'L' and visit[mx][my] == False:
            visit[mx][my] = True
            que.append((mx, my, d + 1))
    
    if que:
        bfs()
    else:
        result = max(result, d)

for x in range(X):
    for y in range(Y):
        if map[x][y] == 'L':
            visit = [[False] * Y for _ in range(X)]
            que.append((x, y, 0))
            visit[x][y] = True
            bfs()

print(result)
