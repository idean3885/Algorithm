package com.dykim.programmers.level2.max_expression;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.*;

/**
 * 시간: 20:38 ~ 23:00(2시간 22분) / 실패
 *
 * 실패 사유
 * 1. 연산자 우선순위를 입력된 모든 식에 대해 계산 -> 실패
 * 2. 연산자 우선순위 3가지 설정 후 값을 복사해 계산하며 최댓값 계산하도록 수정 -> 실패
 * 3. 숫자 자료구조 int -> long으로 변경 -> 성공
 *    int 의 경우, overflow 가 발생하는 케이스들이 실패한다.
 *
 * <p>
 * 문제 분석
 * 숫자, 3가지 연산문자(+, -, *) 로 이루어진 수식
 * 참가자 별 수식 연산자 우선순위는 자유롭게 재정의하여 가장 큰 숫자를 제출한다.
 * <p>
 * 단, 연산자 우선순위가 같은 문자는 없다.
 * <p>
 * 연산자 우선순위 조합 -> n!, 결과값은 절대값으로 최종 제출된다.
 * 연산 수식 문자열이 주어질 때, 우승 시 받을 수 있는 가장 큰 금액 return
 * <p>
 * 문제 풀이
 * 1. 문자열 파싱 -> 숫자, 연산자 배열로 분리
 * 2. 연산자 우선순위 지정
 * 3. 우선순위에 맞춰 연산
 * 4. |최댓값| 구하기
 */

class Solution {
    private final List<Long> nums = new ArrayList<>(); // 피연산자
    private final List<Character> ops = new ArrayList<>(); // 연산자배열

    public long solution(String expression) {
        var answer = Long.MIN_VALUE;

        // 문자열 파싱
        parsed(expression);

        var opList = selOpList();

        for (var opOrders: opList) {
            answer = Math.max(answer, calc(opOrders));
        }

        return answer;
    }

    // 문자열 파싱
    private void parsed(String expression) {
        String regexp = "\\D+";
        var pattern = Pattern.compile(regexp);
        var matcher = pattern.matcher(expression);

        int startIdx = 0;
        while (matcher.find()) {
            var opIdx = matcher.start();
            ops.add(expression.charAt(opIdx));
            nums.add(Long.parseLong(expression.substring(startIdx, opIdx)));
            startIdx = opIdx + 1;
        }
        nums.add(Long.parseLong(expression.substring(startIdx)));
    }

    // 연산자 우선 순위 배정
    private List<char[]> selOpList() {
        return Stream.of(
                new char[] {'+', '-', '*'}
                , new char[] {'+', '*', '-'}
                , new char[] {'-', '+', '*'}
                , new char[] {'-', '*', '+'}
                , new char[] {'*', '+', '-'}
                , new char[] {'*', '-', '+'}
        ).collect(Collectors.toList());
    }

    // 연산자 계산 -> 연산자 한번 계산 후, 숫자 및 연산자를 재정렬시킨다.
    private long calc(char[] selOps) {
        // 임시 계산할 리스트 복사
        var tops = new ArrayList<Character>(ops);
        var tnums = new ArrayList<Long>(nums);

        for (var op: selOps) {
            var isCalc = true;
            while (isCalc) {
                isCalc = false;
                for (int i = 0; i < tops.size(); i++) {
                    if (tops.get(i) == op) {
                        switch (op) {
                            case '+':
                                tnums.set(i, tnums.get(i) + tnums.get(i+1));
                                break;
                            case '-':
                                tnums.set(i, tnums.get(i) - tnums.get(i+1));
                                break;
                            case '*':
                                tnums.set(i, tnums.get(i) * tnums.get(i+1));
                                break;
                        }

                        tnums.remove(i+1); // 계산된 값 제거
                        tops.remove(i);
                        isCalc = true;
                        break;
                    }
                }
            }

        }

        return Math.abs(tnums.get(0));
    }

}