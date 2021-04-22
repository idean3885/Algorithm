package com.dykim.baekjoon.trie._5052;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 문제
 *  전화번호 목록이 주어진다. 이때, 이 목록이 일관성이 있는지 없는지를 구하는 프로그램을 작성하시오.
 *  전화번호 목록이 일관성을 유지하려면, 한 번호가 다른 번호의 접두어인 경우가 없어야 한다.
 *  예를 들어, 전화번호 목록이 아래와 같은 경우를 생각해보자
 *  
 *  긴급전화: 911
 *  상근: 97 625 999
 *  선영: 91 12 54 26
 *  이 경우에 선영이에게 전화를 걸 수 있는 방법이 없다. 
 *  전화기를 들고 선영이 번호의 처음 세 자리를 누르는 순간 바로 긴급전화가 걸리기 때문이다. 
 *  따라서, 이 목록은 일관성이 없는 목록이다. 
 *  
 * 입력
 *  첫째 줄에 테스트 케이스의 개수 t가 주어진다. (1 ≤ t ≤ 50) 
 *  각 테스트 케이스의 첫째 줄에는 전화번호의 수 n이 주어진다. (1 ≤ n ≤ 10000) 
 *  다음 n개의 줄에는 목록에 포함되어 있는 전화번호가 하나씩 주어진다. 
 *  전화번호의 길이는 길어야 10자리이며, 목록에 있는 두 전화번호가 같은 경우는 없다.
 *  
 * 출력
 *  각 테스트 케이스에 대해서, 일관성 있는 목록인 경우에는 YES, 아닌 경우에는 NO를 출력한다.
 */

/**
 * 1. 풀이 과정
 *  1) 트라이 알고리즘 사용 -> 트리구조로 저장하며 탐색 문자열을 마지막까지 찾았을 때, 트리가 더 이어지는 경우 false 처리함 -> ArrayIndexOutOfBounds 에러 발생, 실패
 *  2) 마지막까지 탐색한 경우를 저장하는 boolean 변수 Node에 추가 -> ArrayIndexOutOfBounds 에러 발생, 실패
 *  3) 0~9까지 저장할 수 있도록 자식 노드 갯수 10개로 설정 -> 실패
 *  4) 탐색 완료한 경우, 마지막인지 자식 노드가 더 있는지 구체적으로 확인하도록 세분화 -> 실패
 * 
 * 2. 풀이 결과
 *  소요시간: 56분 (20:21 ~ 21:17) 
 *  결과: 실패
 *  
 * 3. 문제 해결
 *  각 테스트 케이스 별로 문자열을 다 받은 뒤, 탐색하며 포함 여부를 확인한다.
 *  테스트 케이스가 끝나면  root 노드를 초기화하여 이전 트리를 지워준다.
 *   
 * 4. 개선할 점
 *  테스트 케이스가 독립적이라는 사실을 간과하여 실패함. -> 케이스마다 root 노드를 초기화하여 트리구조를 지워줬어야 했다...
 *  다음부터는 이런 사소한 실수를 하지 않도록 조심해야겠다.
 *  
 * @term 2021.04.21
 */
public class Main {
    private static class Node {
        private Node[] children;
        private boolean last;
        
        public Node[] getChildren() {
            return children;
        }
        
        public void createChildren() {
            children = new Node[10];    // 0~9
        }
        
        public boolean isLast() {
            return last;
        }
        
        public void setLast() {
            this.last = true;
        }
    }
    
    private static Node root;
    
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        
        for (int i=0; i<t; i++) {
            int n = Integer.parseInt(br.readLine());
            
            String[] arr = new String[n];
            for (int j=0; j<n; j++) {
                arr[j] = br.readLine();
            }
            
            root = new Node();
            boolean isConsist = false;
            for (String str: arr) {
                isConsist = trie(str);
                if (!isConsist) {
                    break;
                }
            }
            bw.write(isConsist? "YES" : "NO");
            bw.newLine();
        }
        
        bw.close();
    }
    
    private static boolean trie(String str) {
        Node currentNode = root;
        
        for (int i=0; i< str.length(); i++) {
            // 이미 탐색 완료된 문자열이 있다면 종료
            if (currentNode.isLast()) {
                return false;
            }
            
            if (currentNode.getChildren()==null) {
                currentNode.createChildren();
            }
            
            int indx = str.charAt(i) - 48;
            
            // 탐색하지 않은 경우
            if (currentNode.getChildren()[indx]==null) {
                currentNode.getChildren()[indx] = new Node();
            }
            
            currentNode = currentNode.getChildren()[indx];
        }
        
        // 탐색 완료 표시
        currentNode.setLast();
        
        return currentNode.getChildren()==null;
    }
}
