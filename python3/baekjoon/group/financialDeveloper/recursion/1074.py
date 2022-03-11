'''
시간: 22:07 ~ 22:57(50분) / 성공

풀이 결과
1. 문법 오류 발생 -> 탐색 실패한 경우 리턴해주지 않아 실패함.
2. 시간 초과 -> 목표좌표까지 모두 탐색해서 발생한 문제로 보임
3. 목표 좌표가 포함되지 않은 사분면은 계산하고 탐색하지 않도록 수정 -> 성공

문제 분석
2*2 배열은 Z 모양으로 방문 -> (0, 0) -> (0, 1) -> (1,0) -> (1, 1)
2^N 배열 = 2^N-1 배열로 4등분 후 Z 탐색

N 이 주어졌을 때, r행 c열을 몇 번째로 방문하는지 출력하기

풀이 방식
1. 왼쪽 위 부터 탐색
2. 현재 배열 크기가 2보다 큰 경우 -> 현재 그래프 칸을 기준으로 재탐색
3. 그래프 크기가 2인 경우 -> Z 순서로 탐색
'''
import math

N, r, c = map(int, input().split())

# 목표 좌표가 포함된 최소단위 Z 탐색
def moveZ(x, y):
    global r, c

    dxy = [(0, 0), (0, 1), (1, 0), (1, 1)] # 현재 위치 포함 탐색순서(Z 순서)
    count = 0
    for dx, dy in dxy:
        mx, my = x + dx, y + dy

        # 목표위치인 경우
        if mx == r and my == c:
            break

        count += 1 # 탐색 횟수 증가

    # 계산 횟수 전달
    return count

# 탐색단위 나누기
def find(sx, sy, size):
    global r, c

    # 최소 단위면서 목표 좌표를 포함하는 경우
    if size == 2:
        return moveZ(sx, sy)

    count = 0
    step = int(size/2)
    for x in range(sx, sx + size, step):
        for y in range(sy, sy + size, step):
            # 해당 범위에 목표지점이 포함되는 경우
            if x <= r < x + step and y <= c < y + step:
                tcount = find(x, y, step) # 크기 줄여 재탐색
                count += tcount # 탐색 횟수 더하기

                return int(count)

            # 그 외 - 해당 사분면 탐색횟수 계산하여 더하기
            count += math.pow(step, 2)

    # 목표 좌표가 잘못되지 않는 한 4분면 탐색 중 무조건 목표좌표가 걸리기 때문에 이 위치까지 코드가 도달하지 않는다.
    # 따라서 루프 완료 후 따로 리턴하지는 않는다.

print(find(0, 0, int(math.pow(2, N))))
