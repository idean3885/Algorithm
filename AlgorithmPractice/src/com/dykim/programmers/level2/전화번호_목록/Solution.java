package com.dykim.programmers.level2.전화번호_목록;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        var solution = new Solution();
        var phoneBook = new String[]{"119", "97674223", "1195524421"};
        System.out.println(solution.solution(phoneBook));
    }

    public boolean solution(String[] phoneBook) {
        // 1. 오름차순 정렬 -> 사전 순서로 정렬
        Arrays.sort(phoneBook);

        // 2. 정렬되었기 때문에 바로 옆만 비교하면 된다. -> 따라서 O(n)
        return linearSearch(phoneBook);
    }

    private boolean linearSearch(String[] phoneBook) {
        for (int i = 0; i < phoneBook.length - 1; i++) {
            var shortPhone = "";
            var longPhone = "";
            if (phoneBook[i].length() > phoneBook[i + 1].length()) {
                shortPhone = phoneBook[i + 1];
                longPhone = phoneBook[i];
            } else {
                shortPhone = phoneBook[i];
                longPhone = phoneBook[i + 1];
            }

            if (longPhone.startsWith(shortPhone)) {
                return false;
            }
        }

        return true;
    }

}
