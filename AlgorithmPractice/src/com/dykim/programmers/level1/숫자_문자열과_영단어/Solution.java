package com.dykim.programmers.level1.숫자_문자열과_영단어;

public class Solution {

    public static void main(String[] args) {
        var solution = new Solution();

        System.out.println(solution.solution("one4seveneight"));
        System.out.println(solution.solution("23four5six7"));
        System.out.println(solution.solution("2three45sixseven"));
        System.out.println(solution.solution("123"));
    }

    public int solution(String s) {
        return Integer.parseInt(s
                .replaceAll("zero", "0")
                .replaceAll("one", "1")
                .replaceAll("two", "2")
                .replaceAll("three", "3")
                .replaceAll("four", "4")
                .replaceAll("five", "5")
                .replaceAll("six", "6")
                .replaceAll("seven", "7")
                .replaceAll("eight", "8")
                .replaceAll("nine", "9"));
    }

}
