import sys
import copy
from collections import deque

# 최대 반복횟수 제한 10만번(이상 넘어가는 경우는 거의 없다. 무한루프 방지용)
sys.setrecursionlimit(100001)

N, M = map(int, input().split())

map = [list(map(int, input().split())) for _ in range(N)]
vxy = deque() # 바이러스 위치 -> 고정
scount = 0 # 안전지역 크기 -> 벽 3개 세우기 전(세운 뒤 3개 줄여야 함.)

# 바이러스 위치, 안전지역 찾기
for x in range(N):
    for y in range(M):
        if map[x][y] == 2:
            vxy.append((x, y))
        elif map[x][y] == 0:
            scount += 1

# 바이러스가 이동할 수 있는 방향
dxy = [(1, 0), (-1, 0), (0, 1), (0, -1)]

# 바이러스 퍼트린 뒤 안전지역 구하기
def spreadBirus():
    pmap = copy.deepcopy(map) # 벽이 세워진 맵에 바이러스 퍼트리기 deep copy
    
    # 바이러스 퍼트리기
    for vx, vy in vxy:
        tmp = deque()
        tmp.append((vx, vy))
        while tmp:
            x, y = tmp.popleft()
            for dx, dy in dxy:
                mx = x + dx
                my = y + dy

                # 맵 벗어난 경우
                if 0 > mx or mx >= N or 0 > my or my >= M:
                    continue

                if pmap[mx][my] == 0:
                    pmap[mx][my] = 2
                    tmp.append((mx, my))
    
    # 안전지역 구하기
    count = 0
    for x in range(N):
        for y in range(M):
            if pmap[x][y] == 0:
                count += 1

    return count # 안전지역 크기 전달

result = 0 # 안전지역 저장

# 벽 3개 고른 뒤 퍼트리기
def pickWalls(sx, sy, pickCount):
    global result
    if pickCount == 3:
        result = max(result, spreadBirus()) # 바이러스 퍼트린 뒤 안전지역 구하기
        return

    # 벽을 세운 위치부터 반복하여 시간 단축
    for x in range(sx, N):
        # 다음 줄부턴 0부터 탐색할 수 있도록 조건문 추가
        for y in range(sy if sx == x else 0, M):
            if map[x][y] == 0:
                map[x][y] = 1 # 벽 세우기
                pickWalls(x, y, pickCount + 1) # 다시 탐색
                map[x][y] = 0 # 벽 빼기

pickWalls(0, 0, 0)
print(result)
