package com.dykim.baekjoon.dp._2839;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 문제
 *  상근이는 요즘 설탕공장에서 설탕을 배달하고 있다. 상근이는 지금 사탕가게에 설탕을 정확하게 N킬로그램을 배달해야 한다. 
 *  설탕공장에서 만드는 설탕은 봉지에 담겨져 있다. 봉지는 3킬로그램 봉지와 5킬로그램 봉지가 있다.
 *  상근이는 귀찮기 때문에, 최대한 적은 봉지를 들고 가려고 한다. 
 *  예를 들어, 18킬로그램 설탕을 배달해야 할 때, 3킬로그램 봉지 6개를 가져가도 되지만, 5킬로그램 3개와 3킬로그램 1개를 배달하면, 더 적은 개수의 봉지를 배달할 수 있다.
 *  상근이가 설탕을 정확하게 N킬로그램 배달해야 할 때, 봉지 몇 개를 가져가면 되는지 그 수를 구하는 프로그램을 작성하시오.
 * 
 * 입력
 *  첫째 줄에 N이 주어진다. (3 ≤ N ≤ 5000)
 *  
 * 출력
 *  상근이가 배달하는 봉지의 최소 개수를 출력한다. 만약, 정확하게 N킬로그램을 만들 수 없다면 -1을 출력한다.
 */

/**
 * 1. 풀이 과정
 *  1) 5나누기 -> 3나누기 로 단순 계산 -> 실패
 *  2) dfs방식으로 변경 -> 시간초과
 *  3) 이전 결과를 저장하는 메모이제이션 추가 -> 성공
 * 
 * 2. 풀이 결과
 *  소요시간: 44분 (10:00 ~ 10:44) 
 *  결과: 성공
 *  
 * 3. 문제 해결
 *   5로 나누는 경우, 3으로 나누는 경우 전부를 탐색하는 과정이 필요하다.
 *   dfs 알고리즘으로 해결하되, 메모이제이션 기법을 사용하여 시간초과되지 않도록 했다.
 *   
 * 4. 개선할 점
 *  dfs 방식이 어색하여 시간이 소요되었다.
 *  꾸준한 문제풀이로 익숙해지도록 하자.
 *  
 * @since 2021.04.15
 */
public class Main {

    private static int memo[] = new int[5001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        int result = dfs(n, 0);
        System.out.println(result!=99999? result: -1);
    }
    
    private static int dfs(int n, int cnt) {
        if (n==0) {
            return cnt;
        }
        
        if (n<3) {
            return 99999;   // 불가능한 경우 최솟값을 갱신하지 않기 위해 큰값 전달
        }
        
        if (memo[n]>0) {
            return memo[n];
        }
                
        int cnt5 = dfs(n-5, cnt+1);
        int cnt3 = dfs(n-3, cnt+1);
        
        memo[n] = cnt5>cnt3? cnt3: cnt5;
        return memo[n];
    }
}
