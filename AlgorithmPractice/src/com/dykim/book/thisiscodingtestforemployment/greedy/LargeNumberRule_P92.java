package com.dykim.book.thisiscodingtestforemployment.greedy;

import java.util.Scanner;

public class LargeNumberRule_P92 {

    private static int N, M, K;

    private static int first, second;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();

        for (var i=0; i<N; i ++) {
            var tmp = sc.nextInt();
            if (first < tmp) {
                second = first;
                first = tmp;
            }
            else if (second < tmp) {
                second = tmp;
            }
        }

        var count = M / (K + 1) * K + M % (K + 1);

        System.out.println(count * first + (M - count) * second);
    }

}
