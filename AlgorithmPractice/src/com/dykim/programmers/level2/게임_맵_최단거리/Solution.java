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

        private int dist;

        public Square(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
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
        int answer = findMinDist(maps);
        return answer == 0 ? -1 : answer;
    }

    private int findMinDist(int[][] maps) {
        int[] dx = new int[]{1, -1, 0, 0};
        int[] dy = new int[]{0, 0, 1, -1};

        Queue<Square> que = new LinkedList<>();
        que.add(new Square(0, 0, 1));

        int dist = 0;
        int mapWidth = maps.length;
        int mapHeight = maps[0].length;
        boolean[][] visits = new boolean[mapWidth][mapHeight];
        while (!que.isEmpty()) {
            Square square = que.poll();

            if (square.x == mapWidth - 1 && square.y == mapHeight - 1) {
                return square.dist;
            }

            for (int i = 0; i < 4; i++) {
                int mx = square.x + dx[i];
                int my = square.y + dy[i];

                if (0 > mx || mx >= mapWidth || 0 > my || my >= mapHeight) {
                    continue;
                }

                if (visits[mx][my]) {
                    continue;
                }

                if (maps[mx][my] == WALL) {
                    continue;
                }

                que.add(new Square(mx, my, square.dist + STEP));
                visits[mx][my] = true;
            }
        }

        return dist;
    }

}
