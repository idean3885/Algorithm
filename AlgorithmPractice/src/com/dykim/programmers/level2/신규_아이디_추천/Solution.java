package com.dykim.programmers.level2.신규_아이디_추천;

/**
 * 시간: 20:30 ~ 21:07(37분 해결)
 *
 * 문제 분석
 * 1. 3<= 길이 <= 15
 * 2. 소문자, 숫자, -, _, . 만 사용가능
 * 3. . 양 옆에는 무조건 다른 문자가 있어야 함.
 */
public class Solution {

    public static void main(String[] args) {
        var solution = new Solution();
        System.out.println(solution.solution("abcdefghijklmn.p"));
    }

    public String solution(String newId) {
        // 1단계 - 소문자 변경
        newId = newId.toLowerCase();

        // 2단계 - 특수문자 삭제
        newId = newId.replaceAll("[^\\da-z_\\-.]", "");

        // 3단계 - 마침표 줄이기
        newId = newId.replaceAll("[.]+", ".");

        // 4단계 - 처음, 끝 . 삭제
        newId = dotTrim(newId);

        // 5단계 - 빈 문자열 확인
        if ("".equals(newId)) {
            newId = "a";
        }

        // 6단계 - 16자 이상 확인
        if (newId.length() >= 16) {
            newId = newId.substring(0, 15);

            // 끝 마침표 한번 더 제거
            newId = dotTrim(newId);
        }

        // 7단계 - 2자 이하 확인
        newId = appendString(newId);

        return newId;
    }

    private String dotTrim(String str) {
        var charArray = str.toCharArray();
        var arrLen = charArray.length;

        var sb = new StringBuilder();

        if (charArray[0] != '.') {
            sb.append(charArray[0]);
        }

        for (int i = 1; i < arrLen - 1; i++) {
            sb.append(charArray[i]);
        }

        if (charArray[arrLen - 1] != '.') {
            sb.append(charArray[arrLen - 1]);
        }

        return sb.toString();
    }

    private String appendString(String newId) {
        if (newId.length() > 2) {
            return newId;
        }

        var sb = new StringBuilder(newId);

        while (sb.length() <= 2) {
            sb.append(sb.charAt(sb.length()-1));
        }

        return sb.toString();
    }

}
