package com.dykim.baekjoon.dp._11726;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * 문제
 *  2×n 크기의 직사각형을 1×2, 2×1 타일로 채우는 방법의 수를 구하는 프로그램을 작성하시오.
 * 
 * 입력
 *  첫째 줄에 n이 주어진다. (1 ≤ n ≤ 1,000)
 * 
 * 출력
 *  첫째 줄에 2×n 크기의 직사각형을 채우는 방법의 수를 10,007로 나눈 나머지를 출력한다.
 * ***************************************************************************
 * 1. 풀이 과정
 *   규칙성을 찾지 않고 순열로 생각하여 푼 결과, 
 *   1000! 값을 담을 곳이 없고 이를 계산할 방법이 없어 오류 발생함.
 * 
 * 2. 풀이 결과
 *  소요시간: 1시간 30분
 *  결과: 실패
 *  
 * 3. 문제 해결
 *   f(n) = f(n-1) + f(n-2) 규칙성을 찾는 것이 포인트인 문제였다.
 *   중요) 이전 계산 결과를 저장하는 알고리즘인 메모이제이션(Memoization) 이 있어야 시간초과가 나지 않는다.
 *        저장할 때, 자료형에 담길 수 있도록 10007로 나눈 나머지를 저장하도록 한다.(문제의 조건)
 *   
 * 4. 개선할 점
 *   규칙성을 찾아 최대한 간단하게 풀 수 있는 방법을 찾아보자.
 *   자료형에 담아지지 않는 값을 어떻게 처리할 지, 생각해야 한다. 
 *   
 *   @since 2021.04.14
 */
public class Main {
    private static int d[] = new int[1001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());

        System.out.println(loop(n));
    }
    
    private static int loop(int n) {
        if (n==1 || n==0) {
            return 1;
        }
        
        if (d[n]>0) {
            return d[n];
        }
        d[n] = (loop(n-1) + loop(n-2))%10007;
                
        return d[n]; 
    }
}
