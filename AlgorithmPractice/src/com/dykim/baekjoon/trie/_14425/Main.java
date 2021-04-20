package com.dykim.baekjoon.trie._14425;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


/**
 * 문제
 *  총 N개의 문자열로 이루어진 집합 S가 주어진다.
 *  입력으로 주어지는 M개의 문자열 중에서 집합 S에 포함되어 있는 것이 총 몇 개인지 구하는 프로그램을 작성하시오.
 *
 * 입력
 *  첫째 줄에 문자열의 개수 N과 M (1 ≤ N ≤ 10,000, 1 ≤ M ≤ 10,000)이 주어진다. 
 *  다음 N개의 줄에는 집합 S에 포함되어 있는 문자열들이 주어진다.
 *  다음 M개의 줄에는 검사해야 하는 문자열들이 주어진다.
 *  입력으로 주어지는 문자열은 알파벳 소문자로만 이루어져 있으며, 길이는 500을 넘지 않는다. 
 *  집합 S에 같은 문자열이 여러 번 주어지는 경우는 없다.
 *  
 * 출력
 *  첫째 줄에 M개의 문자열 중에 총 몇 개가 집합 S에 포함되어 있는지 출력한다.
 */

/**
 * 1. 풀이 과정
 *  1) 기준 문자열을 트리 구조로 정렬, 탐색 문자열에 대해 존재하는 경우 확인 -> 시간 초과
 *  2) 같은 문자열을 탐색하는 경우 제외 -> 실패
 *  3) 탐색 문자열과 기준 문자열이 완전히 일치해야하도록 수정 -> 실패
 * 
 * 2. 풀이 결과
 *  소요시간: 1시간 16분 (11:00 ~ 12:16) 
 *  결과: 실패
 *  
 * 3. 문제 해결
 *   
 * 4. 개선할 점
 *  
 * @term 2021.04.20 ~ 2021.04.21
 */
public class Main {
    static class Node {
        private Node parents;
        private Map<Character, Node> children;
        
        private Node() {
        }
        
        public Node(Node parents, Map<Character, Node> children) {
            this.parents = parents;
            this.children = children;
        }
        
        public Node getParents() {
            return parents;
        }
        
        public Map<Character, Node> getChildren() {
            return children;
        }
        
        public void resetChildren() {
            children = null;
        }
    }
    
    private static Node root = new Node(null, new HashMap<>());
    private static Node currentNode;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] nm = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        
        for(int i=0; i<n; i++) {
            addNode(br.readLine());
        }

        // 검색할 문자열 중복 제거
        Map<String, Boolean> deDup = new HashMap<>();
        for(int i=0; i<m; i++) {
            deDup.put(br.readLine(), true);
        }
        
        // 검색
        int result = 0;
        for (String key: deDup.keySet()) {
            result += findStr(key);
        }
        
        System.out.println(result);
    }
    
    // 문자열 트리에 추가
    private static void addNode(String str) {
        StringBuilder sb = new StringBuilder(str);
        
        // root 노드부터 시작
        currentNode = root;
        sb.chars().forEach(ascii -> {
            Character ch = (char) ascii;
            
            if (!currentNode.getChildren().containsKey(ch)) {   // 현재 레벨에 존재하지 않는 문자인 경우
                currentNode.getChildren().put(ch, new Node(currentNode, new HashMap<>()));
            }
            
            // 다음 트리로 이동
            currentNode = currentNode.getChildren().get(ch);
        });
    }
    
    // 문자열 탐색
    private static int findStr(String str) {
        StringBuilder sb = new StringBuilder(str);
        
        // root 노드부터 시작
        currentNode = root;
        for(int i=0; i<sb.length(); i++) {
            Character ch = sb.charAt(i);
            
            if (!currentNode.getChildren().containsKey(ch)) {
                return 0;
            }
            
            currentNode = currentNode.getChildren().get(ch);
        }
        
        return currentNode.getChildren().isEmpty()? 1: 0;
    }
}
