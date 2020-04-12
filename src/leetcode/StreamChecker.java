package leetcode;

import java.util.HashMap;
import java.util.Map;

class StreamChecker {

    class Node{
        Map<Character, Node> children = new HashMap<>();
        boolean isWord = false;
        public Node(){

        }
    }

    Node root = new Node();
    StringBuilder sb = new StringBuilder();

    public void insert(String s){
        Node cur = root;
        for(int i = s.length() - 1; i >= 0 ; i --){
            char c = s.charAt(i);
            if(!cur.children.containsKey(c)){
                cur.children.put(c, new Node());
            }
            cur = cur.children.get(c);
        }
        cur.isWord = true;
    }

    public StreamChecker(String[] words) {
        for(String word : words){
            insert(word);
        }
        System.out.println();
    }

    public boolean query(char letter) {
        sb.append(letter);
        Node cur = root;
        for(int i = sb.length() - 1 ; i >= 0 ; i --){
            char c = sb.charAt(i);
            cur = cur.children.get(c);
            if(cur == null) break;
            if(cur != null && cur.isWord) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String[] word = {"cd","f","kl"};
        StreamChecker checker = new StreamChecker(word);
        System.out.println(checker.query('a'));
        System.out.println(checker.query('b'));
        System.out.println(checker.query('c'));
        System.out.println(checker.query('d'));
        System.out.println(checker.query('e'));
        System.out.println(checker.query('f'));
        System.out.println(checker.query('g'));
    }
}