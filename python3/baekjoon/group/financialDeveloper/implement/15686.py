from collections import deque

N, M = input().split()
N = int(N)
M = int(M)

map = [list(map(int, input().split())) for _ in range(N)]

# 집, 치킨집 찾아두기
hxy = []
cxy = []
for x in range(N):
    for y in range(N):
        if map[x][y] == 1:
            hxy.append((x, y))
        if map[x][y] == 2:
            cxy.append((x, y))

dxy = [(1, 0), (-1, 0), (0, 1), (0, -1)]

# 치킨 거리 구하기
def find(hx, hy, picks):
    dist = 9999999
    for cx, cy in picks:
        dist = min(dist, abs(cx - hx) + abs(cy - hy))
    return dist;

# 치킨집 고르기(조합)
answer = 99999999
def pickChickenHouse(pickNum, picks, idx):
    global answer

    # 도시의 치킨 거리 구하기
    if pickNum == M:
        cityResult = 0
        for x, y in hxy:
            cityResult += find(x, y, picks)
        answer = min(answer, cityResult)
        return
    
    # 치킨집 고르기
    for i in range(idx, len(cxy)):
        picks.append(cxy[i])
        pickChickenHouse(pickNum + 1, picks, i + 1)
        picks.pop()

pickChickenHouse(0, [], 0)
print(answer)