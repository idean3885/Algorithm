package com.dykim.hackerrank._1_FindTheNumber;

import java.util.HashSet;
import java.util.List;

public class Result {

    /*
     * Complete the 'findNumber' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY arr
     *  2. INTEGER k
     */

    public static String findNumber(List<Integer> arr, int k) {
        var set = new HashSet<>(arr);

        return set.contains(k) ? "YES" : "NO";
    }

}
