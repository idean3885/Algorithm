package com.dykim.baekjoon.group.financialDeveloper.recursion._15649;

import java.util.Scanner;

/**
 * N과 M (1) 실패
 * 시간 제한	메모리 제한	제출	정답	맞힌 사람	정답 비율
 * 1 초	512 MB	48166	29464	19672	60.408%
 * 문제
 * 자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
 *
 * 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
 * 입력
 * 첫째 줄에 자연수 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 8)
 *
 * 출력
 * 한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다. 중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.
 *
 * 수열은 사전 순으로 증가하는 순서로 출력해야 한다.
 */
/**
 * 예제 입력 1
 * 3 1
 * 예제 출력 1
 * 1
 * 2
 * 3
 * 예제 입력 2
 * 4 2
 * 예제 출력 2
 * 1 2
 * 1 3
 * 1 4
 * 2 1
 * 2 3
 * 2 4
 * 3 1
 * 3 2
 * 3 4
 * 4 1
 * 4 2
 * 4 3
 * 예제 입력 3
 * 4 4
 * 예제 출력 3
 * 1 2 3 4
 * 1 2 4 3
 * 1 3 2 4
 * 1 3 4 2
 * 1 4 2 3
 * 1 4 3 2
 * 2 1 3 4
 * 2 1 4 3
 * 2 3 1 4
 * 2 3 4 1
 * 2 4 1 3
 * 2 4 3 1
 * 3 1 2 4
 * 3 1 4 2
 * 3 2 1 4
 * 3 2 4 1
 * 3 4 1 2
 * 3 4 2 1
 * 4 1 2 3
 * 4 1 3 2
 * 4 2 1 3
 * 4 2 3 1
 * 4 3 1 2
 * 4 3 2 1
 */

class Main {

    private static int[] nums;
    private static boolean[] visits;

    private static int N;
    private static int M;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        for (int i=1; i<=N; i++) {
            visits = new boolean[N + 1];
            nums = new int[M];
            find(i, 0);
        }
    }

    private static void find(int position, int pickNum) {
        visits[position] = true;
        nums[pickNum] = position;

        for (int i=1; i<=N && pickNum < M - 1; i++) {
            if (visits[i]) continue;

            find(i, pickNum +1);
        }

        // 한줄 출력
        if (pickNum == M -1)
            printNums();

        // visit false 처리
        visits[position] = false;
    }

    private static void printNums() {
        // 한줄 출력
        for (int num : nums) {
            System.out.print(num + " ");
        }
        // 개행
        System.out.println();
    }

}
