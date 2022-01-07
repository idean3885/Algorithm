package com.dykim.practice.calc;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 진행 순서
 *  1. 수식 입력(띄어쓰기 구분자로 문자열 한줄 입력)
 *  2. 숫자 배열, 연산자 배열로 구분
 *  3. 우선순위 연산자 기준으로 1차 계산
 *    예)
 *      초기식 : 1 * 2 + 3 / 4 - 1
 *      3번 계산 이후 : 2 + 0.75 - 1
 *  4. 더하기 빼기 연산자 기준 2차 계산
 *  5. 계산된 값 출력
 */
public class EngineeringCalc {

    private static Double[] numbers;
    private static String[] opers;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        var parsedExp = sc.nextLine().split(" ");
        numbers = new Double[parsedExp.length/2 + 1];
        opers = new String[parsedExp.length/2];

        for (var i = 0; i<parsedExp.length; i+=2) {
            numbers[i/2] = Double.parseDouble(parsedExp[i]);
        }

        for (var i=1; i< parsedExp.length; i+=2) {
            opers[i/2] = parsedExp[i];
        }

        var calcNum = calc();
        printCalc(calcNum);
    }

    private static void calcMulDiv() {
        var calcNumbers = new ArrayList<Double>();
        var calcOpers = new ArrayList<String>();

        var tmpCalc = 1.0;
        var isTmp = false;

        for (var i = 0; i < opers.length; i++) {
            var oper = opers[i];
            if ("*".equals(oper)) {
                if (isTmp) {
                    tmpCalc *= numbers[i+1];
                } else {
                    tmpCalc = numbers[i] * numbers[i+1];
                    isTmp = true;
                }
            }
            else if ("/".equals(oper)) {
                if (isTmp) {
                    tmpCalc /= numbers[i+1];
                } else {
                    tmpCalc = numbers[i] / numbers[i+1];
                    isTmp = true;
                }
            }
            else {
                calcNumbers.add(isTmp ? tmpCalc : numbers[i]);
                calcOpers.add(oper);
                tmpCalc = 1;
                isTmp = false;
            }
        }

        // 마지막 값 넣기
        calcNumbers.add(isTmp? tmpCalc : numbers[numbers.length-1]);

        numbers = calcNumbers.toArray(Double[]::new);
        opers = calcOpers.toArray(String[]::new);
    }

    private static double calc() {
        // 우선순위 연산자 계산(곱셈, 나눗셈)
        calcMulDiv();

        // 더하기 빼기 계산
        var calcNum = numbers[0];
        for (var i = 0; i<opers.length; i++) {
            var oper = opers[i];
            if ("+".equals(oper)) calcNum += numbers[i+1];
            if ("-".equals(oper)) calcNum -= numbers[i+1];
        }

        return calcNum;
    }

    private static void printCalc(double calcNum) {
        System.out.println(calcNum);
    }

}
