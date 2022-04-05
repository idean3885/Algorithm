package com.dykim.programmers.level1.push_keypad;

/**
 * 시간: 21:15 ~ 22:00(45분) / 성공
 * 마지막 거리 계산에서 거리 값이 큰 쪽을 넣어서 시간을 잡아먹었다.
 * 조건을 좀더 꼼꼼히 보도록 연습하자
 *
 * 문제 분석
 * 왼손 엄지 -> * 키패드에서 시작
 * 오른손 엄지 -> # 키패드에서 시작
 *
 * 4방향 이동 가능 -> 1칸 당 거리 1로 계산
 * 1, 4, 7 -> 왼손으로 터치
 * 3, 6, 9 -> 오른손으로 터치
 * 2, 5, 8, 0 -> 가까운 손으로 터치
 *            -> 같은 경우 오른손 잡이는 오른쪽, 왼손잡이는 왼쪽 터치
 *
 * 누를 번호 배열 numbers
 * 손잡이 문자열 hand "left" / "right"
 *
 * 왼손을 사용하는 경우 L, 오른손을 사용하는 경우 R 로 표기하여 누르는 순서 배열 리턴해주세요.
 *
 * 문제 풀이
 * 1. 왼손 움직임 확인
 *   -> 1, 4, 7 인 경우 바로 이동
 *   -> 3, 6, 9 인 경우 패스
 *   -> 가운데인 경우, 현재위치로부터 거리 측정
 *
 * 2. 오른손 움직임 확인
 *   -> 3, 6, 9 인 경우 바로 이동
 *   -> 1, 4, 7 인 경우 패스
 *   -> 가운데인 경우, 현재 위치로부터 거리 측정
 *
 * 3. 가운데인 경우 로직
 *   -> 가까운 쪽 손가락 이동
 *   -> 같은 경우, 손잡이 손가락 이동
 *
 * 4. 1 ~ 3 반복 후 터치한 손가락 배열 전달
 *
 */
public class Solution {

    private int left = 10; // * 은 10 으로 대체
    private int right = 12; // #d 은 12 로 대체

    public String solution(int[] numbers, String hand) {
        var sb = new StringBuffer();

        // 배열 크기만큼 반복
        for (int number : numbers) {
            // 0. 입력된 값 치환
            if (number == 0) {
                number = 11; // 거리 계산을 위해 치환
            }

            // 1. 왼손 움직임 확인
            var isMove = isMoveLeft(number);

            // 움직인 경우 왼손 위치 저장
            if (isMove) {
                sb.append("L");
                continue;
            }

            // 2. 오른손 움직임 확인
            isMove = isMoveRight(number);

            // 움직인 경우 오른손 저장
            if (isMove) {
                sb.append("R");
                continue;
            }

            // 3. 가운데인 경우 확인
            var moveHand = moveCenter(number, hand);
            sb.append(moveHand);
        }

        return sb.toString();
    }

    private boolean isMoveLeft(int number) {
        switch (number) {
            case 1: case 4: case 7:
                left = number;
                return true;
            default:
                return false;
        }
    }

    private boolean isMoveRight(int number) {
        switch (number) {
            case 3: case 6: case 9:
                right = number;
                return true;
            default:
                return false;
        }
    }

    private char moveCenter(int number, String hand) {
        // 왼손 거리 측정
        var leftDist = Math.abs(number - left) / 3 + Math.abs(number - left) % 3;

        // 오른손 거리 측정
        var rightDist = Math.abs(number - right) / 3 + Math.abs(number - right) % 3;

        // 가까운 쪽 확인
        var result = Character.toUpperCase(hand.charAt(0)); // 기본값은 손잡이 손가락
        if (leftDist > rightDist) {
            result = 'R';
            right = number;
        }
        else if (leftDist < rightDist) {
            result = 'L';
            left = number;
        }
        else if ('L' == result) {
            left = number;
        }
        else {
            right = number;
        }

        return result;
    }

}
