package com.dykim.hackerrank._2_Odd_Numbers;

import java.util.ArrayList;
import java.util.List;

public class Result {

    /*
     * Complete the 'oddNumbers' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER l
     *  2. INTEGER r
     */

    public static List<Integer> oddNumbers(int l, int r) {
        var odds = new ArrayList<Integer>();

        var startOdd = l %2 == 0 ? l+1 : l;

        for (int i = startOdd; i <= r; i+=2) {
            odds.add(i);
        }

        return odds;
    }

}
