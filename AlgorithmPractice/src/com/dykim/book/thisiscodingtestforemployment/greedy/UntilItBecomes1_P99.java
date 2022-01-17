package com.dykim.book.thisiscodingtestforemployment.greedy;

import java.util.*;

public class UntilItBecomes1_P99 {

    private static long N, K;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextLong();
        K = sc.nextLong();

        var count = 0;

        while(N > 1) {
            if (N % K == 0) {
                N /= K;
                count++;
            } else {
                // 나머지를 한번에 빼고 카운트하여 반복횟수를 줄인다.
                var minusValue = N%K;

                N-= minusValue;
                count += minusValue;
            }
        }

        System.out.println(count);
    }

}
