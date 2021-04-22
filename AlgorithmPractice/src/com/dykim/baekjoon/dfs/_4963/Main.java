package com.dykim.baekjoon.dfs._4963;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 문제
 *  정사각형으로 이루어져 있는 섬과 바다 지도가 주어진다. 섬의 개수를 세는 프로그램을 작성하시오.

 *  한 정사각형과 가로, 세로 또는 대각선으로 연결되어 있는 사각형은 걸어갈 수 있는 사각형이다. 
 *  두 정사각형이 같은 섬에 있으려면, 한 정사각형에서 다른 정사각형으로 걸어서 갈 수 있는 경로가 있어야 한다. 
 *  지도는 바다로 둘러싸여 있으며, 지도 밖으로 나갈 수 없다.
 *	
 * 입력
 *  입력은 여러 개의 테스트 케이스로 이루어져 있다. 
 *  각 테스트 케이스의 첫째 줄에는 지도의 너비 w와 높이 h가 주어진다. 
 *  w와 h는 50보다 작거나 같은 양의 정수이다.
 *  
 *  둘째 줄부터 h개 줄에는 지도가 주어진다. 1은 땅, 0은 바다이다.
 *  입력의 마지막 줄에는 0이 두 개 주어진다.
 * 
 * 출력
 *  각 테스트 케이스에 대해서, 섬의 개수를 출력한다. 
 */

/**
 * 1. 풀이 과정
 *  1) dfs 작성, 시작점부터 모든 지점을 방문하며 방문 가능하면서 가보지 않은 곳인 경우 탐색 -> 무한 루프, 실패
 *  2) 방문하던 곳을 다시 가던 부분 수정 -> 배열 범위 벗어남, 실패
 *  3) 맵 크기에 맞는 인덱스인지 확인 -> 실패
 * 
 * 2. 풀이 결과
 *  소요시간: 48분 (17:10 ~ 17:58)
 *  결과: 실패
 *  
 * 3. 문제 해결
 *   
 * 4. 개선할 점
 *  
 * @term 2021.04.22
 */
public class Main {
    private static final int W = 0;
    private static final int H = 1;
    
    private static int[][] map;
    private static boolean[][] visit;
    
    private static int[] dr = {1, 1, 1, -1, -1, -1, 0, 0};
    private static int[] dc = {1, 0, -1, 1, 0, 1, 1, -1};
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        while(true) {
            String[] wh = br.readLine().split(" ");
            if ("0".equals(wh[W])) {
                break;
            }
            
            int w = Integer.parseInt(wh[W]);
            int h = Integer.parseInt(wh[H]);
            map = new int[h][w];
            visit = new boolean[h][w];
            
            // 맵 입력
            for (int i=0; i< h; i++) {
                String[] row = br.readLine().split(" ");
                
                for (int j=0; j<w; j++) {
                    map[i][j] = Integer.parseInt(row[j]);
                }
            }
            
            // 섬 갯수 카운트
            int count = 0;
            for (int row=0; row<h; row++) {
                for(int col=0; col<w; col++) {
                    
                    if (map[row][col]==1 && !visit[row][col]) {
                        dfs(row, col);
                        count++;
                    }
                }
            }
            
            bw.write(""+count);
            bw.newLine();
        }
        
        bw.close();
    }
    
    private static void dfs(int row, int col) {
        visit[row][col] = true;
        
        // 8방향 탐색
        for (int i=0; i<8; i++) {
            int mr = row + dr[i];
            int mc = col + dc[i];
            
            // 인덱스 범위 & 방문하지 않은 곳 & 땅
            if ((0 <=mr && mr < map.length) && (0<= mc && mc < map[0].length)) {
                if (map[mr][mc]==1 && !visit[mr][mc]) {
                    dfs(mr, mc);
                }
            }
        }
    }
}
