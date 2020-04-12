package leetcode;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;

public class GraphProblems {

    class PairInt {
        int a, b;

        public PairInt(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public String toString() {
            return "{a:" + a + " b:" + b + "}";
        }
    }

    //5, 5, {[1,2],[1,3],[3,4],[1,4],[4,5]}
    //output: {[1,2],[4,5]}

    @Test
    public void test1() {
        List<PairInt> test = Arrays.asList(new PairInt(1, 2),
                new PairInt(1, 3), new PairInt(1, 4), new PairInt(3, 4), new PairInt(4, 5));
        criticalConnections(5, test.size(), test).stream().forEach(x -> {
            System.out.println(x.toString());
        });
    }

    @Test
    public void test2() {
        List<PairInt> test = Arrays.asList(new PairInt(1, 2),
                new PairInt(3, 4), new PairInt(2,3));
        criticalConnections(5, test.size(), test).stream().forEach(x -> {
            System.out.println(x.toString());
        });
    }

    public static boolean flag = false;

    public List<PairInt> criticalConnections(int numOfServers, int numOfConnections, List<PairInt> connections) {
        if (numOfServers == 0 || numOfConnections == 0) {
            return new ArrayList<>();
        }
        if (numOfConnections == 1 || numOfConnections < numOfServers) {
            return connections;
        }
        List<PairInt> result = new ArrayList<>();
        for (int i = 0; i < connections.size(); i++) {
            List<PairInt> copy = new ArrayList<>(connections);
            PairInt remove = copy.remove(i);
            Set<Integer> visitedServer = new HashSet<>();
            visitedServer.add(copy.get(0).a);
            visitedServer.add(copy.get(0).b);
            boolean[] visitedConnection = new boolean[numOfConnections];
//            helper(copy, visitedServer, 1, numOfServers, visitedConnection);
            helper(copy, numOfServers);
            if (flag == false) {
                result.add(remove);
            }
            flag = false;
        }
        return result;
    }

    private void dfs(List<PairInt> copy, Set<Integer> visitedServer, int count, int numOfServers, boolean[] visitedConnection) {
        if (count == copy.size()) {
            return;
        }
        for (int i = 1; i < copy.size(); i++) {
            boolean flagA = false;
            boolean flagB = false;
            int tempA = 0, tempB = 0;
            if (visitedConnection[i] == false) {
                visitedConnection[i] = true;
                if (visitedServer.contains(copy.get(i).a) || visitedServer.contains(copy.get(i).b)) {
                    if (visitedServer.contains(copy.get(i).a)) {
                        flagA = true;
                        tempA = copy.get(i).a;
                    }
                    if (visitedServer.contains(copy.get(i).b)) {
                        flagB = true;
                        tempB = copy.get(i).b;
                    }
                    visitedServer.add(copy.get(i).a);
                    visitedServer.add(copy.get(i).b);
                    if (visitedServer.size() == numOfServers) {
                        flag = true;
                    }
//                    dfs(copy, visitedServer, count + 1, numOfServers, visitedConnection);
                }
                visitedConnection[i] = false;
                if (flagA == true) {
                    visitedServer.remove(tempA);
                }
                if (flagB == true) {
                    visitedServer.remove(tempB);
                }
            }
        }
    }

//    private void dfs(List<PairInt> copy, Set<Integer> visitedServer, int count, int numOfServers, boolean[] visitedConnection) {
//        if (count == copy.size()) {
//            return;
//        }
//        for (int i = 1; i < copy.size(); i++) {
//            if (visitedConnection[i] == false) {
//                visitedConnection[i] = true;
//                if (visitedServer.contains(copy.get(i).a) || visitedServer.contains(copy.get(i).b)) {
//
//                    visitedServer.add(copy.get(i).a);
//                    visitedServer.add(copy.get(i).b);
//                    if (visitedServer.size() == numOfServers) {
//                        flag = true;
//                    }
////                    dfs(copy, visitedServer, count + 1, numOfServers, visitedConnection);
//                }
//                visitedConnection[i] = false;
//            }
//
//        }
//    }

    private void helper(List<PairInt> copy, int numOfServers) {
        Set<Integer> visitedServer = new HashSet<>();
        for (int i = 0; i < copy.size(); i++) {
            PairInt pair = copy.get(i);
            visitedServer.add(pair.a);
            visitedServer.add(pair.b);
        }
        if (visitedServer.size() == numOfServers){
            flag = true;
        }
    }

    private void graph(List<List<Integer>> graph){
        List<List<Integer>> s = graph;
        for (List a : s){
            System.out.println();
            List<List<Integer>> copy = new ArrayList<>(graph);
            List<Integer> connect = copy.remove(1);
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        map.keySet().iterator().next();
    }


    private static void bfs(HashMap<Character, LinkedList<Character>> graph, HashMap<Character, Integer> dist, char start) {
        Queue<Character> q = new LinkedList<>();
        q.add(start);// 将s作为起始顶点加入队列
        dist.put(start, 0);
        int i = 0;
        while (!q.isEmpty()) {
            char top = q.poll();// 取出队首元素
            i++;
            System.out.println("The " + i + "th element:" + top + " Distance from s is:" + dist.get(top));
            int d = dist.get(top) + 1;// 得出其周边还未被访问的节点的距离
            for (Character c : graph.get(top)) {
                if (!dist.containsKey(c))// 如果dist中还没有该元素说明还没有被访问
                {
                    dist.put(c, d);
                    q.add(c);
                }
            }
        }
    }

    private Integer dfs(HashMap<Integer,ArrayList<Integer>> graph, Set<Integer> visitedSet){
        Integer root = 0;
        for(Integer key : graph.keySet()){
            root = key;
        }
        Queue<Integer> q = new LinkedList<>();
        ((LinkedList<Integer>) q).add(root);
        visitedSet.add(root);
        while(!q.isEmpty()){
            Integer cur = q.poll();
            visitedSet.add(cur);
            for (Integer neighbour : graph.get(cur)){
                if (!visitedSet.contains(neighbour)){
                    ((LinkedList<Integer>) q).add(neighbour);
                }
            }
        }
        return visitedSet.size();
    }

    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {}

        public Node(int _val,List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    };

    HashMap<Node, Node> graph = new HashMap<Node, Node>();
    public Node cloneGraph(Node node) {
        copy(node);
        return graph.get(node);
    }

    private void copy(Node node){
        Queue<Node> q = new ArrayDeque<Node>();
        q.offer(node);
        while(!q.isEmpty()){
            Node n = q.poll();
            if(!graph.containsKey(n)){
                Node c = new Node(n.val, new ArrayList<Node>());
                graph.put(n, c);
                for(Node neigh : n.neighbors){
                    q.offer(neigh);
                }
            }
        }
        for (Node ori : graph.keySet()){
            Node cp = graph.get(ori);
            ArrayList<Node> temp = (ArrayList<Node>) cp.neighbors;
            for (Node nei : ori.neighbors){
                temp.add(graph.get(nei));
            }
        }
    }

    public static int numIslands(char[][] grid) {
        int num = 0;
        for(int i = 0; i < grid.length ; i++){
            for(int j = 0 ; j < grid[0].length ; j++){
                if(grid[i][j] == '1'){
                    dfs(i, j, grid);
                    num ++;
                }
            }
        }
        return num;
    }

    private static void dfs(int i, int j, char[][] grid){
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1' )
            return;
        grid[i][j] = '0';
        dfs(i + 1, j, grid);
        dfs(i - 1, j, grid);
        dfs(i, j + 1, grid);
        dfs(i, j - 1, grid);
    }

    public static String longestPalindrome(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        String res = "";
        for(int i = 0 ; i < s.length() ; i++){
            dp[i][i] = true;
        }
        for(int i = 0 ; i < s.length(); i ++){
            for(int j = 0 ; j <= i ; j ++){
                if(s.charAt(j) == s.charAt(i) && ((i - j <= 2) || dp[j + 1][i - 1])){
                    dp[j][i] = true;
                    if(res.length() < (i - j + 1)){
                        res = s.substring(j,i + 1);
                    }
                }else{
                    dp[j][i] = false;
                }
            }
        }
        return res;
    }

    public static int getResult(int num, List<Integer> list, int target){
        Map<Integer, Integer> map = new HashMap<>();
        int cnt = 0;
        for(int i : list){
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()){
            if(entry.getKey() <= target/2){
                Integer integer = map.get(target - entry.getKey());
                if (integer != null){
                    cnt ++;
                    if (target % 2 == 0 && entry.getKey() == target/2 && entry.getValue() < 2){
                        cnt --;
                    }
                }
            }
        }
        return cnt;
    }

    public static String mostCommonWord(String paragraph, String[] banned) {
        paragraph = paragraph.toLowerCase();
        paragraph = paragraph + ".";
        StringBuilder sb = new StringBuilder();
        List<String> set = new ArrayList<String>(Arrays.asList(banned));
        HashMap<String, Integer> map = new HashMap<>();
        char[] chars = paragraph.toCharArray();
        String res = "";
        int max = 0;
        for(int i = 0 ; i < chars.length ; i++){
            if(Character.isLetter(chars[i])){
                sb.append(chars[i]);
            }else if(sb.length() != 0){
                String word = sb.toString();
                if (!set.contains(word)){
                    map.put(word, map.getOrDefault(word,0) + 1);
                    if (map.get(word) > max){
                        res = word;
                        max = map.get(word);
                    }
                }
                sb = new StringBuilder();
            }
        }
        return res;
    }
    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head = new ListNode(0);
        ListNode cur = head;
        PriorityQueue<ListNode> q = new PriorityQueue<ListNode>((n1, n2) -> {
            if(n1.val > n2.val)
                return 1;
            else if(n1.val < n2.val)
                return -1;
            else
                return 0;
        });

        HashMap hashMap = new HashMap();

        for(ListNode node : lists){
            q.offer(node);
        }
        while(!q.isEmpty()){
            ListNode t = q.poll();
            cur.next = t;
            q.offer(t.next);
        }
        return head.next;
    }

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> res = new ArrayList<String>();
        HashSet<String> dict = new HashSet<String>(Arrays.asList(words));

        for(String s : dict){
            dict.remove(s);
            if(wordBreak(s, dict)){
                res.add(s);
            }
            dict.add(s);
        }
        return res;
    }

    public String[] spellchecker(String[] wordlist, String[] queries) {
        Set<String> words = new HashSet<>(Arrays.asList(wordlist));
        HashMap<String, String> cap = new HashMap<>();
        HashMap<String, String> vowel = new HashMap<>();
        for (String w : wordlist) {
            String lower = w.toLowerCase(), devowel = lower.replaceAll("[aeiou]", "#");
            cap.putIfAbsent(lower, w);
            vowel.putIfAbsent(devowel, w);
        }
        for (int i = 0; i < queries.length; ++i) {
            if (words.contains(queries[i])) continue;
            String lower = queries[i].toLowerCase(), devowel = lower.replaceAll("[aeiou]", "#");
            if (cap.containsKey(lower)) {
                queries[i] = cap.get(lower);
            } else if (vowel.containsKey(devowel)) {
                queries[i] = vowel.get(devowel);
            } else {
                queries[i] = "";
            }
        }
        return queries;
    }

    private boolean wordBreak(String s, HashSet<String> dict) {
        return false;
    }

    public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
        int[] res = new int[n];
        HashMap<Integer, ArrayList<Integer>> redGraph = new HashMap<Integer, ArrayList<Integer>>();
        HashMap<Integer, ArrayList<Integer>> blueGraph = new HashMap<Integer, ArrayList<Integer>>();

        for(int[] edge : red_edges){
            ArrayList<Integer> list = redGraph.getOrDefault(edge[0], new ArrayList<Integer>());
            list.add(edge[1]);
            redGraph.put(edge[0], list);
        }
        for(int[] edge : blue_edges){
            ArrayList<Integer> list = blueGraph.getOrDefault(edge[0], new ArrayList<Integer>());
            list.add(edge[1]);
            blueGraph.put(edge[0], list);
        }

        for(int i = 0 ; i < n ; i ++){
            int red = redbfs(0, i, redGraph, blueGraph);
            int blue = bluebfs(0, i, redGraph, blueGraph);
            if(red == -1 && blue == -1)
                res[i] = -1;
            else if(red == -1)
                res[i] = blue;
            else if(blue == -1)
                res[i] = red;
            else
                res[i] = blue > red ? red : blue;
        }

        return res;
    }

    private int bluebfs(int start, int end, HashMap<Integer, ArrayList<Integer>> redGraph, HashMap<Integer, ArrayList<Integer>> blueGraph){
        Queue<Integer> q = new ArrayDeque<Integer>();
        q.offer(start);
        int step = 0;
        Set<Integer> red = new HashSet<Integer>();
        Set<Integer> blue = new HashSet<Integer>();

        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0 ; i < size ; i++){
                Integer arr = q.poll();

                if(arr == end)
                    return step;

                if(step % 2 == 0){
                    if(blueGraph.containsKey(arr)){
                        for(Integer p : blueGraph.get(arr)){
                            if(!blue.contains(p)){
                                q.offer(p);
                                blue.add(p);
                            }
                        }
                    }
                }else if(step % 2 == 1){
                    if(redGraph.containsKey(arr)){
                        for(Integer p : redGraph.get(arr)){
                            if(!red.contains(p)){
                                q.offer(p);
                                red.add(p);
                            }
                        }
                    }
                }
            }
            step ++;
        }
        return -1;
    }

    private int redbfs(int start, int end, HashMap<Integer, ArrayList<Integer>> redGraph, HashMap<Integer, ArrayList<Integer>> blueGraph){
        Queue<Integer> q = new ArrayDeque<Integer>();
        q.offer(start);
        int step = 0;
        Set<Integer> red = new HashSet<Integer>();
        Set<Integer> blue = new HashSet<Integer>();

        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0 ; i < size ; i++){
                Integer arr = q.poll();

                if(arr == end)
                    return step;

                if(step % 2 == 0){
                    if(redGraph.containsKey(arr)){
                        for(Integer p : redGraph.get(arr)){
                            if(!red.contains(p)){
                                q.offer(p);
                                red.add(p);
                            }
                        }
                    }
                }else if(step % 2 == 1){
                    if(blueGraph.containsKey(arr)){
                        for(Integer p : blueGraph.get(arr)){
                            if(!blue.contains(p)){
                                q.offer(p);
                                blue.add(p);
                            }
                        }
                    }
                }
            }
            step ++;
        }
        return -1;
    }



    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, ArrayList<Integer>> graph = new HashMap<Integer, ArrayList<Integer>>();
        if(numCourses == 0 || numCourses == 1)
            return true;
        for(int[] req : prerequisites){
            int course = req[0];
            int pre = req[1];
            ArrayList<Integer> list = graph.getOrDefault(pre, new ArrayList<Integer>());
            list.add(course);
            graph.put(pre, list);
        }
        Stack<Integer> stack = new Stack<Integer>();
        Set<Integer> set = new HashSet<Integer>();

        stack.add(prerequisites[0][1]);

        while(!stack.isEmpty()){
            int pre = stack.pop();
            if(!set.add(pre))
                return false;
            List<Integer> courses = graph.getOrDefault(pre, new ArrayList<>());

            for(Integer course : courses){
                stack.add(course);
            }
        }

        return set.size() == numCourses;
    }

    public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> graph = new HashMap<String, Map<String, Double>>();
        buildGraph(equations, values, graph);

        double[] res = new double[queries.size()];
        Arrays.fill(res, -1.0);

        for(int i = 0 ; i < queries.size() ; i ++){
            List<String> q = queries.get(i);
            String from = q.get(0);
            String to = q.get(1);
            if(!graph.containsKey(from) || !graph.containsKey(to)){
                res[i] = -1;
            }else{
                dfs(from, to, graph, new HashSet<String>(), 1.0, res, i);
            }
        }

        return res;
    }

    private static void dfs(String start, String end, Map<String, Map<String, Double>> graph, Set<String> visited, Double value, double[] res, int index){
        if(graph.get(start) == null || graph.get(start).size() == 0){
            return;
        }
        if(graph.get(start).containsKey(end)){
            res[index] = value * graph.get(start).get(end);
            return;
        }
        for(String next : graph.get(start).keySet()){
            if(visited.add(next)){
                dfs(next, end, graph, visited, value * graph.get(start).get(next), res, index);
            }
        }
    }

    private static void buildGraph(List<List<String>> equations, double[] values, Map<String, Map<String, Double>> graph){
        for(int i = 0 ; i < equations.size() ; i ++){
            List<String> e = equations.get(i);
            String from = e.get(0);
            String to = e.get(1);
            double value = values[i];
            graph.putIfAbsent(from, new HashMap<String, Double>());
            graph.putIfAbsent(to, new HashMap<String, Double>());
            graph.get(from).put(to, value);
            graph.get(to).put(from, 1.0 / value);
            graph.get(from).put(from, 1.0);
            graph.get(to).put(to, 1.0);
        }
    }

    TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();


    /** Record a hit.
     @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        if(map.containsKey(timestamp)){
            map.put(timestamp, map.get(timestamp) + 1);
        }else{
            map.put(timestamp, 1);
        }
    }

    /** Return the number of hits in the past 5 minutes.
     @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        int start = timestamp < 300 ? 0 : timestamp - 300 + 1;
        int end = timestamp + 1;
        SortedMap<Integer, Integer> subMap = map.subMap(start, end);
        int count = 0;
        for (Integer k : subMap.keySet()) {
            count += subMap.get(k);
        }
        return count;
    }

    public int[][] highFive(int[][] items) {
        HashMap<Integer, Integer> map = new HashMap<Integer,Integer>();
        for(int[] item : items){
            int key = item[0];
            int value = item[1];
            if(map.containsKey(key)){
                map.put(key, map.get(key) + value);
            }else{
                map.put(key, value);
            }
        }

        PriorityQueue<int[]> q = new PriorityQueue<int[]>((s1, s2) -> {
            return s2[1] - s1[1];
        });

        for(Integer key : map.keySet()){
            q.offer(new int[]{key, map.get(key)});
        }

        int size = Math.min(q.size(), 5);
        int[][] res = new int[size][2];
        for(int i = 0 ; i < size ; i ++){
            int[] cur = q.poll();
            res[i] = cur;
        }
        Arrays.sort(res, (a1, a2) -> {
            return a1[0] - a2[0];
        });
        return res;
    }

    public static int maxLength(List<String> A) {
        List<Integer> dp = new ArrayList<>();
        dp.add(0);
        int res = 0;
        for (String s : A) {
            int a = 0, dup = 0;
            for (char c : s.toCharArray()) {
                dup |= a & (1 << (c - 'a'));
                a |= 1 << (c - 'a');
            }
            if (dup > 0) continue;
            for (int i = dp.size() - 1; i >= 0; --i) {
                if ((dp.get(i) & a) > 0) continue;
                dp.add(dp.get(i) | a);
                res = Math.max(res, Integer.bitCount(dp.get(i) | a));
            }
        }
        return res;
    }

    int[][] dirs = {{1, 1},{1, -1}, {-1, 1} , {-1, -1}};

    public int longestIncreasingPath(int[][] matrix) {
        int max = 1;
        if(matrix == null || matrix.length == 0) return max;
        int[][] cache = new int[matrix.length][matrix[0].length];

        for(int i = 0 ; i < matrix.length ; i++){
            for(int j = 0 ; j < matrix[i].length ; j ++){
                int len = dfs(i, j, matrix, cache);
                max = Math.max(len, max);
            }
        }
        return max;
    }

    private int dfs(int row, int col, int[][] matrix, int[][] cache) {
        if(cache[row][col] != 0) return cache[row][col];
        if(row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length) return 0;

        int max = 0;
        for(int[] dir : dirs) {
            int x = dir[0] + row;
            int y = dir[1] + col;
            int temp = 0;
            if(x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length) continue;
            else{
                if(matrix[row][col] < matrix[x][y]){
                    temp = 1 + dfs(x,y,matrix,cache);
                }
            }
            max = Math.max(temp, max);
        }
        cache[row][col] = max;
        return max;
    }

    public static void main(String[] args) {
        List<String> a = Arrays.asList("un","iq","ue");
        maxLength(a);
    }

}
