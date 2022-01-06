package com.dykim.baekjoon.group.financialDeveloper.recursion._15663;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * N과 M (9) 성공
 * 시간 제한	메모리 제한	제출	정답	맞힌 사람	정답 비율
 * 1 초	512 MB	15777	7988	5795	49.370%
 * 문제
 * N개의 자연수와 자연수 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
 *
 * N개의 자연수 중에서 M개를 고른 수열
 * 입력
 * 첫째 줄에 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 8)
 *
 * 둘째 줄에 N개의 수가 주어진다. 입력으로 주어지는 수는 10,000보다 작거나 같은 자연수이다.
 *
 * 출력
 * 한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다. 중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.
 *
 * 수열은 사전 순으로 증가하는 순서로 출력해야 한다.
 */

/**
 * 예제 입력 1
 * 3 1
 * 4 4 2
 * 예제 출력 1
 * 2
 * 4
 * 예제 입력 2
 * 4 2
 * 9 7 9 1
 * 예제 출력 2
 * 1 7
 * 1 9
 * 7 1
 * 7 9
 * 9 1
 * 9 7
 * 9 9
 * 예제 입력 3
 * 4 4
 * 1 1 1 1
 * 예제 출력 3
 * 1 1 1 1
 */
public class Main {

    private static int N, M;
    private static int[] numbers;
    private static boolean[] visits;

    private static int[] pickNumbers;

    private static Set printSet;
    private static StringBuffer sb;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        printSet = new LinkedHashSet();
        sb = new StringBuffer();

        N = sc.nextInt();
        M = sc.nextInt();

        numbers = new int[N];
        visits = new boolean[N];

        pickNumbers = new int[M];

        for (var i=0; i<N; i++) {
            numbers[i] = sc.nextInt();
        }

        Arrays.sort(numbers);
        find(0);
        printSet.forEach(System.out::println);
    }

    private static void find(int pickNum) {
        if (pickNum == M) {
            for (var num : pickNumbers)
                sb.append(num).append(" ");

            printSet.add(sb.toString());
            sb.setLength(0);
            return;
        }

        for (var i=0; i<N; i++) {
            if (visits[i]) continue;

            visits[i] = true;
            pickNumbers[pickNum] = numbers[i];
            find(pickNum + 1);
            visits[i] = false;
        }
    }

}
