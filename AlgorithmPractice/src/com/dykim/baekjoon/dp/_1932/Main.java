package com.dykim.baekjoon.dp._1932;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 문제
 *         7
 *       3   8
 *     8   1   0
 *   2   7   4   4
 * 4   5   2   6   5
 * 
 * 위 그림은 크기가 5인 정수 삼각형의 한 모습이다.
 * 맨 위층 7부터 시작해서 아래에 있는 수 중 하나를 선택하여 아래층으로 내려올 때, 이제까지 선택된 수의 합이 최대가 되는 경로를 구하는 프로그램을 작성하라. 
 * 아래층에 있는 수는 현재 층에서 선택된 수의 대각선 왼쪽 또는 대각선 오른쪽에 있는 것 중에서만 선택할 수 있다.
 * 삼각형의 크기는 1 이상 500 이하이다. 삼각형을 이루고 있는 각 수는 모두 정수이며, 범위는 0 이상 9999 이하이다.
 * 
 * 입력
 * 첫째 줄에 삼각형의 크기 n(1 ≤ n ≤ 500)이 주어지고, 둘째 줄부터 n+1번째 줄까지 정수 삼각형이 주어진다.

 * 출력
 * 첫째 줄에 합이 최대가 되는 경로에 있는 수의 합을 출력한다.
 */

/**
 * 1. 풀이 과정
 *  1) f(r,c) = cost[r][c] + min(f(r+1, c), f(r+1, c+1)) 의 점화식 -> Top-Down 방식, 메모이제이션 적용하여 풀이 -> 실패
 *  2) 점화식 f(r,c) = cost[r][c] + max(f(r+1, c), f(r+1, c+1)) 로 수정 -> 성공
 * 
 * 2. 풀이 결과
 *  소요시간: 40분 (15:09 ~ 15:49) 
 *  결과: 성공
 *  
 * 3. 문제 해결
 *  점화식 f(r,c) = cost[r][c] + max(f(r+1, c), f(r+1, c+1))
 *  현재 위치를 2차원으로 저장하고 다음 방문할 위치를 잘 선택해야 한다.
 *   
 * 4. 개선할 점
 *  최댓값 구하는 문제를 최솟값으로 계산하였다. 다음부턴 문제를 제대로 읽도록 해야겠다.
 *  
 * @since 2021.04.19
 */
public class Main {
    private static int[][] memo;
    private static int[][] cost;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        cost = new int[n][n];
        for (int i=0; i<n; i++) {
            String[] rowArr = br.readLine().split(" ");
            
            for (int j=0; j<rowArr.length; j++) {
                cost[i][j] = Integer.parseInt(rowArr[j]);
            }
        }
        
        memo = new int[n][n];
        for (int[] row: memo) {
            Arrays.fill(row, -1);
        }
        
        System.out.println(topToDown(0, 0));
    }
    
    private static int topToDown(int row, int col) {
        if (row == cost.length -1) { // 마지막까지 온 경우
            return cost[row][col];
        }
        
        if (memo[row][col]>-1) {    // 이미 방문한 경우
            return memo[row][col];
        }
        
        memo[row][col] = Math.max(topToDown(row+1, col), topToDown(row+1, col+1)) + cost[row][col];
        
        return memo[row][col];
    }
}
