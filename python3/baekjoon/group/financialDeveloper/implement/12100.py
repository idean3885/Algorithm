'''
시간: 17:17 ~ 17:37(20분), 23:00 ~ 01:05(2시간 5분) 총 2시간 25분 / 실패

실패 원인
1. 블록 이동 조건 중 합쳐지는 케이스 조건을 누락함.
2. for 문 stop 인덱스까지 포함인 것으로 착각함. -> 이걸로 30분 넘게 헤맸다...

문제 풀이 노트
상하좌우 이동 -> 블록 전체가 정해진 방향으로 끝까지 움직인다.
움직이는 도중 같은 숫자의 블록과 합쳐질 경우, 숫자 두배가 되며 하나는 사라진다.
한 턴당 같은 블록은 한번만 합쳐질 수 있다. -> 블록이 합쳐지는 경우 플래그 표시 필요.
같은 숫자가 3개 연달아 있는 경우 -> 맨 처음 벽에 닿은 블록을 기준으로 합쳐진다.

최대 5번 이동해서 만들어지는 가장 큰 블록의 값을 구하시오.

1. 이동방향 5가지 순열로 고르기
2. 그 방향 기준 제일 앞 블록부터 이동시키면서 합치기(두 숫자 더하기 = 기존 블록 값 2배)
3. 가장 큰 블록 구하기
'''

import sys
import copy
from collections import deque

sys.setrecursionlimit(100001)

# 블록 한개 한 방향으로 이동시키기
def moveBlock(didx, x, y):
    dx, dy = dxy[didx]
    while True:
        mx = x + dx
        my = y + dy

        # 벽에 도달해 멈추는 경우
        if 0 > mx or mx >= N or 0 > my or my >= N:
            return

        # 다른 값의 블록에 막혀 멈춘 경우
        if graph[mx][my] and graph[mx][my] != graph[x][y]:
            return

        # 이미 합쳐진 블록에 막혀 멈춘 경우
        if merged[mx][my]:
            return
        
        # 합쳐지는 경우
        if graph[mx][my] == graph[x][y] and not merged[mx][my]:
            merged[mx][my] = True
            graph[mx][my] *= 2
            graph[x][y] = 0
            return
        
        # 그 외 - 이동하는 경우
        graph[mx][my] = graph[x][y]
        graph[x][y] = 0

        # 현재 위치 이동
        x = mx
        y = my

# 정해진 5개 방향으로 이동시키기
def fiveMove():
    global merged
    for didx in que: # 큐에서 빼지 않고 순서대로 반복만 시킨다.
        merged = [[False] * N for _ in range(N)] # 한 방향으로 모두 이동 후 초기화

        # 아랫 방향(하) -> x 역순, y 순차탐색
        if didx == 0:
            for x in range(N - 1, -1, -1):
                for y in range(N):
                    if graph[x][y]:
                        moveBlock(didx, x, y)

        # 윗 방향(상) -> x 순차탐색, y 순차탐색
        elif didx == 1:
            for x in range(N):
                for y in range(N):
                    if graph[x][y]:
                        moveBlock(didx, x, y)
        
        # 오른쪽 방향(우) -> y 역순, x 순차탐색
        elif didx == 2:
            for y in range(N-1, -1, -1):
                for x in range(N):
                    if graph[x][y]:
                        moveBlock(didx, x, y)
        
        # 왼쪽 방향(좌) -> y 순차탐색, x 순차탐색
        elif didx == 3:
            for y in range(N):
                for x in range(N):
                    if graph[x][y]:
                        moveBlock(didx, x, y)
    
    # 블록 최댓값 리턴 -> 코드 가독성을 위해 한번에 확인
    result = 0
    for x in range(N):
        for y in range(N):
            result = max(graph[x][y], result)
    return result

# 순열로 5가지 방향 선택 -> 재귀
def pickDirection():
    global result, graph
    if len(que) == 5:
        graph = copy.deepcopy(origraph)
        result = max(result, fiveMove())
        return
    
    for i in range(4):
        que.append(i)
        pickDirection()
        que.pop()

N = int(input())
origraph = [list(map(int, input().split())) for _ in range(N)] # 시작 그래프
merged = [] # 블록 합침 여부

dxy = [(1, 0), (-1, 0), (0, 1), (0, -1)] # 0: 하, 1: 상, 2: 우, 3: 좌

graph = [] # 5방향 정해진 후 블록이동시킬 때 사용하는 임시 맵
que = deque() # 5방향 저장
result = 0 # 최댓값

pickDirection()
print(result)
