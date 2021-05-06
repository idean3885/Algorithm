package com.dykim.baekjoon.string._2438;

import java.io.*;

/**
 * 문제
 * 첫째 줄에는 별 1개, 둘째 줄에는 별 2개, N번째 줄에는 별 N개를 찍는 문제
 *
 * 입력
 * 첫째 줄에 N(1 ≤ N ≤ 100)이 주어진다.
 *
 * 출력
 * 첫째 줄부터 N번째 줄까지 차례대로 별을 출력한다.
 */

/**
 * 1. 풀이 과정
 *  1) 버퍼에 저장 후 한번에 출력 -> 성공
 *
 * 2. 풀이 결과
 *  소요시간: 20분 (16:37 ~ 16:57)
 *  결과: 성공
 *
 * 3. 문제 해결
 *  입력값을 받은 뒤, 인덱스에 주의하며 별을 출력한다.
 *
 * 4. 개선할 점
 *  시간을 줄이기 위해 Scanner -> BufferedReader 를 사용함.
 *
 * @term 2021.05.06
 */
public class Main {
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        makeStar(N);
        bw.flush();
        bw.close();
    }

    private static void makeStar(int N) throws IOException{
        for (int i=1; i<=N; i++) {
            for (int j=0; j<i; j++) {
                bw.write('*');
            }
            bw.newLine();
        }
    }
}
