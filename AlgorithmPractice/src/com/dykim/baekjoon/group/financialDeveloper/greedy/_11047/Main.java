package com.dykim.baekjoon.group.financialDeveloper.greedy._11047;

import java.util.*;

public class Main {

    private static int N;
    private static long K;

    private static long[] numbers;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextLong();

        numbers = new long[N];

        for (int i = N-1; i>=0; i--) {
            numbers[i] = sc.nextLong();
        }

        int count = 0;
        for (long num : numbers) {
            if (K == 0) break;

            if (K / num > 0) {
                count += K/num;
                K %= num;
            }
        }

        System.out.println(count);
    }

}
