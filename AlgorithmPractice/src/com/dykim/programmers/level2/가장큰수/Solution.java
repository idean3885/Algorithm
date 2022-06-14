package com.dykim.programmers.level2.가장큰수;

import java.util.Arrays;

/**
 * 시간: 20:50 ~ 21:50 (1시간 목표)
 *
 * 문제 분석
 * 1. 정수 배열을 이어 붙여 만들 수 있는 가장 큰 수 구하기
 * 2. 0 <= n <= 1000
 * 3. 이어 붙인 숫자가 너무 클 수 있다 -> 숫자로 계산이 안될 수 있다.
 *    -> 문자열 컴페어로 비교해보자
 */
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(new int[]{1, 101}));
    }

    public String solution(int[] numbers) {
        StringBuilder sb = new StringBuilder();

        String[] arr = toStringArray(numbers);

        // 기본 정렬 -> 오름차순, 내림차순 정렬을 위해 컴페어 결과에 * -1
        Arrays.sort(arr, (v1, v2) -> -Integer.compare(Integer.parseInt(v1 + v2), Integer.parseInt(v2 + v1)));

        for (String numStr : arr) {
            sb.append(numStr);
        }

        String answer = sb.toString();

        if (answer.charAt(0) == '0') {
            answer = "0";
        }

        return answer;
    }

    private String[] toStringArray(int[] numbers) {
        String[] arr = new String[numbers.length];

        for (int i=0; i<arr.length; i++) {
            arr[i] = String.valueOf(numbers[i]);
        }

        return arr;
    }

}
