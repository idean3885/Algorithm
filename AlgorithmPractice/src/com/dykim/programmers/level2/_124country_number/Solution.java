package com.dykim.programmers.level2._124country_number;

/**
 * 124 나라의 숫자
 *
 * 문제 설명
 * 124 나라가 있습니다. 124 나라에서는 10진법이 아닌 다음과 같은 자신들만의 규칙으로 수를 표현합니다.
 *
 * 124 나라에는 자연수만 존재합니다.
 * 124 나라에는 모든 수를 표현할 때 1, 2, 4만 사용합니다.
 * 예를 들어서 124 나라에서 사용하는 숫자는 다음과 같이 변환됩니다.
 *
 * 10진법	124 나라	    10진법	124 나라
 * 1	    1	        6	    14
 * 2	    2	        7	    21
 * 3	    4	        8	    22
 * 4	    11	        9	    24
 * 5	    12	        10	    41
 * 자연수 n이 매개변수로 주어질 때, n을 124 나라에서 사용하는 숫자로 바꾼 값을 return 하도록 solution 함수를 완성해 주세요.
 *
 * 제한사항
 * n은 500,000,000이하의 자연수 입니다.
 * 입출력 예
 * n	result
 * 1	1
 * 2	2
 * 3	4
 * 4	11
 *
 * **************************************************************************************
 * 1. 풀이 과정
 *  1) 3진법 정렬 후 3을 4로 치환 -> 실패
 *  2) n이 3의 배수인 경우 몫-1 / 나머지=4 -> 성공
 *
 * 2. 풀이 결과
 *  소요시간: 50분 (15:04 ~ 15:54)
 *  결과: 성공
 *
 * 3. 문제 해결
 *  1. 3진법으로 계산
 *  2. 0인 부분은 상위 차수에서 값을 내려준다.
 *     예) 101 -> 31
 *  3. 3 -> 4 치환
 *
 * 4. 개선할 점
 *  단순히 3진법으로 계산하는 줄 알고 틀린 뒤 시간이 많이 소요됬었다.
 *  손코딩으로 놓친 부분이 있는지 규칙을 잘 따져보고 풀어야겠다.
 *
 * @term 2021.07.23 ~ 2021.07.23
 */
class Solution {
    public String solution(int n) {
        String answer;

        answer = getNotation3(n);
        return answer;
    }

    private String getNotation3(int n) {
        StringBuilder sb = new StringBuilder();
        int[] countryNum = {4, 1, 2};

        while(n>0) {
            int remainder = countryNum[n%3];
            sb.insert(0, remainder);
            n = n%3!=0? n/3 : n/3 -1;
        }

        return sb.toString();
    }
}