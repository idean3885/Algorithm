package com.dykim.baekjoon.dp._1149;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 문제
 *  RGB거리에는 집이 N개 있다. 거리는 선분으로 나타낼 수 있고, 1번 집부터 N번 집이 순서대로 있다.
 *  집은 빨강, 초록, 파랑 중 하나의 색으로 칠해야 한다. 각각의 집을 빨강, 초록, 파랑으로 칠하는 비용이 주어졌을 때, 아래 규칙을 만족하면서 모든 집을 칠하는 비용의 최솟값을 구해보자.
 *      
 *      1번 집의 색은 2번 집의 색과 같지 않아야 한다.
 *      N번 집의 색은 N-1번 집의 색과 같지 않아야 한다.
 *      i(2 ≤ i ≤ N-1)번 집의 색은 i-1번, i+1번 집의 색과 같지 않아야 한다.
 * 
 * 입력
 *  첫째 줄에 집의 수 N(2 ≤ N ≤ 1,000)이 주어진다. 
 *  둘째 줄부터 N개의 줄에는 각 집을 빨강, 초록, 파랑으로 칠하는 비용이 1번 집부터 한 줄에 하나씩 주어진다. 
 *  집을 칠하는 비용은 1,000보다 작거나 같은 자연수이다.
 *  
 * 출력
 *  첫째 줄에 모든 집을 칠하는 비용의 최솟값을 출력한다.
 */

/**
 * 1. 풀이 과정
 *  1) 처음 집부터 rgb 를 순서대로 끝까지 탐색하는 경우로 생각 -> dfs 로 풀고 메모이제이션 사용 -> 실패
 *  2) 현재 위치 + 이 후 마지막까지 탐색한 비용 으로 착각함. 
 *     마지막까지 탐색한 경우 이미 최종 비용이 계산된 경우이기 때문에 해당 값을 저장 후 리턴하도록 수정 -> 실패
 * 
 * 2. 풀이 결과
 *  소요시간: 분 (15:15 ~ 16:22) 
 *  결과: 실패
 *  
 * 3. 문제 해결
 *   
 * 4. 개선할 점
 *  
 * @since 2021.04.16
 */
public class Main {
    private static int costMap[][];
    private static int memo[][];
    
    private static final int R = 0;
    private static final int G = 1;
    private static final int B = 2;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        costMap = new int[n][3];
        memo = new int[n][3];
        
        Arrays.asList(costMap).stream()
            .forEach(row -> {
                try {
                    String[] rowArr = br.readLine().split(" ");
                    row[0] = Integer.parseInt(rowArr[0]);
                    row[1] = Integer.parseInt(rowArr[1]);
                    row[2] = Integer.parseInt(rowArr[2]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        
        int min = 999999999;
        for (int i=0; i<3; i++) {
            int cost = dfs(0, i, costMap[0][i]);
            min = cost<min? cost:min;
        }
        
        System.out.println(min);
    }
    
    private static int dfs(int p, int rgb, int cost) {
        // 집 끝까지 다 온 경우
        if (p == memo.length-1) {
            if (memo[p][rgb]==0 || memo[p][rgb]> cost) {
                memo[p][rgb] = cost;
            }
            
            return memo[p][rgb];
        }
        
        // 이미 계산된 경우
        if (memo[p][rgb]>0) {
            // 이전에 계산된 값보다 큰 경우 갱신
            if (memo[p][rgb] > cost) {
                memo[p][rgb] = cost;
            }
            
            return memo[p][rgb];
        }
        
        int np = p +1;
        if (rgb == R) {
            memo[p][rgb] =Math.min(dfs(np, G, cost + costMap[np][G]), dfs(np, B, cost + costMap[np][B]));
        }
        else if (rgb == G) {
            memo[p][rgb] = Math.min(dfs(np, R, cost + costMap[np][R]), dfs(np, B, cost + costMap[np][B]));
        }
        else {
            memo[p][rgb] = Math.min(dfs(np, R, cost + costMap[np][R]), dfs(np, G, cost + costMap[np][G]));
        }
        
        // 마지막까지 다녀온 값은 가중치를 모두 들고 있기 때문에 그대로 리턴
        return memo[p][rgb];
    }
}
