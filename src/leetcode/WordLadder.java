package leetcode;

import java.util.*;

public class WordLadder {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        HashMap<String, List<String>> graph = new HashMap<>();
        HashMap<String, Integer> distances = new HashMap<>();
        HashSet<String> dict = new HashSet<String>(wordList);
        List<List<String>> res = new ArrayList<>();
        List<String> solution = new ArrayList<String>();
        buildGraph(beginWord, endWord, graph, distances, dict);
        dfs(beginWord, endWord, graph, distances, res, solution);
        return res;
    }

    private void buildGraph(String beginWord, String endWord, HashMap<String, List<String>> graph, HashMap<String, Integer> distances, HashSet<String> dict){
        graph.put(beginWord,new ArrayList<>());
        for (String word : dict){
            graph.put(word, new ArrayList<String>());
        }
        Queue<String> q = new ArrayDeque<>();
        boolean end = false;
        distances.put(beginWord, 0);
        q.add(beginWord);

        while(!q.isEmpty() && !end){
            int size = q.size();
            for(int i = 0 ; i < size ; i ++){
                String word = q.poll();
                int curDistance = distances.get(word);
                List<String> neighbours = getNeighbours(word, dict);

                for (String neighbour : neighbours){
                    graph.get(word).add(neighbour);
                    if(!distances.containsKey(neighbour)){
                        distances.put(neighbour, curDistance + 1);
                        if(neighbour.equals(endWord)){
                            end = true;
                        } else{
                            q.offer(neighbour);
                        }
                    }
                }
            }
        }
    }

    private List<String> getNeighbours(String word, HashSet<String> dict){
        List<String> neighbours = new ArrayList<String>();
        for (int i = 0 ; i < word.length() ; i ++ ){
            char[] chars = word.toCharArray();
            char old = word.charAt(i);
            for (char c = 'a' ; c < 'z' ; c ++){
                if (c == old)
                    continue;
                chars[i] = c;
                String newWord = new String(chars);
                if (dict.contains(newWord)){
                    neighbours.add(newWord);
                }
            }
        }
        return neighbours;
    }

    private void dfs(String cur, String endWord, HashMap<String, List<String>> graph, HashMap<String, Integer> distances, List<List<String>> res, List<String> solution){
        solution.add(cur);
        if (cur.equals(endWord)){
            res.add(new ArrayList<>(solution));
        } else{
            for(String neighbour : graph.get(cur)){
                if (distances.get(cur) + 1 == distances.get(neighbour)){
                    dfs(neighbour, endWord, graph, distances, res, solution);
                }
            }
        }
        solution.remove(solution.size() - 1);
    }

    public static void main(String[] args) {
        WordLadder wordLadder = new WordLadder();
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");
        wordLadder.findLadders(beginWord, endWord, wordList);

    }

}
