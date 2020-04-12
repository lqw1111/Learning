package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class UnionFindSet{
    private int[] parent;
    private int[] rank;

    UnionFindSet(int n){
        parent = new int[n + 1];
        rank = new int[n + 1];
        for(int i = 0 ; i <  parent.length ; i ++){
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public boolean Union(int x, int y){
        int px = Find(x);
        int py = Find(y);
        if (px == py) return false;

        if (rank[px] > rank[py]){
            parent[py] = px;
        }else if (rank[px] < rank[py]){
            parent[px] = py;
        }else{
            parent[py] = px;
            rank[px] ++;
        }
        return true;
    }

    public int Find(int x){
        while(x != parent[x]){
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

}

public class UnionFind {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        UnionFindSet unionFindSet = new UnionFindSet(n);

        for (int[] edge : edges){
            if(!unionFindSet.Union(edge[0],edge[1])){
                return edge;
            }
        }
        return new int[2];
    }

    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, List<List<String>> pairs) {
        if (words1.length != words2.length)
            return false;
        HashMap<String, Integer> index = new HashMap<>();

        UnionFindSet unionFindSet = new UnionFindSet(pairs.size() * 2);

        for (List<String> pair : pairs){
            int index1 = getIndex(pair.get(0), index);
            int index2 = getIndex(pair.get(1), index);
            unionFindSet.Union(index1, index2);
        }

        for(int i = 0 ; i < words1.length ; i ++){
            String word1 = words1[i];
            String word2 = words2[i];
            int i1 = getIndex(word1, index);
            int i2 = getIndex(word2, index);
            if(unionFindSet.Find(i1) != unionFindSet.Find(i2)) return false;
        }

        return true;
    }

    private int getIndex(String word, HashMap<String, Integer> map){
        if(map.containsKey(word))
            return map.get(word);
        else{
            map.put(word, map.size());
            return map.get(word);
        }
    }

    public int findCircleNum(int[][] M) {
        UnionFindSet unionFindSet = new UnionFindSet(M.length);
        for(int i = 0 ; i < M.length ; i ++){
            for(int j = i + 1 ; j < M[i].length ; j ++){
                if(M[i][j] == 1){
                    unionFindSet.Union(i,j);
                }
            }
        }
        Set<Integer> set = new HashSet<Integer>();
        for(int i = 0 ; i < M.length ; i++){
            set.add(unionFindSet.Find(i));
        }
        return set.size();
    }

    public static void main(String[] args) {
        HashMap a = new HashMap();
        a.keySet();
        for (int i = 0 ; i < 2 ; i ++){
            for(int j = 0 ; j < 2 ; j ++){
                System.out.println(4 * (i * 2 + j));
            }
        }
    }
}
