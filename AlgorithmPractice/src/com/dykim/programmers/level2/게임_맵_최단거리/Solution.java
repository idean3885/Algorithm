package com.dykim.programmers.level2.게임_맵_최단거리;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 시간: 20:26 ~ 20:57(31분 해결)
 * <p>
 * 최단거리 구하기
 * -> 도착할 수없는 경우 -1
 */
public class Solution {

    private class Square {
        private int x;
        private int y;

        public Square(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private final int WALL = 0;
    private final int STEP = 1;

    public static void main(String[] args) {
        Solution solution = new Solution();
        var maps = new int[][]{
                {1, 0, 1, 1, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 1, 1},
                {1, 1, 1, 0, 1},
                {0, 0, 0, 0, 1}
        };

        System.out.println(solution.solution(maps));
    }

    public int solution(int[][] maps) {
        return findMinDist(maps);
    }

    private int findMinDist(int[][] maps) {
        int[] dx = new int[]{1, -1, 0, 0};
        int[] dy = new int[]{0, 0, 1, -1};

        Queue<Square> que = new LinkedList<>();
        que.add(new Square(0, 0));

        int n = maps.length;
        int m = maps[0].length;
        int[][] visits = new int[n][m];
        while (!que.isEmpty()) {
            Square square = que.poll();

            if (square.x == n - 1 && square.y == m - 1) {
                return visits[square.x][square.y] + 1;
            }

            for (int i = 0; i < 4; i++) {
                int mx = square.x + dx[i];
                int my = square.y + dy[i];

                if (0 > mx || mx >= n || 0 > my || my >= m) {
                    continue;
                }

                if (visits[mx][my] > 0) {
                    continue;
                }

                if (maps[mx][my] == WALL) {
                    continue;
                }

                que.add(new Square(mx, my));
                visits[mx][my] = visits[square.x][square.y] + STEP;
            }
        }

        return -1;
    }

}
