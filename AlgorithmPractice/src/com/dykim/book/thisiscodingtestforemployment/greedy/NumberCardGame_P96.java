package com.dykim.book.thisiscodingtestforemployment.greedy;

import java.util.Arrays;
import java.util.Scanner;

public class NumberCardGame_P96 {

    private static int N, M;
    private static int[][] map;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        map = new int[N][M];

        var maxNum = Integer.MIN_VALUE;
        for (var i = 0; i<N; i++) {
            // 1. 한줄 입력
            for (var j=0; j<M; j++) {
                map[i][j] = sc.nextInt();
            }

            // 2. 행의 최솟값
            var rowMin = Arrays.stream(map[i]).min().orElse(Integer.MIN_VALUE);

            // 3. 행의 최솟값이 룰에 맞는 최대값인지 확인
            maxNum = Math.max(maxNum, rowMin);
        }

        System.out.println(maxNum);
    }

}
