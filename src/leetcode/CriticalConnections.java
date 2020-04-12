package leetcode;

import java.util.*;

public class CriticalConnections {
    public static List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> res = new ArrayList<>();
        if(n == 0)
            return res;

        for(int i = 0 ; i < connections.size() ; i ++){
            HashMap<Integer,ArrayList<Integer>> graph = new HashMap<Integer, ArrayList<Integer>>();
            List<List<Integer>> copy = new ArrayList<>(connections);
            List<Integer> connect = copy.remove(i);
            buildGraph(copy, graph);
            Set<Integer> visitedSet = new HashSet<Integer>();
            if(dfs(graph, visitedSet) != n)
                res.add(connect);
        }

        return res;
    }

    private static void buildGraph(List<List<Integer>> copy, HashMap<Integer,ArrayList<Integer>> graph){
        for(List<Integer> pair : copy){
            Integer first = pair.get(0);
            Integer second = pair.get(1);
            if(!graph.containsKey(first)){
                List<Integer> neighbour = new ArrayList<Integer>();
                neighbour.add(second);
                graph.put(first, (ArrayList<Integer>) neighbour);
            }else{
                graph.get(first).add(second);
            }
            if(!graph.containsKey(second)){
                List<Integer> neighbour = new ArrayList<Integer>();
                neighbour.add(first);
                graph.put(second, (ArrayList<Integer>) neighbour);
            }else{
                graph.get(second).add(first);
            }
        }
    }

    private static Integer dfs(HashMap<Integer,ArrayList<Integer>> graph, Set<Integer> visitedSet){
        Integer root = 0;
        for(Integer key : graph.keySet()){
            root = key;
        }
        Queue<Integer> q = new LinkedList<>();
        q.add(root);
        visitedSet.add(root);
        while(!q.isEmpty()){
            Integer cur = q.poll();
            visitedSet.add(cur);
            for (Integer neighbour : graph.get(cur)){
                if (!visitedSet.contains(neighbour)){
                    q.add(neighbour);
                }
            }
        }
        return visitedSet.size();
    }

    public static void main(String[] args) {
        int n = 4;
        List<List<Integer>> connections = Arrays.asList(Arrays.asList(0,1),
                Arrays.asList(1,2),
                Arrays.asList(2,0),
                Arrays.asList(1,3));
        criticalConnections(n, connections);
    }
}
