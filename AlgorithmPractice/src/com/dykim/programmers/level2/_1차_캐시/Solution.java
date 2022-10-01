package com.dykim.programmers.level2._1차_캐시;

import java.util.LinkedList;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        var solution = new Solution();
        var cities = new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
        System.out.println(solution.solution(3, cities));

        cities = new String[]{"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"};
        System.out.println(solution.solution(3, cities));

        cities = new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"};
        System.out.println(solution.solution(2, cities));

        cities = new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"};
        System.out.println(solution.solution(5, cities));

        cities = new String[]{"Jeju", "Pangyo", "NewYork", "newyork"};
        System.out.println(solution.solution(2, cities));

        cities = new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
        System.out.println(solution.solution(0, cities));


    }

    private final List<String> cacheMemory = new LinkedList<>();

    public int solution(int cacheSize, String[] cities) {
        int answer = 0;

        for (String city : cities) {
            var lowerCase = city.toLowerCase();
            answer += searchRestaurant(cacheSize, lowerCase);
        }

        return answer;
    }

    private int searchRestaurant(int cacheSize, String city) {
        if (cacheSize != 0 && isCacheHit(city)) {
            return 1;
        }

        if (cacheSize != 0) {
            addCacheData(cacheSize, city);
        }

        return 5;
    }

    private boolean isCacheHit(String city) {
        for (int i = 0; i < cacheMemory.size(); i++) {
            var cacheCity = cacheMemory.get(i);
            if (cacheCity.equals(city)) {
                cacheMemory.remove(i);
                cacheMemory.add(0, city);

                return true;
            }
        }

        return false;
    }

    private void addCacheData(int cacheSize, String city) {
        if (cacheMemory.size() < cacheSize) {
            cacheMemory.add(0, city);
            return;
        }

        cacheMemory.remove(cacheMemory.size() - 1);
        cacheMemory.add(0, city);
    }

}
