package com.dykim.programmers.level2.JadenCase_문자열_만들기;

/**
 * 시간: 20:33 ~ 20:50(17분 성공)
 *
 * 맨 앞 단어 대문자
 * 그 외 소문자
 *  -> 맨 앞이 알파벳이 아닌 경우 제외
 */
public class Solution {

    public static void main(String[] args) {
        var solution = new Solution();

        System.out.println(solution.solution("3people unFollowed me"));
    }

    public String solution(String s) {
        return toJadenCase(s);
    }

    private String toJadenCase(String s) {
        var sb = new StringBuilder();

        var isFirst = true;
        for (var ch : s.toCharArray()) {
            var appendChar = ch;
            if (isFirst && Character.isLowerCase(ch)) {
                appendChar = Character.toUpperCase(ch);
            } else if (!isFirst && Character.isUpperCase(ch)) {
                appendChar = Character.toLowerCase(ch);
            }

            isFirst = ch == ' ';

            sb.append(appendChar);
        }

        return sb.toString();
    }

}
