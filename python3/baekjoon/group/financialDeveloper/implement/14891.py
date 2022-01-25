import math

# 풀이기록
# 1. 문자열 입력을 받지 못해 시간 초과
# 2. 본인 위치 방문처리를 잘못하여 실패
# 3. 바퀴번호를 인덱스로 착각하여 문제를 틀림
# 4. 결과를 실수로 출력하여 실패
# 5. 성공
gears = [list(map(int, input())) for _ in range(4)]
k = int(input())
plans = [list(map(int, input().split())) for _ in range(k)]
visit = [False for _ in range(4)]

def exp(idx, dir):
    if idx == 0:
        if not visit[idx+1] and gears[idx][2] != gears[idx+1][-2]:
            visit[idx] = True
            exp(idx+1, dir * -1)
            visit[idx] = False
    elif idx == 1 or idx == 2:
        if not visit[idx+1] and gears[idx][2] != gears[idx+1][-2]:
            visit[idx] = True
            exp(idx+1, dir * -1)
            visit[idx] = False
        if not visit[idx-1] and gears[idx][-2] != gears[idx-1][2]:
            visit[idx] = True
            exp(idx-1, dir * -1)
            visit[idx] = False
    else:
        if not visit[idx-1] and gears[idx][-2] != gears[idx-1][2]:
            visit[idx] = True
            exp(idx-1, dir * -1)
            visit[idx] = False
    
    if (dir == 1):
        tmp = gears[idx].pop()
        gears[idx].insert(0, tmp)
    else:
        tmp = gears[idx].pop(0)
        gears[idx].append(tmp)

for plan in plans:
    exp(plan[0] - 1, plan[1]) # 바퀴번호는 인덱스보다 1 크다

sum = 0;
for i in range(len(gears)):
    sum += math.pow(2, i) * gears[i][0]

print(int(sum))
