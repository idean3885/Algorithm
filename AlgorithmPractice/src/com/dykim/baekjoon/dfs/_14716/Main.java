package com.dykim.baekjoon.dfs._14716;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * 시간: 20:51 ~ 21:10(19분 성공)
 *
 * 1. 8방 탐색
 * 2. 완전 탐색
 * 3. 글자 수 구하기 -> 1 글자
 */
public class Main {

    private static class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static String[][] graph;
    private static boolean[][] visits;

    private static int N;
    private static int M;

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);

        var parsedInput = scanner.nextLine().split(" ");
        M = Integer.parseInt(parsedInput[0]);
        N = Integer.parseInt(parsedInput[1]);

        graph = new String[M][N];
        visits = new boolean[M][N];
        for (int i = 0; i < M; i++) {
            graph[i] = scanner.nextLine().split(" ");
        }

        var wordCount = 0;
        for (int x = 0; x < M; x++) {
            for (int y = 0; y< N; y++) {
                if (visits[x][y]) {
                    continue;
                }

                if ("1".equals(graph[x][y])) {
                    visitWord(x, y);
                    wordCount++;
                }
            }
        }

        System.out.println(wordCount);
    }

    private static void visitWord(int startX, int startY) {
        int[] dx = {1, 1, 1, 0, 0, -1, -1, -1};
        int[] dy = {1, 0, -1, 1, -1, 1, 0, -1};

        var que = new LinkedList<Pos>();
        que.add(new Pos(startX, startY));

        while (!que.isEmpty()) {
            var pos = que.poll();
            for (int i = 0; i < 8; i++) {
                var mx = pos.x + dx[i];
                var my = pos.y + dy[i];

                if (0 > mx || mx >= M || 0 > my || my >= N) {
                    continue;
                }

                if (visits[mx][my]) {
                    continue;
                }

                if ("1".equals(graph[mx][my])) {
                    que.add(new Pos(mx, my));
                    visits[mx][my] = true;
                }
            }
        }
    }

}
