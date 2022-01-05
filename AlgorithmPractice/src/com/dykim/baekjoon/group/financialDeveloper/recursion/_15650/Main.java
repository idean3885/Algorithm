package com.dykim.baekjoon.group.financialDeveloper.recursion._15650;

/**
 * 문제
 * 자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
 *
 * 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
 * 고른 수열은 오름차순이어야 한다.
 * 입력
 * 첫째 줄에 자연수 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 8)
 *
 * 출력
 * 한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다. 중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.
 *
 * 수열은 사전 순으로 증가하는 순서로 출력해야 한다.
 */

import java.util.Scanner;

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
 * 2 3
 * 2 4
 * 3 4
 * 예제 입력 3
 * 4 4
 * 예제 출력 3
 * 1 2 3 4
 */
public class Main {

    private static int N;
    private static int M;

    private static int[] numbers;
    private static boolean[] visits;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        numbers = new int[M];
        visits = new boolean[N + 1];
        find(1, 0);
    }

    private static void find(int position, int pickNum) {
        if (pickNum == M) {
            for (int num : numbers) {
                System.out.print(num + " ");
            }
            System.out.println();
            return;
        }

        for (int i=position; i<=N; i++) {
            if (visits[i]) continue;

            visits[i] = true;
            numbers[pickNum] = i;

            find(i+1, pickNum+1);

            visits[i] = false;
        }
    }

}
