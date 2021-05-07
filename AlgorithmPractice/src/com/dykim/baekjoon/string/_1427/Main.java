package com.dykim.baekjoon.string._1427;

import java.io.*;

/**
 * 문제
 * 배열을 정렬하는 것은 쉽다. 수가 주어지면, 그 수의 각 자리수를 내림차순으로 정렬해보자.
 *
 * 입력
 * 첫째 줄에 정렬하고자하는 수 N이 주어진다. N은 1,000,000,000보다 작거나 같은 자연수이다.
 *
 * 출력
 * 첫째 줄에 자리수를 내림차순으로 정렬한 수를 출력한다.
 */

/**
 * 1. 풀이 과정
 *  1) 문자열을 숫자 배열로 변경 후, 버블 정렬 -> 실패
 *  2) 내림차순으로 수정 -> 실패
 *  3) Arrays.toString 대신 직접 문자열로 변환 후 출력 -> 성공
 *
 * 2. 풀이 결과
 *  소요시간: 28분 (14:34 ~ 14:52)
 *  결과: 성공
 *
 * 3. 문제 해결
 *  문자열을 배열로 바꾸고 이를 오름차순 정렬 뒤 출력한다.
 *
 * 4. 개선할 점
 *  배열 출력 시, Arrays.toString 을 사용할 경우 배열 형태로 출력되기 때문에 조심해야 한다.
 *
 * @term 2021.05.07
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String n = br.readLine();
        int[] arr = getArr(n);
        bubbleSort(arr);
        bufString(arr, bw);

        bw.close();
    }

    private static int[] getArr(String n) {
        int[] arr = new int[n.length()];

        for (int i=0; i<n.length(); i++) {
            arr[i] = n.charAt(i) - '0';
        }

        return arr;
    }

    private static void bufString(int[] arr, BufferedWriter bw) throws IOException{
        for(int i: arr) {
            bw.write(Integer.toString(i));
        }
        bw.newLine();
    }

    private static void bubbleSort(int[] arr) {
        for (int i=0; i< arr.length; i++) {
            for(int j=0; j< arr.length -1 - i; j++) {
                if (arr[j]< arr[j+1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            }
        }
    }
}
