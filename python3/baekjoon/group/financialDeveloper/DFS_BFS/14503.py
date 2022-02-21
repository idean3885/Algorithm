'''
시간: 21:47 ~ 23:02(1시간 15분)
성공여부: 실패

실패 원인
1. 후진 기능 미구현
2. 이동이 정해진 경우, 다른 방향 탐색은 중지해야함. -> 재귀함수 탐색의 전형적인 방식인 스택구조를 활용하면 틀림

해설
방향이 정해지면 이동하고 끝나기 때문에, 4방향 탐색 중 이동하게 되는 경우, 나머지 방향은 탐색하면 안된다.
DFS 구조이지만, 전형적인 스택 구조로 탐색하는 것을 생각하면 틀림.
-> 스택 구조 대신, 후진으로 이전 위치로 돌아가며 다시 탐색하는 방식으로 진행해야 한다.
'''

N, M = map(int, input().split())
X, Y, D = map(int, input().split())

map = [list(map(int, input().split())) for _ in range(N)]
visit = [[0] * M for _ in range(N)]

dxy = [(-1, 0), (0, 1), (1, 0), (0, -1)]

def dfs(x, y, d):
    visit[x][y] = 1

    # 왼쪽방향 탐색
    for i in range(1, 5):
        md = (d-i) % 4
        mx = x + dxy[md][0]
        my = y + dxy[md][1]

        if map[mx][my] == 0 and visit[mx][my] == 0:
            dfs(mx, my, md)
            return # 진행가능한 경우, 다른 방향은 탐색하지 않는다. -> 후진으로 다시 와야함.
    
    # 후진 가능한지 확인
    md = (d - 2) % 4
    mx = x + dxy[md][0]
    my = y + dxy[md][1]
    if map[mx][my] == 0:
        dfs(mx, my, d) # 현재 방향 유지한채 이동

dfs(X, Y, D)

result = 0
for x in range(N):
    for y in range(M):
        result += visit[x][y]

print(result)