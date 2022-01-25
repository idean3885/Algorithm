import math

# 풀이기록 2차 한쪽방향 재귀(왼쪽, 오른쪽 재귀)
gears = [list(map(int, input())) for _ in range(4)]
k = int(input())
plans = [list(map(int, input().split())) for _ in range(k)]

def right(idx, dir):
    if idx < 3 and gears[idx][2] != gears[idx+1][-2]:
        right(idx+1, -dir)

    if (dir == 1):
        tmp = gears[idx].pop()
        gears[idx].insert(0, tmp)
    else:
        tmp = gears[idx].pop(0)
        gears[idx].append(tmp)

def left(idx, dir):
    if idx > 0 and gears[idx][-2] != gears[idx-1][2]:
        left(idx-1, -dir)

    if (dir == 1):
        tmp = gears[idx].pop()
        gears[idx].insert(0, tmp)
    else:
        tmp = gears[idx].pop(0)
        gears[idx].append(tmp)

for plan in plans:
    idx = plan[0] - 1   # 바퀴번호는 인덱스보다 1 크다
    dir = plan[1]
    if idx < 3 and gears[idx][2] != gears[idx+1][-2]:
        right(idx+1, -dir)
    if idx > 0 and gears[idx][-2] != gears[idx-1][2]:
        left(idx-1, -dir)
    if (dir == 1):
        tmp = gears[idx].pop()
        gears[idx].insert(0, tmp)
    else:
        tmp = gears[idx].pop(0)
        gears[idx].append(tmp)

sum = 0;
for i in range(len(gears)):
    sum += math.pow(2, i) * gears[i][0]

print(int(sum))
