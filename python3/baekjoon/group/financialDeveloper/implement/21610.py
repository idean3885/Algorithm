'''
시간: 20:38 ~ 21:50(1시간 5분, 스터디 마감 얘기 7분) / 실패

문제 제출
1. 구름 생성 > 이동 > 비내리고 구름 삭제 > 물복사 > 구름 생성 순으로 반복 -> 물복사 기준을 대각선이 아니라 가로세로로 계산하여 실패함
2. 물복사 오류 가로세로 > 대각선 으로 수정 -> 성공

문제 분석
마법사 상어 -> 파이어볼, 토네이도, 파이어스톰, 물복사버그
추가된 마법 -> 비바라기: 하늘에 비구름을 만든다.

크기 N*N
A[r][c] = r행 c열에 저장된 물의 양
좌상단 (1,1) / 우하단(N, N) => 각 행, 열의 끝은 연결되어있다.

비바라기로 생성된 구름 (N-1, 1) ~ (N, 2)4칸 차지 => M번 이동
각 이동당 방향 및 거리 설정
방향 : 1 ~ 8(좌측부터 시계방향)

1. 같은 방향으로 구름 이동(경계를 넘어가는 경우도 계산해야함.)
2. 구름이 멈춘 위치 물의양 1씩 증가(4칸 각각 1씩 증가)
3. 구름 사라짐
4. 물이 증가한 칸에 물복사 버그 시전 -> 대각선 방향 거리1인 칸에 물이 있는 바구니 수만큼 물의 양 증가(물의 양 X, 물이 1이상인 바구니 갯수)
  => 경계를 넘어가는 칸은 x
5. 물 2 이상인 모든 칸에 구름이 생기며 물의 양이 칸마다 2씩 줄어든다.
  => 3에서 사라진 칸에는 구름이 생기지 않는다.(반복할 때마다 구름이 사라지는 칸이 바뀌는듯?)

M번 이동 후 바구니 물 양의 총 합 구하기

문제 풀이
1. 시작점부터 반복(N-1, 1) ~ (N, 2)
2. 구름 단체이동(좌표를 이동시킴)
3. 비 내리고 삭제(맵에 1 증가)
4. 물복사(경계 제외 대각선 물이 들어있는 바구니 갯수)
5. 크기 2이상 위치에 구름 생성
6. M회 반복
'''
from collections import deque

# 구름 이동 > 비내리기 > 물복사 > 다음 구름 생성 1회
def solve(que, d, s): # 구름 위치 큐, 이동방향 및 거리
    visit = [[False] * N for _ in range(N)] # 구름이 멈춘 위치
    cque = deque() # 구름이 멈춘 위치
    
    # 구름 이동 > 비내리기
    while que:
        x, y = que.popleft()
        dx, dy = dxy[d]

        # 경계 넘어가는 지점 고려하여 나머지 계산
        mx, my = (x + dx * s) % N, (y + dy * s) % N
        
        # 구름 이동 및 비 내리기
        graph[mx][my] += 1
        visit[mx][my] = True # 사라진 위치 표시
        cque.append((mx, my)) # 사라진 위치 저장
    
    # 물복사 -> 모든 구름 이동 후 계산한다.
    while cque:
        copywater(cque.popleft())

    # 다음 구름 생성
    for x in range(N):
        for y in range(N):
            if not visit[x][y] and graph[x][y] >= 2:
                que.append((x, y)) # 구름 생성
                graph[x][y] -= 2 # 생성된 구름만큼 물 양 감소

    return que

# 물복사 -> 그래프에 바로 반영시킴
def copywater(xy):
    x, y = xy
    for i in range(2, 9, 2):
        dx, dy = dxy[i]
        mx, my = x + dx, y + dy

        # 범위 벗어나는 경우 생략
        if 0 > mx or mx >= N or 0 > my or my >= N:
            continue
        
        # 바구니가 비지 않은 경우
        if graph[mx][my]:
            graph[x][y] += 1

N, M = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(N)]
dsque = deque()
for _ in range(M):
    dsque.append(list(map(int, input().split())))

dxy = [(), (0, -1), (-1, -1), (-1, 0), (-1, 1), (0, 1), (1, 1), (1, 0), (1, -1)] # 1 ~ 8 까지의 방향

# 시작 구름 위치 저장
que = deque()
que.append((N - 2, 0))
que.append((N - 2, 1))
que.append((N - 1, 0))
que.append((N - 1, 1))

# M번 반복
for d, s in dsque:
    que = solve(que, d, s)

# 물 총량 구하기
result = 0
for x in range(N):
    for y in range(N):
        result += graph[x][y]

print(result)
