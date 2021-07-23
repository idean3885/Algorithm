package com.dykim.baekjoon.backtracking._14888;

/**
 * 연산자 끼워넣기
 * <p>
 * 문제
 * N개의 수로 이루어진 수열 A1, A2, ..., AN이 주어진다. 또, 수와 수 사이에 끼워넣을 수 있는 N-1개의 연산자가 주어진다.
 * 연산자는 덧셈(+), 뺄셈(-), 곱셈(×), 나눗셈(÷)으로만 이루어져 있다.
 * <p>
 * 우리는 수와 수 사이에 연산자를 하나씩 넣어서, 수식을 하나 만들 수 있다. 이때, 주어진 수의 순서를 바꾸면 안 된다.
 * 예를 들어, 6개의 수로 이루어진 수열이 1, 2, 3, 4, 5, 6이고,
 * 주어진 연산자가 덧셈(+) 2개, 뺄셈(-) 1개, 곱셈(×) 1개, 나눗셈(÷) 1개인 경우에는 총 60가지의 식을 만들 수 있다.
 * 예를 들어, 아래와 같은 식을 만들 수 있다.
 * <p>
 * 1+2+3-4×5÷6
 * 1÷2+3+4-5×6
 * 1+2÷3×4-5+6
 * 1÷2×3-4+5+6
 * 식의 계산은 연산자 우선 순위를 무시하고 앞에서부터 진행해야 한다. 또, 나눗셈은 정수 나눗셈으로 몫만 취한다.
 * 음수를 양수로 나눌 때는 C++14의 기준을 따른다. 즉, 양수로 바꾼 뒤 몫을 취하고, 그 몫을 음수로 바꾼 것과 같다.
 * 이에 따라서, 위의 식 4개의 결과를 계산해보면 아래와 같다.
 * <p>
 * 1+2+3-4×5÷6 = 1
 * 1÷2+3+4-5×6 = 12
 * 1+2÷3×4-5+6 = 5
 * 1÷2×3-4+5+6 = 7
 * N개의 수와 N-1개의 연산자가 주어졌을 때, 만들 수 있는 식의 결과가 최대인 것과 최소인 것을 구하는 프로그램을 작성하시오.
 * <p>
 * 입력
 * 첫째 줄에 수의 개수 N(2 ≤ N ≤ 11)가 주어진다. 둘째 줄에는 A1, A2, ..., AN이 주어진다. (1 ≤ Ai ≤ 100)
 * 셋째 줄에는 합이 N-1인 4개의 정수가 주어지는데, 차례대로 덧셈(+)의 개수, 뺄셈(-)의 개수, 곱셈(×)의 개수, 나눗셈(÷)의 개수이다.
 * <p>
 * 출력
 * 첫째 줄에 만들 수 있는 식의 결과의 최댓값을, 둘째 줄에는 최솟값을 출력한다.
 * 연산자를 어떻게 끼워넣어도 항상 -10억보다 크거나 같고, 10억보다 작거나 같은 결과가 나오는 입력만 주어진다.
 * 또한, 앞에서부터 계산했을 때, 중간에 계산되는 식의 결과도 항상 -10억보다 크거나 같고, 10억보다 작거나 같다.
 */

/**
 * 1. 풀이 과정
 *  1) dfs 방식, 사칙연산 각각 수행 -> 실패
 *  2) dfs 완료 후, min/max 계산하도록 수정 -> 실패
 *  3) 맨 마지막까지 탐색을 완료한 경우만 min/max 계산하도록 수정 -> 실패
 *  4) 자료형 int가 10억을 담지 못한다고 생각하여 int 으로 변경 -> 실패
 *
 * 2. 풀이 결과
 *  소요시간: 47분 (13:38 ~ 14:25)
 *  결과: 실패
 *
 * 3. 문제 해결
 *  1부터 목표 위치까지 재귀함수 호출 방식으로 문제 해결
 *
 * 4. 개선할 점
 *  max, min 값에 적당히 큰 값과 작은 값을 넣어서 틀렸다...
 *  다음부터는 문제에 주어진 자료형의 최대값, 최소값을 확실하게 넣을 수 있도록 하자.
 *  int -> Integer.MAX_VALUE / Integer.MIN_VALUE
 *
 * @term 2021.07.23 ~ 2021.07.23
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    private static final int PLUS = 0;
    private static final int MINUS = 1;
    private static final int MULTIPLE = 2;
    private static final int DIVIDE = 3;

    private static int min = Integer.MAX_VALUE;
    private static int max = Integer.MIN_VALUE;

    private static int N;
    private static int[] A;
    private static int[] operator;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        A = new int[N];

        String[] arr = br.readLine().split(" ");

        for (int i = 0; i < N; i++) {

            A[i] = Integer.parseInt(arr[i]);
        }

        operator = new int[4];
        String[] opArr = br.readLine().split(" ");
        for (int i = 0; i < 4; i++) {
            operator[i] = Integer.parseInt(opArr[i]);
        }

        dfs(1, A[0]);

        System.out.println(max);
        System.out.println(min);
    }

    private static void dfs(int pos, int sum) {
        if (pos == N) {
            max = Math.max(sum, max);
            min = Math.min(sum, min);

            return;
        }

        for (int op=0; op<4; op++) {
            if (operator[op]>0) {
                operator[op]--;

                int nextPos = pos+1;
                switch (op) {
                    case PLUS : dfs(nextPos, sum + A[pos]); break;
                    case MINUS : dfs(nextPos, sum - A[pos]); break;
                    case MULTIPLE : dfs(nextPos, sum * A[pos]); break;
                    case DIVIDE : dfs(nextPos, sum / A[pos]); break;
                }

                operator[op]++;
            }
        }
    }
}
