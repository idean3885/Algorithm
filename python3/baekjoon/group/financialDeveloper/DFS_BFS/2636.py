'''

시간: 치즈 기준 1차 시도: 실패 | 공기 기준 2차 시도: 18:20 ~ 19:14(34분) / 성공

1. 치즈 기준으로 탐색하여 실패함. -> 동떨어진 치즈도 동시에 녹여야 한다.
2. 공기 기준으로 재탐색 진행 -> 실패
3. 탐색 전 모든 치즈가 녹은 경우, 결과를 출력하고 종료하도록 조건 추가 -> 성공
 
문제분석
판의 끝에는 치즈가 없다 -> 탐색 시, 맵 나가는지 확인할 필요가 없음.

공기와 접촉된 칸의 치즈는 1시간이 지나면 녹아 없어진다.
 -> 단, 치즈로 둘러쌓인 공간의 경우 녹지 않는다.
 -> 일부가 녹아 외부와 노출되면 1시간 뒤 녹는다.

출력
1줄: 치즈가 모두 녹는데 걸리는 시간 => 시간 더하다가 종료 후 출력
2줄: 다 녹기 1시간 전 치즈 갯수 구하기 => 현재 모양을 계속 저장하다가 종료 시점에선 저장하지 않고 갯수 파악(이전 모양에서 갯수 파악)

빈칸: 0, 치즈: 1

문제 풀이
1. 치즈가 녹을 부분 탐색 -> 2 표시(숫자 그래프이기 때문에 2로 변경)
2. 2 표시된 부분 치즈 녹이기, 1시간 경과 누적

공기 탐색
4방 탐색
1. 공기 위치 찾기
2. bfs 탐색
  1) 현재 맵 치즈 갯수 파악
  2) 치즈와 닿은 경우 녹임 표시(2) 만 한다.
  3) 방문처리할 경우, 다음 시간 탐색 시 녹아 공기가 된 지점을 갈 수 없다.
  4) 치즈 녹이기
  5) 시간 경과시키기

3. 종료시간 출력
4. 마지막 시점에 저장된 치즈 갯수 출력
'''
from collections import deque

 # 공기 탐색 1초
def bfs(que):
    visit = [[False] * M for _ in range(N)]

    # 현재 시간동안 공기위치 BFS
    while que:
        x, y = que.popleft()
            
        for dx, dy in dxy:
            mx, my = x + dx, y + dy

            # 그래프를 벗어나는 경우 다음위치 탐색
            if 0 > mx or mx >= N or 0 > my or my >= M:
                continue

            # 방문한 경우, 다음 위치 탐색
            if visit[mx][my]:
                continue

            # 녹일 치즈를 만난 경우
            if graph[mx][my] == 1:
                graph[mx][my] = 2 # 다음위치를 녹일 지점으로 표시
                continue

            # 공기를 만난 경우
            if graph[mx][my] == 0:
                visit[mx][my] = True
                que.append((mx, my))

# 치즈 갯수 구하기
def findCheeze(graph):
    count = 0
    for line in graph:
        for cell in line:
            if cell == 1:
                count += 1
    return count

# 치즈 녹이기
def melt(graph):
    for x in range(N):
        for y in range(M):
            if graph[x][y] == 2:
                graph[x][y] = 0

N, M = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(N)]

dxy = [(1, 0), (0, 1), (0, -1), (-1, 0)] # 공기 탐색

# 맵 전체 공기 탐색
def find_air():
    time = 0
    cheeze = 0

    for x in range(N):
        for y in range(M):
            # 모든 치즈가 녹은 경우
            if findCheeze(graph) == 0:
                print(time)
                print(cheeze)
                return

            if graph[x][y] == 0:
                cheeze = findCheeze(graph) # 녹이기 전 치즈 갯수 파악

                # 탐색 시작 위치
                que = deque()
                que.append((x, y))

                bfs(que) # 녹일 치즈 찾기
                
                time += 1 # 1초 경과
                melt(graph) # 치즈 녹이기

find_air()
