package leetcode;

import java.util.HashMap;

class TrieNode{
    HashMap<Character,TrieNode> children = new HashMap<Character,TrieNode>();
    Character c;
    boolean is_word;
    public TrieNode(){}
    public TrieNode(char c){
        this.c = c;
    }
}
public class WordDictionary {

    TrieNode root;

    /** Initialize your data structure here. */
    public WordDictionary() {
        this.root = new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        HashMap<Character,TrieNode> children;
        TrieNode cur = root;
        for(int i = 0 ; i < word.length() ; i ++){
            char c = word.charAt(i);
            children = cur.children;
            if(children.containsKey(c)){
                cur = children.get(c);
            }else{
                cur = new TrieNode(c);
                children.put(c, cur);
            }
        }
        cur.is_word = true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        if(word.charAt(0) == '.'){
            for (TrieNode node : root.children.values()){
                if(find(word, 0, node))
                    return true;
            }
            return false;
        }else{
            if(root.children.containsKey(word.charAt(0))){
                return find(word, 0, root.children.get(word.charAt(0)));
            }else{
                return false;
            }
        }
    }

    private boolean find(String word, int index, TrieNode cur){

        HashMap<Character,TrieNode> children = cur.children;
        char c = word.charAt(index);

        if(c == '.'){
            if(children.size() != 0){
                for(TrieNode node: children.values()){
                    if(find(word, index + 1, node))
                        return true;
                }
            }else
                return false;

        }

        if(children.containsKey(c)){
            return find(word, index + 1, children.get(c));
        }
        return false;
    }

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("a");
//        wordDictionary.addWord("abb");
//        wordDictionary.addWord("dad");
//        wordDictionary.addWord("mad");
//        System.out.println(wordDictionary.search("."));
//        System.out.println(wordDictionary.search("a"));
//        System.out.println(wordDictionary.search("aa"));
//        System.out.println(wordDictionary.search("a"));
//        System.out.println(wordDictionary.search(".a"));
        System.out.println(wordDictionary.search("."));
    }
}