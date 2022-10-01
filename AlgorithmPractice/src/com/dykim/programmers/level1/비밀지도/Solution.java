package com.dykim.programmers.level1.비밀지도;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        var solution = new Solution();

        var n = 5;
        var arr1 = new int[]{9, 20, 28, 18, 11};
        var arr2 = new int[]{30, 1, 21, 17, 28};
        System.out.println(Arrays.toString(solution.solution(n, arr1, arr2)));
    }

    public String[] solution(int n, int[] arr1, int[] arr2) {
        // 벽: 1 -> 1 || 2 => 벽으로 공식 표기 => 두 지도 합이 1이상은 벽
        // 공백: 0 -> 1 && 2 => 공백 카운트 2 확인 후 벽 표기 => 두 지도 합 0인 경우만 공백

        // 1. 이진수 분석 -> 0: 공백, 1: 벽 -> 맵 만들기
        // 2. 맵 합치기 -> 한 줄당 문자열로 만들어 리스트에 담기
        // 3. 리턴
        return mergeMap(n, arrToMap(n, arr1), arrToMap(n, arr2));
    }

    private int[][] arrToMap(int n, int[] arr) {
        var result = new int[n][n];

        for (int i = 0; i < arr.length; i++) {
            var number = arr[i];

            for (int j = n-1; j > -1 && number > 0; j--) {
                result[i][j] = number % 2;
                number /= 2;
            }
        }

        return result;
    }

    private String[] mergeMap(int n, int[][] map1, int[][] map2) {
        var mergedMap = new String[n];

        var sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.setLength(0);
            for (int j = 0; j < n; j++) {
                sb.append(map1[i][j] + map2[i][j] > 0 ? '#' : ' ');
            }
            mergedMap[i] = sb.toString();
        }

        return mergedMap;
    }

}