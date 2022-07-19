package com.dykim.programmers.level2.위장;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public static void main(String[] args) {
        var solution = new Solution();

        var case1 = new String[][]{
                {"yellow_hat", "headgear"},
                {"blue_sunglasses", "eyewear"},
                {"green_turban", "headgear"}
        };

        var result1 = solution.solution(case1);
        assert result1 == 5;
        System.out.println(result1);

        var case2 = new String[][] {
                {"crow_mask", "face"},
                {"blue_sunglasses", "face"},
                {"smoky_makeup", "face"}
        };

        var result2 = solution.solution(case2);
        assert result2 == 3;
        System.out.println(result2);
    }

    public int solution(String[][] clothes) {
        // 1. 입력된 옷 종류 별로 저장 -> 모든 옷은 중복되지 않는다. -> 중복 검사 x, 종류 별 갯수만 파악
        var clothMap = clothesToMap(clothes);

        // 2. 각 종류 별 0 ~ 1 개 선택 => 각 선택은 동시에 발생한다 => 곱하기
        var chooseCount = chooseClothes(clothMap);

        // 3. 모든 종류가 0인 경우 제외 => -1
        chooseCount--;

        return chooseCount;
    }

    private Map<String, Integer> clothesToMap(String[][] clothes) {
        var clothesMap = new HashMap<String, Integer>();

        for (var cloth : clothes) {
            var type = cloth[1];
            var name = cloth[0];

            if (clothesMap.containsKey(type)) {
                clothesMap.replace(type, clothesMap.get(type) + 1);
            } else {
                clothesMap.put(type, 1);
            }
        }

        return clothesMap;
    }

    private int chooseClothes(Map<String, Integer> clothesMap) {
        int chooseCount = 1;
        for (Map.Entry<String, Integer> cloth : clothesMap.entrySet()) {
            var value = cloth.getValue();

            chooseCount *= (value + 1); // 1개 선택 + 0개 선택 경우의 수
        }

        return chooseCount;
    }

}
