package com.dykim.baekjoon.group.financialDeveloper.greedy._11497;

import java.util.*;

public class Main {

    private static int T, N;
    private static int[] numbers;

    private static int[] result;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();
        for (int i=0; i<T; i++) {
            N = sc.nextInt();
            numbers = new int[N];
            for (int j=0; j<N; j++) {
                numbers[j] = sc.nextInt();
            }
            Arrays.sort(numbers);
            greedy();
        }
    }

    private static void greedy() {
        result = new int[N];
        int lineIdx = 0;
        // 짝수 정방향
        for (int i =0; i<N; i+=2) {
            result[lineIdx++] = numbers[i];
        }

        // 홀수 뒤부터 채우기
        int start = N % 2 == 0? N-1 : N-2;
        for(int i=start; 0<i; i-=2) {
            result[lineIdx++] = numbers[i];
        }

        printResult();
    }

    private static void printResult() {
        int max = Integer.MIN_VALUE;
        for (int i=0; i<N; i++) {
            int nextIdx = nextIdx(i+1);
            int tmp = Math.abs(result[i] - result[nextIdx]);
            max = Math.max(max, tmp);
        }
        System.out.println(max);
    }

    private static int nextIdx(int idx) {
        return idx == N ? 0 : idx;
    }

}
