package com.dykim.leetcode._860;

import java.util.*;

public class Solution {

    private static int[] coins = new int[3];

    private static final int FIVE = 0;
    private static final int TEN = 1;
    private static final int TWENTY = 2;

    public static void main(String[] args) {
        Integer[] bills = new Integer[5];
        List.of(5,5,5,10,20).toArray(bills);

        lemonadeChange(bills);
    }

    private static boolean lemonadeChange(Integer[] bills) {
        for (int bill : bills) {
            if (bill == 5) {
                coins[FIVE]++;
            }
            else if (bill == 10) {
                if (coins[FIVE]>0) {
                    coins[FIVE]--;
                    coins[TEN]++;
                } else {
                    return false;
                }
            }
            else {
                if (coins[TEN] > 0 && coins[FIVE]>0) {
                    coins[TEN]--;
                    coins[FIVE]--;
                    coins[TWENTY]++;
                } else if (coins[FIVE]>=3) {
                    coins[FIVE]-=3;
                    coins[TWENTY]++;
                } else {
                    return false;
                }
            }
        }

        return true;
    }

}
