package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class tarjan {

    int[] dfn;
    int[] low;
    int step = 1;
    List<List<Integer>> graph  = new ArrayList<>();
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {

        if(n == 0)
            return res;
        dfn = new int[n];
        low = new int[n];

        for(int i = 0 ; i < n ;  i ++){
            graph.add(new ArrayList<Integer>());
        }

        for(List<Integer> pair : connections){
            int first = pair.get(0);
            int second = pair.get(1);
            graph.get(first).add(second);
            graph.get(second).add(first);
        }

        for(int i = 0 ; i < n ; i ++){
            if(dfn[i] == 0){
                tarjan(i, -1);
            }
        }
        return res;
    }

    private void tarjan1(int cur, int parent){
        low[cur] = step;
        dfn[cur] = step;
        step ++;
        for(Integer neighbour : graph.get(cur)){
            if(dfn[neighbour] == 0){
                tarjan1(neighbour, cur);
                low[cur] = Math.min(low[cur], low[neighbour]);
                if(low[neighbour] > dfn[cur]){
                    ArrayList<Integer> temp = new ArrayList<Integer>();
                    if(cur > neighbour){
                        temp.add(neighbour);
                        temp.add(cur);
                    }else{
                        temp.add(cur);
                        temp.add(neighbour);
                    }
                    res.add(temp);
                }

            }else if(neighbour != parent){
                low[cur] = Math.min(low[cur], dfn[neighbour]);
            }
        }
    }

    public void tarjan(int u,int parent){
        low[u] = step;
        dfn[u] = step;
        step++;
        for (Integer v:graph.get(u)){
            if(dfn[v]==0){
                tarjan(v,u);
                low[u] = Math.min(low[u],low[v]);
                if(low[v]>dfn[u]){
                    ArrayList<Integer> temp = new ArrayList<>();
                    if(u<v){
                        temp.add(u);
                        temp.add(v);
                    }
                    else{
                        temp.add(v);
                        temp.add(u);
                    }
                    res.add(temp);
                }
            }
            else if(v!=parent){
                low[u] = Math.min(low[u],dfn[v]);
            }
        }
    }

    public static String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (log1, log2) -> {
            String[] split1 = log1.split(" ", 2);
            String[] split2 = log2.split(" ", 2);
            boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
            boolean isDigit2 = Character.isDigit(split2[1].charAt(0));
            if(!isDigit1 && !isDigit2){
                int tem = split1[1].compareTo(split2[1]);
                if(tem != 0)
                    return tem;
                return split1[0].compareTo(split2[0]);
            }

            return isDigit1 ? (isDigit2 ? 0 : 1) : -1;

        });

        return logs;
    }

    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<Integer,Integer>();
        for(int i = 0 ;i < nums.length ; i ++){
            if(!map.containsKey(target - nums[i])){
                map.put(nums[i], i);
            }else{
                int[] res = new int[2];
                res[0] = i;
                res[1] =  map.get(target - nums[i]);
                return res;
            }
        }
        return new int[2];
    }

    public static void main(String[] args) {
//        tarjan t = new tarjan();
//        List<List<Integer>> connections = Arrays.asList(Arrays.asList(0,1),
//                Arrays.asList(1,2),
//                Arrays.asList(2,0),
//                Arrays.asList(1,3));
//        t.criticalConnections(4,connections);
        String[] res = {"dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"};
//        reorderLogFiles(res);
//        int[] a = {2, 7, 11, 15};
//        StringBuilder sb = new StringBuilder();
//        res[1].toCharArray();
//        sb.append('c');
        char c = 'c';
        System.out.println(Character.isLetter('c'));
        //        twoSum(a,9);
    }
}