package leetcode;

import java.util.*;


class Pair{
    int time;
    String sentence;
    public Pair(int time, String sentence){
        this.time = time;
        this.sentence = sentence;
    }
}

public class AutocompleteSystem {

    class TrieNode{
        HashMap<Character, TrieNode> children;
        HashMap<String, Integer> count;
        boolean is_word;
        public TrieNode(){
            children = new HashMap<Character, TrieNode>();
            count = new HashMap<String,Integer>();
            is_word = false;
        }
    }

    TrieNode root;
    String prefix;

    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        prefix = "";
        for(int i = 0 ; i < sentences.length ; i ++){
            add(sentences[i], times[i]);
        }
    }

    private void add(String sentence, int time){
        TrieNode cur = root;
        for(Character c : sentence.toCharArray()){
            TrieNode next = cur.children.get(c);
            if(next == null){
                next = new TrieNode();
                cur.children.put(c, next);
            }
            cur = next;
            cur.count.put(sentence, cur.count.getOrDefault(sentence, 0) + time);
        }
        cur.is_word = true;
    }

    public List<String> input(char c) {
        if(c == '#'){
            add(prefix, 1);
            prefix = "";
            return new ArrayList<String>();
        }
        prefix = prefix + c;
        TrieNode cur = root;
        for(Character cc : prefix.toCharArray()){
            TrieNode next = cur.children.get(cc);
            if(next == null){
                return new ArrayList<String>();
            }
            cur = next;
        }
        PriorityQueue<Pair> q = new PriorityQueue<Pair>((p1, p2) -> {
            return p1.time == p2.time ? p1.sentence.compareTo(p2.sentence) : p2.time - p1.time;
        });

        for(String sentence : cur.count.keySet()){
            q.offer(new Pair(cur.count.get(sentence), sentence));
        }
        List<String> res = new ArrayList<String>();
        for(int i = 0 ; i < 3 ; i ++){
            res.add(q.poll().sentence);
        }
        return res;
    }

    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if(beginWord.length() != endWord.length() || !wordList.contains(endWord))
            return new ArrayList<>();
        List<List<String>> res = new ArrayList<>();

        Stack<String> stack = new Stack<>();
        stack.add(beginWord);
        ArrayList<String> temp = new ArrayList<>();

        while(!stack.isEmpty()){
            String word = stack.pop();
            temp.add(word);
            if (word.equals(endWord)){
                List<String> ans = new ArrayList<>(temp);
                res.add(ans);

            } else{

                for (int i = 0 ; i < word.length() ; i ++){
                    char[] chars = word.toCharArray();
                    for (char c = 'a' ; c <= 'z' ; c ++){
                        chars[i] = c;
                        String str = new String(chars);
                        if (wordList.contains(str)){
                            stack.add(str);
                        }
                    }
                }
            }
            temp.remove(word);
        }

        return res;
    }

    public static void main(String[] args) {
        String begin = "hit";
        String end = "cog";
        List<String>  list= Arrays.asList("hot","dot","dog","lot","log","cog") ;

        findLadders(begin,end,list);
    }

}



