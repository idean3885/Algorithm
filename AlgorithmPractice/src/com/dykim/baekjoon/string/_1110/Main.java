package com.dykim.baekjoon.string._1110;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 문제
 * 0보다 크거나 같고, 99보다 작거나 같은 정수가 주어질 때 다음과 같은 연산을 할 수 있다.
 * 먼저 주어진 수가 10보다 작다면 앞에 0을 붙여 두 자리 수로 만들고, 각 자리의 숫자를 더한다.
 * 그 다음, 주어진 수의 가장 오른쪽 자리 수와 앞에서 구한 합의 가장 오른쪽 자리 수를 이어 붙이면 새로운 수를 만들 수 있다. 다음 예를 보자.
 *
 * 26부터 시작한다. 2+6 = 8이다. 새로운 수는 68이다.
 * 6+8 = 14이다. 새로운 수는 84이다.
 * 8+4 = 12이다. 새로운 수는 42이다.
 * 4+2 = 6이다. 새로운 수는 26이다.
 *
 * 위의 예는 4번만에 원래 수로 돌아올 수 있다. 따라서 26의 사이클의 길이는 4이다.
 *
 * N이 주어졌을 때, N의 사이클의 길이를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 N이 주어진다. N은 0보다 크거나 같고, 99보다 작거나 같은 정수이다.
 *
 * 출력
 * 첫째 줄에 N의 사이클 길이를 출력한다.
 */

/**
 * 1. 풀이 과정
 *  1) 2자릿수를 표현하기 위해 int 배열 설정, 매 계산마다 배열로 계산하며 재귀함수 호출 방식으로 해결함 -> 성공
 *
 * 2. 풀이 결과
 *  소요시간: 30분 (17:03 ~ 17:33)
 *  결과: 성공
 *
 * 3. 문제 해결
 *  2자릿수 배열로 변환 후 자릿수를 더한뒤 각 오른쪽 결과를 이어붙인 값이 원본값과 같으면 종료하도록 설정
 *  예) n=55  getDigit(55) = 10 -> 새로 생성된 수 = 50, cycle = 1
 *            getDigit(50) = 05 -> 새로 생성된 수 = 05, cycle = 2
 *            getDigit(05) = 05 -> 새로 생성된 수 = 55, cycle = 3 => 종료;
 *
 * 4. 개선할 점
 *  30분보다 더 빠른 시간 안에 풀 수 있도록 문자열 각 자릿수 처리에 익숙해지자.
 *
 * @term 2021.05.06
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String n = br.readLine();

        int[] digit = getDigit(n);

        System.out.println(getCycle(digit, digit, 1));
    }

    private static int[] getDigit(String n) {
        int[] digit = new int[2];

        if (n.length()==2) {
            digit[0] = n.charAt(0) - 48;
            digit[1] = n.charAt(1) - 48;
        } else {
            digit[1] = n.charAt(0) - 48;
        }

        return digit;
    }

    private static int getCycle(int[] source, int[] digit, int cycle) {
        int[] calcDigit = getDigit(String.valueOf(digit[0] + digit[1]));

        int[] result = new int[2];
        result[0] = digit[1];
        result[1] = calcDigit[1];

        if (source[0]!=result[0] || source[1]!=result[1]) {
            cycle = getCycle(source, result, cycle+1);
        }

        return cycle;
    }
}
