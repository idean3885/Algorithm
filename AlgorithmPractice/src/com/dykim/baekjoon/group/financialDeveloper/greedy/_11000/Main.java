package com.dykim.baekjoon.group.financialDeveloper.greedy._11000;

import java.util.*;

public class Main {

    private static class ST implements Comparable<ST>{
        long S;
        long T;

        public ST(long S, long T) {
            this.S = S;
            this.T = T;
        }

        @Override
        public int compareTo(ST next) {
            if (S == next.T) {
                return 1;
            }
            else if (T == next.S) {
                return 0;
            } else {
                return -1;
            }
        }
    }
    private static int N;

    private static long S, T;

    private static PriorityQueue<ST> queue;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        queue = new PriorityQueue<>();

        for (int i=0; i<N; i++) {
            S = sc.nextLong();
            T = sc.nextLong();
            queue.add(new ST(S, T));
        }
        findClass();
    }

    private static void findClass() {
        int result = 1;
        ST preST = queue.poll();
        while (!queue.isEmpty()) {
            ST st = queue.poll();
            if (preST.T != st.S) result++;

            preST = st;
        }

        System.out.println(result);
    }

}
