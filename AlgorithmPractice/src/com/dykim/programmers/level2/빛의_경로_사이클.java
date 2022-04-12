package com.dykim.programmers.level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 시간: 20:50 ~ 22:00(1시간 10분) / 실패
 *
 * 행,열이 같은 그래프라고 착각하여 아웃오브인덱스 오류가 발생하였다.
 * 이후 방향 설정 시 -1 인 경우 % 4 하면 -3 으로 갈 것이라고 착각하여 잘못된 방향으로 사이클이 돌아 실패했었다.
 * 또한 모든 점에 대해 방문처리를 동시에 진행해야 했는데, 시작점에 대해서만 하면 된다고 생각하여 같은 사이클을 도는 경우를 잡지 못했다.
 * 전체적으로 문제를 분석하고 조건을 추려낼 수 있도록 계속 반복하자.
 *
 *
 * 코드 제출
 * 1. 왼쪽 이동 시, 음수값 % 4 를 했을 때 음수가 나오는 부분에서 인덱스 오류 발생
 * 2. 위 오류 처리했으나, 케이스1 시간초과 / 케이스 3 실패
 * 3. 그래프의 행,열 크기를 구분하도록 수정, 인덱스가 -1이 되는 경우 로직 변경 -> 성공
 *
 * 문제 분석
 * 1. 빛의 이동방향
 *  S - 직진
 *  L - 좌회전
 *  R - 우회전
 * 2. 격자를 넘어가는 경우, 반대 방향 끝으로 돌아옴.
 * 3. 같은 사이클은 1개로 취급함.
 * 4. 각 점마다 4방향 탐색이 가능함.
 * 5. 1차원 격자 배열 문자열 길이는 항상 동일하다.
 *
 * -> 모든 사이클의 길이를 구해 오름차순으로 정렬하여 배열로 전달하기
 *
 * 문제 풀이
 * 1. 한 점 기준 4방향 탐색
 * 2. 시작점에 시작 방향으로 들어오는 경우 종료 처리 -> 예) (1, 1) 아래방향 출발 => 위에서 (1, 1)로 진입 시 종료.
 * 3. 종료된 경우, 사이클 길이 배열에 저장
 * 4. 사이클 진행 중 통과한 방향은 탐색하지 않는다.
 * 5. 통과하지 않은 방향 탐색
 * 6. 1 ~ 5 까지 모든 점 반복
 * 7. 저장된 사이클 길이 배열 오름차순 정렬
 * 8. 리턴
 *
 */
public class 빛의_경로_사이클 {

    public static void main(String[] args) {
        var solution = new 빛의_경로_사이클();
        var grid1 = new String[]{"SL", "LR"};
        var grid2 = new String[]{"S"};
        var grid3 = new String[]{"R", "R"};

        System.out.println(Arrays.toString(solution.solution(grid1)));
    }

    private class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private class Node {
        char dir;
        boolean[] visits;

        public Node(char dir) {
            this.dir = dir;
            visits = new boolean[4];
        }
    }

    // 오른쪽부터 시계방향
    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};

    List<Integer> answer = new ArrayList<>();

    public int[] solution(String[] grid) {
        // 2차원 배열로 전환
        var graph = new Node[grid.length][grid[0].length()];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length(); j++) {
                graph[i][j] = new Node(grid[i].charAt(j));
            }
        }

        // 모든 지점 방문
        for (int x = 0; x < graph.length; x++) {
            for (int y = 0; y < graph[0].length; y++) {
                search(graph, new Pos(x, y));
            }
        }

        // 결과값 정렬
        Collections.sort(answer);
        var result = new int[answer.size()];
        for (int i=0; i < result.length; i++) {
            result[i] = answer.get(i);
        }

        return result;
    }

    // 한 점 4방향 탐색
    private void search(Node[][] graph, Pos pos) {
        for (int i = 0; i < 4; i++) {
            var x = pos.x;
            var y = pos.y;
            var idx = i;
            var dist = 0;
            while(!graph[x][y].visits[idx]) {
                // 방문처리
                graph[x][y].visits[idx] = true;

                // 이동
                x += dx[idx];
                y += dy[idx];
                   dist++;

                // 그래프를 벗어난 경우
                if (0 > x) {
                    x = graph.length - 1;
                } else if (x >= graph.length) {
                    x = 0;
                } else if (0 > y) {
                    y = graph[0].length - 1;
                } else if (y >= graph[0].length) {
                    y = 0;
                }

                // 이동한 지점에서 방향 전환
                if (graph[x][y].dir == 'L')
                    idx = toLeft(idx);
                else if (graph[x][y].dir == 'R')
                    idx = toRight(idx);

            }

            // 사이클이 생긴 경우만 저장
            if (dist > 0)
                answer.add(dist);
        }
    }

    // 왼쪽으로 방향 전환(반시계)
    private int toLeft(int idx) {
        return idx == 0 ? 3 : idx - 1;
    }

    // 오른쪽으로 방향 전환(시계)
    private int toRight(int idx) {
        return (idx + 1) % 4;
    }

}
