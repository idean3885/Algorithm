'''
스터디 6주차 본 문제
시간: 19:45 ~ 20:47(1시간 제한) / 실패

실패 원인
1. 메모리 초과 
    bfs 내 임시 큐를 생성한 뒤 재귀로 계속 쌓아나가다보니 메모리 초과로 실패함.
    BFS 내 재귀를 없애고, while 루프로 반복하도록 수정하여 해결함.

문제 풀이 노트
빈 공간: .
불: *
벽: #
상근이 위치: @

불은 시간마다 4방향으로 번진다. 벽에는 번지지 않음.
불이 있는 칸, 다음 초에 불이 번질 칸은 이동 불가

최단 탈출 시간초 구하기(1초당 1칸, 즉 최단거리)
탈출할수 없는 경우, IMPOSSIBLE 출력

불 퍼트리기(맵에 직접 퍼트리기) -> 1칸이동(BFS) 반복
'''
import sys
from collections import deque
import copy

sys.setrecursionlimit(100001)

dxy = [(1, 0), (-1, 0), (0, 1), (0, -1)]

T = int(input())

# 전역으로 미리 설정
bmap = []
visit = []

hque = deque()
fque = deque()

# 불 퍼트리기 -> 사람 이동
def bfs(w, h):
    global hque, fque

    # 사람이 움직일 수 있는 동안 반복
    while hque:
        # 불 퍼트리기 -> 갈 곳을 미리 퍼트려야 사람이 지나가지 않는다.
        tfque = deque()
        for x, y in fque:
            for dx, dy in dxy:
                mx = dx + x
                my = dy + y

                if 0 > mx or mx >= h or 0 > my or my >= w:
                    continue
                if bmap[mx][my] == '.':
                    bmap[mx][my] = '*'
                    tfque.append((mx, my))
        fque = copy.deepcopy(tfque)

        # 사람이 지나가기
        thque = deque()
        for x, y in hque:
            c = count[x][y] # 방문할 곳의 최단 거리
            for dx, dy in dxy:
                mx = dx + x
                my = dy + y

                # 성공 조건 -> 맵 밖으로 탈출하는 경우
                if 0 > mx or mx >= h or 0 > my or my >= w:
                    print(c)
                    return
                if bmap[mx][my] == '.' and not count[mx][my]:
                    count[mx][my] = c + 1
                    thque.append((mx, my)) 
        hque = copy.deepcopy(thque)

    # 탈출하지 못하고 갈 곳도 없는 경우
    print('IMPOSSIBLE')

for _ in range(T):
    w, h = map(int, input().split())
    bmap = [list(input()) for _ in range(h)]
    count = [[0] * w for _ in range(h)]

    # 불 시작 위치(1개 이상), 사람 시작 위치 파악(1개)
    hque = deque()
    fque = deque()
    for x in range(h):
        for y in range(w):
            if bmap[x][y] == '@':
                hque.append((x, y))
                count[x][y] = 1
            if bmap[x][y] == '*':
                fque.append((x, y))

    bfs(w, h)

