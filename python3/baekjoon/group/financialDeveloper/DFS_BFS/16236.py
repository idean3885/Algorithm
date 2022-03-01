'''
시간: 20:33 ~ 21:33(1시간 스터디 본 문제) / 실패

실패 원인
1. 상어 위치를 문자열 '9' 로 탐색하여 찾지 못해 실패함.
2. 이동한 상어 위치를 그래프에 표시하지 않아서 실패함.
3. 같은 거리에 있는 물고기 중 가장 상단 -> 가장 왼쪽 물고기를 고르지 못해서 실패함.
   -> 전체 결과 값 중 가장 높이 있는 물고기를 골라야하기 때문에, 물고기 위치를 bfs 중에 파악해선 안된다.
     예) 이동해야할 위치: 우 > 우 / 실제 탐색: 좌 > 하(왼쪽을 먼저 탐색하기 때문에 거기서 이어지는 곳을 최단으로 찾게 된다.)
4. 3번 조건에 맞춰 물고기 높이 비교 로직 추가 -> 성공


문제 분석
N*N 크기
물고기 M 마리 / 아기 상어 1 마리(크기 2로 시작)
물고기와 아기 상어는 크기가 각각 존재함.

상하좌우 이동
크기가 큰 물고기는 못 지나감.
같거나 작은 칸은 지나감
자기보다 작은 물고기만 먹을 수 있음. -> 같을 경우, 지나가는 것만 가능

더 이상 먹을 수 있는 물고기가 없는 경우, 종료
먹을 수 있는 물고기 1 마리면 그 물고기를 먹는다
그 외 가장 가까운 물고기를 먹으러 간다.(지나야하는 칸 갯수의 최솟값)

같은 거리에 물고기가 많다면
1. 가장 위에 있는 물고기(x 가 작은 순서)
2. 가장 왼쪽에 있는 물고기(y가 작은 순서)

이동과 동시에 물고기 먹으면 그 칸은 빈칸이 된다.
자신과 같은 크기만큼 물고기를 먹어야 한다.(크기:2 => 2마리 먹으면 3이 된다.)

몇 초 동안 물고기를 최대한 잡아먹을 수 있는지 구하시오

# 풀이 생각
1. 가장 가까운 물고기 찾기(bfs) -> 거리 1, 2, 3 순서대로 탐색하며 물고기가 탐색되거나 그래프 끝까지 탐색.
2. 찾은 경우, 이동 및 먹기 -> 먹은 마릿 수 카운트, 크기 증가 확인
3. 1부터 반복
4. 시간초 출력
'''
from collections import deque

N = int(input())

# 0: 빈 칸 / 1~6: 물고기 / 9: 아기상어(크기 2)
graph = [list(map(int, input().split())) for _ in range(N)]

for x in range(N):
    for y in range(N):
        if graph[x][y] == 9:
            shark = (x, y, 2, 0) # 아기상어 좌표, 현재 크기, 먹은 물고기 수

dxy = [(-1, 0), (0, -1), (0, 1), (1, 0)] # 우선순위: 상 좌 우 하

# 물고기 찾기
def bfs(que):
    global graph, shark

    dist = 0 # 물고기 찾는 거리
    visit = [[False] * N for _ in range(N)] # 방문점 초기화
    visit[shark[0]][shark[1]] = True

    while que:
        tque = deque() # 상어 위치 임시 저장
        dist += 1

        isFind = False # 물고기 탐색 여부
        sx = sy = 20 # 상어가 이동할 위치
        while que:
            x, y = que.popleft()

            for dx, dy in dxy:
                mx = x + dx
                my = y + dy

                # 맵을 나가는 경우
                if 0 > mx or mx >= N or 0 > my or my >= N:
                    continue

                #  이미 방문한 경우
                if visit[mx][my]:
                    continue

                # 먹을 수 있는 물고기가 발견된 경우
                if 0 < graph[mx][my] < shark[2]:
                    isFind = True

                    # 가장 높은 좌표 저장
                    if mx < sx or (mx == sx and my < sy):
                        sx = mx
                        sy = my

                    continue
                
                # 이동할 수 있는 경우 - 빈 곳 또는 같은 크기의 물고기 위치
                if graph[mx][my] == 0 or graph[mx][my] == shark[2]:
                    tque.append((mx, my))
                    visit[mx][my] = True
        
        # 탐색 완료 시, 반복 종료
        if isFind:
            # 그래프에서 상어 이동
            graph[sx][sy] = 9
            graph[shark[0]][shark[1]] = 0

            # 상어 위치 저장
            count = shark[3] + 1
            size = shark[2] + 1 if count == shark[2] else shark[2]
            count = 0 if size > shark[2] else count
            shark = (sx, sy, size, count)

            # 탐색 종료
            return dist # 다음 물고기까지의 거리 전달

        # 다음 방문위치
        que = tque

    return 0 # 물고기 못 찾은 경우 0

result = 0

# 물고기 못 찾을 때까지 반복 -> 최초 1회는 실행하도록 do-while 구조로 작성
while True:
    que = deque() # 물고기 탐색 위치(임시상어 위치)

    # 상어 위치 큐에 저장
    que.append((shark[0], shark[1]))

    # 물고기 찾아 이동
    dist = bfs(que)

    # 이동 거리 저장
    result += dist

    # 종료 조건 확인
    if dist == 0:
        print(result)
        break
