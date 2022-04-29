package com.dykim.baekjoon._23291;

import java.util.Scanner;

/**
 * 시간: 20:48 ~ 22:00 목표 / 실패
 * -> 공중부양 로직에 시간을 오래 잡아먹음
 *   => 제출 시, 인덱스 범위 에러 발생
 *   => 배열 범위 1씩 늘림, 두번 접는 로직 y 인덱스 수정 -> 성공
 *
 * 문제 설명
 * 어항 당 물고기 1마리 이상
 *
 * 어항정리 순서
 * 1. 물고기 수 가장 적은 어항 전부 물고기 +1
 * 2. 맨 왼쪽 어항을 다음 어항 위에 쌓는다.
 * 3-1. 2개 이상 쌓인 어항 90도 회전(시계방향 1/4)
 * 3-2. 남은 어항 위에 회전시킨 어항 쌓기(바닥이 위보다 같거나 많아야 쌓을수 있음.
 * 3-3. 회전시켜 쌓을 수 없을 때까지 반복
 * 4. 인접한 물고기 차이 구하기(동시에 이동, 이동 중 물고기 마릿수가 모자라는 경우는 생각하지 않는다.)
 *   -> 차이 / 5 = d
 *   -> d > 0 : 많은 곳의 물고기 d마리 적은 곳으로 이동
 *   -> 모든 칸에 대해 진행
 * 5. 왼쪽부터 진행, 아래부터 위로 순서대로 1열로 정렬
 * 6. 왼쪽 절반 180도(시계방향) 회전 후 오른쪽 위로 쌓기 => 두번해서 바닥의 어항 수 N/4 로 만든다.
 * 7. 4, 5번 진행
 *
 * => 물고기 최대 - 최소 <= k 가 되는 정리횟수 구하기.
 *
 * 4 <= N <= 100 (4의 배수)
 * 행당 최대 100열
 * 최대 행은 100열보다 작음
 * -> 100 * 100 행렬로 잡고 계산
 * -> 인덱스 오류 발생하여 101*101로 수정
 *
 *
 */
public class Main {

    private static int[][] graph = new int[101][101]; // 물고기 작업할 그래프
    private static int N;
    private static int K;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        var split = sc.nextLine().split(" ");
        N = Integer.parseInt(split[0]);
        K = Integer.parseInt(split[1]);

        // 0. 물고기 저장
        var fishes = sc.nextLine().split(" ");
        for (int i=0; i < N; i++) {
            graph[0][i] = Integer.parseInt(fishes[i]);
        }

        var isEnd = false;
        var count = 0;
        do {
            isEnd = cleanFish();
            count++;
        } while (!isEnd);

        System.out.println(count);
    }

    private static boolean cleanFish() {
        // 1. 가장 적은 어항 전체 물고기 + 1
        var min = Integer.MAX_VALUE;
        for (int i=0; i < N; i++) {
            if (graph[0][i] < min) min = graph[0][i];
        }
        for (int i=0; i < N; i++) {
            if (graph[0][i] == min) graph[0][i]++;
        }

        // 2, 3. 어항 돌리기
        var startIdx = rotate();

        // * 4. 인접한 물고기 차이 구하기
        moveFish(startIdx);

        // * 5. 왼쪽부터 진행, 아래부터 위로 순서대로 1열로 정렬
        sortFish(startIdx);

        // * 6. 왼쪽 절반 180도(시계방향) 회전 후 오른쪽 위로 쌓기 => 두번해서 바닥의 어항 수 N/4 로 만든다.
        foldFish();

        // * 7. 4, 5번 진행
        moveFish(N - N/4);
        sortFish(N - N/4);

        // 8. 물고기 최대 최소 차이 구하기
        var minFish = Integer.MAX_VALUE;
        var maxFish = Integer.MIN_VALUE;
        for (int y = 0; y < N; y++) {
            var fish = graph[0][y];
            maxFish = Math.max(fish, maxFish);
            minFish = Math.min(fish, minFish);
        }

        return maxFish - minFish <= K;
    }

    // 어항 돌리기
    private static int rotate() {
        // i: 0 1 2 3 4 5 6 7 ...
        // x: 1 1 2 2 3 3 4 4 ...
        // y: 1 2 2 3 3 4 4 5 ...

        // 현재 위치
        int y = 0;

        // 회전시켜 쌓을 수 없을 때까지 반복
        for (int i=0; i < 20; i++) {
            int w = i / 2 + 1; // 회전시킬 가로 크기
            int h = (i + 1) / 2 + 1; // 세로 크기

            var stackStart = y + w; // 쌓을 좌표 시작점

            // 회전시킬 수 없는 경우 종료
            if (stackStart + h > N) break;

            // 회전시키기
            // 가까운 열부터 행을 하나씩 키워가며 쌓는다.
            for (int j = stackStart - 1; j >= y ; j--) {
                for (int k = 0; k < h; k++) {
                    graph[stackStart - j][stackStart + k] = graph[k][j];
                    graph[k][j] = 0;
                }
            }

            //  시작 좌표 이동
            y = y + w;
        }

        return y; // 회전한 물고기의 최종 시작 위치 전달
    }

    private static void moveFish(int startIdx) {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        // 현재 위치 기준 갈 수 있는 모든 경우의 수에 대해 한번씩만 임시 이동 -> 이동 중 모자라는 경우는 생각하지 않는다.
        var tmpGraph = new int[101][101];
        for (int x = 0; x < N; x++) {
            for (int y = startIdx; y < N; y++) {
                for (int i = 0; i < 4; i++) {
                    var mx = x + dx[i];
                    var my = y + dy[i];

                    if (graph[x][y] == 0) continue;
                    if (0 > mx || mx >= N || 0 > my || my >= N) continue;
                    if (graph[mx][my] == 0) continue;

                    var d = (graph[x][y] - graph[mx][my]) / 5;
                    if (d > 0) {
                        tmpGraph[x][y] -= d;
                        tmpGraph[mx][my] += d;
                    }
                }
            }
        }

        // 임시이동한 케이스 본 그래프에 적용
        for (int x = 0; x < N; x++) {
            for (int y = startIdx; y < N; y++) {
                graph[x][y] += tmpGraph[x][y];
            }
        }
    }

    private static void sortFish(int startIdx) {
        // * 5. 왼쪽부터 진행, 아래부터 위로 순서대로 1열로 정렬
        // 짝수로 쌓이기 때문에 이를 펼치면 겹치지 않는다. 따라서 순서에 맞춰 앞에 다시 정렬하면 됌.
        var sortIdx = 0;
        for (int y = startIdx; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (graph[0][sortIdx] > 0) return; // 쌓이지 않은 부분에 도달할 경우 종료

                if (graph[x][y] > 0) {
                    graph[0][sortIdx++] = graph[x][y];
                    graph[x][y] = 0;
                }
            }
        }
    }

    private static void foldFish() {
        // 한번 접기
        for (int y = N / 2; y < N; y++) {
            graph[1][y] = graph[0][N - 1 - y];
            graph[0][N - 1 - y] = 0;
        }

        // 두번 접기
        for (int x = 1; x >= 0; x--) {
            for (int y = N -  N / 4; y < N; y++) {
                graph[3 - x][y] = graph[x][2*(N-N/4) - 1 - y];
                graph[x][2*(N-N/4) - 1 - y] = 0;
            }
        }
    }

}
