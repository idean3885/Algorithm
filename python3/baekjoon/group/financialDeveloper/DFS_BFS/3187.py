'''
스터디 8주차
시간: 20:34 ~ 21:09(35분) / 30분에 풀이 완료, 5분동안 출력하는 법을 못 찾음...

문제 분석
같은 울타리 영역 안의 양들이 더 많을 경우, 늑대를 전부 잡아먹는다.

빈 공간: . / 울타리: # / 늑대: v / 양: k
울타리에 갇히지 않은 양, 늑대는 없다.
4방향 이동

살아남는 양 출력
살아남는 늑대 출력

풀이 방법
1. 기준 정하기(늑대 또는 양)
2. 맵 순차탐색하며 한마리 찾기 => 완전 탐색
3. 찾은 위치를 시작으로 방문 처리 및 늑대/양 위치 각각 저장 => bfs
4. 적은 쪽의 마릿수 삭제
5. 전체 방문 처리 확인 후, 남은 양 / 늑대 순서대로 출력 => 완전 탐색
'''
from collections import deque

# 울타리 탐색 -> 기준은 양
def search_kill(que):
    global X, Y

    sheeps = deque() # 울타리 내 양 마릿수
    wolves = deque() # 울타리 내 늑대 마릿수

    # 울타리 탐색
    while que:
        x, y = que.popleft()

        for dx, dy in dxy:
            mx = x + dx
            my = y + dy

            # 갈 수 없는 방향 생략
            if 0 > mx or mx >= X or 0 > my or my >= Y:
                continue

            # 가본 곳 생략
            if visit[mx][my]:
                continue

            # 양이 있는 경우
            if graph[mx][my] == 'k':
                visit[mx][my] = True
                que.append((mx, my))
                sheeps.append((mx, my))
            
            # 늑대가 있는 경우
            elif graph[mx][my] == 'v':
                visit[mx][my] = True
                que.append((mx, my))
                wolves.append((mx, my))

            # 빈 곳인 경우
            elif graph[mx][my] == '.':
                visit[mx][my] = True
                que.append((mx, my))
    
    # 양이 많은 경우
    if len(sheeps) > len(wolves):
        for x, y in wolves:
            graph[x][y] = '.'
    # 그 외 - 늑대가 많거나 양과 늑대 마릿수가 같은 경우
    else:
        for x, y in sheeps:
            graph[x][y] = '.'

X, Y = map(int, input().split())

graph = [list(input()) for _ in range(X)]
visit = [[False] * Y for _ in range(X)]

dxy = [(1, 0), (-1, 0), (0, 1), (0, -1)]

# 울타리 전체 탐색 -> 기준 양
for x in range(X):
    for y in range(Y):
        if graph[x][y] == 'k' and not visit[x][y]:
            que = deque()
            que.append((x, y))
            search_kill(que) # 울타리 탐색 및 삭제

result = [0, 0]
# 남은 마릿수 계산
for x in range(X):
    for y in range(Y):
        if graph[x][y] == 'k': # 양
            result[0] += 1
        if graph[x][y] == 'v': # 늑대
            result[1] += 1

# 출력
print(result[0], result[1])
