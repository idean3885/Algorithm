package com.dykim.test.programmers.level1.push_keypad;

import com.dykim.programmers.level1.push_keypad.Solution;

public class SolutionTest {

    public static void main(String[] args) {
        var solution = new Solution();

        var numbers = new int[]{1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
        var hand = "right";

        System.out.println(solution.solution(numbers, hand));
    }
}
