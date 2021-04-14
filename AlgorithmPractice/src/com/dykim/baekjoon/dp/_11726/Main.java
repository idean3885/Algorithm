package com.dykim.baekjoon.dp._11726;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * 1. 풀이 과정
 *   규칙성을 찾지 않고 순열로 생각하여 푼 결과, 
 *   1000! 값을 담을 곳이 없고 이를 계산할 방법이 없어 오류 발생함.
 * 
 * 2. 문제 해결
 *   결국 1시간 30분이 지나도 풀지 못해서 인터넷 검색하여 품.
 *   
 * 3. 개선할 점
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
