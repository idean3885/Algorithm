package com.dykim.baekjoon.string._9012;

import java.io.*;

/**
 * 문제
 * 괄호 문자열(Parenthesis String, PS)은 두 개의 괄호 기호인 ‘(’ 와 ‘)’ 만으로 구성되어 있는 문자열이다.
 * 그 중에서 괄호의 모양이 바르게 구성된 문자열을 올바른 괄호 문자열(Valid PS, VPS)이라고 부른다.
 * 한 쌍의 괄호 기호로 된 “( )” 문자열은 기본 VPS 이라고 부른다. 만일 x 가 VPS 라면 이것을 하나의 괄호에 넣은 새로운 문자열 “(x)”도 VPS 가 된다.
 * 그리고 두 VPS x 와 y를 접합(concatenation)시킨 새로운 문자열 xy도 VPS 가 된다.
 * 예를 들어 “(())()”와 “((()))” 는 VPS 이지만 “(()(”, “(())()))” , 그리고 “(()” 는 모두 VPS 가 아닌 문자열이다.
 *
 * 여러분은 입력으로 주어진 괄호 문자열이 VPS 인지 아닌지를 판단해서 그 결과를 YES 와 NO 로 나타내어야 한다.
 *
 * 입력
 * 입력 데이터는 표준 입력을 사용한다. 입력은 T개의 테스트 데이터로 주어진다.
 * 입력의 첫 번째 줄에는 입력 데이터의 수를 나타내는 정수 T가 주어진다.
 * 각 테스트 데이터의 첫째 줄에는 괄호 문자열이 한 줄에 주어진다.
 * 하나의 괄호 문자열의 길이는 2 이상 50 이하이다.
 *
 * 출력
 * 출력은 표준 출력을 사용한다.
 * 만일 입력 괄호 문자열이 올바른 괄호 문자열(VPS)이면 “YES”, 아니면 “NO”를 한 줄에 하나씩 차례대로 출력해야 한다.
 */

/**
 * 1. 풀이 과정
 *  1) 여는 괄호면 +1, 닫힌 괄호면 -1 을 해서 괄호의 합이 0이 되는지 확인한다. 중간에 합이 음수라면 실패로 종료한다. -> 성공
 *
 * 2. 풀이 결과
 *  소요시간: 32분 (13:29 ~ 14:01)
 *  결과: 성공
 *
 * 3. 문제 해결
 *  괄호를 열어야 닫을수 있기 때문에 괄호를 열면 +1, 닫으면 -1로 계산한다.
 *  중간에 합이 음수가 되는 경우, 열지 않고 닫은 것이기 때문에 실패처리한다.
 *
 *  4. 개선할 점
 *  이번에도 코딩이 먼저 나왔다. 알고리즘을 정립한 뒤 코딩을 시작하도록 하자.
 *
 * @term 2021.05.07
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        for (int i=0; i<t; i++) {
            String ps = br.readLine();
            bw.write(isVPS(ps)? "YES" : "NO");
            bw.newLine();
        }

        bw.close();
    }

    private static boolean isVPS(String ps) {
        int cnt = 0;

        for(int i=0; i<ps.length(); i++) {
            char ch = ps.charAt(i);

            if (ch=='(') cnt++;
            else if (ch==')') cnt--;

            if (cnt<0) {
                break;
            }
        }

        return cnt==0;
    }
}