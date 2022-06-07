package com.dykim.programmers.level2.큰수만들기;

/**
 * 시간: 20:35 ~ 21:35(1시간 목표)
 *
 * 문제 분석
 *  특정 숫자에서 K개 제거 시, 얻을 수 있는 가장 큰 수 구하기
 *  문자열 숫자 number, 제거할 숫자 갯수 k 가 주어졌을 떄, 가장 큰 숫자를 문자열로 리턴하시오.
 *
 * 문제 풀이
 *  1. 문자열 배열 파싱
 *  2. n - k개 고르기
 *  3. 그 중 최댓값 저장
 */
public class Solution {

    public static void main(String... args) {
        Solution solution = new Solution();
        String number1 = "4177252841";
        int k1 = 4;
        System.out.println(solution.solution(number1, k1));
    }

    public String solution(String number, int k) {
        int totalPickCount = number.length() - k;
        var pickNumber = new char[totalPickCount];
        var parsedNumber = number.toCharArray();
        chooseNums(parsedNumber, pickNumber, totalPickCount);

        return arrayToString(pickNumber);
    }

    private void chooseNums(char[] parsedNumber, char[] pickNumber, int totalCount) {
        for (int i = 0, pickIdx = 0; i < parsedNumber.length; i++) {
            if (pickNumber[pickIdx] < parsedNumber[i]) {
                pickNumber[pickIdx] = parsedNumber[i];

                while (true) {
                    if (pickIdx > 0 && pickNumber[pickIdx - 1] < pickNumber[pickIdx] && parsedNumber.length - i - 1 >= totalCount - pickIdx) {
                        pickNumber[pickIdx - 1] = pickNumber[pickIdx];
                        pickNumber[pickIdx] = '0';
                        pickIdx--;
                    } else if (pickIdx < totalCount - 1){
                        pickIdx++;
                        break;
                    } else {
                        break;
                    }
                }
            }
        }
    }

    private String arrayToString(char[] arr) {
        StringBuilder sb = new StringBuilder();
        for (var num : arr) {
            sb.append(num);
        }

        return sb.toString();
    }

}
