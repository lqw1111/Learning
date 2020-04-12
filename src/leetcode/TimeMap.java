package leetcode;

import java.util.*;

public class TimeMap {
//    public class TreeNode {
//        int val;
//        TreeNode left;
//        TreeNode right;
//        TreeNode(int x) { val = x; }
//    }
//    HashMap<String, TreeMap<Integer, String>> map = new HashMap<String, TreeMap<Integer, String>>();
//    /** Initialize your data structure here. */
//    public TimeMap() {
//
//    }
//
//    public void set(String key, String value, int timestamp) {
//        TreeMap<Integer,String> m = map.getOrDefault(key, new TreeMap<>());
//        m.put(timestamp, value);
//        map.put(key, m);
//    }
//
//    public String get(String key, int timestamp) {
//        if (map.containsKey(key)){
//            TreeMap<Integer, String > m = map.get(key);
//            Map.Entry<Integer, String> res = m.floorEntry(timestamp);
//            if (res != null)
//                return res.getValue();
//            else
//                return "";
//        }else
//            return "";
//    }
//    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
//        int level = 0;
//        Queue<TreeNode> q = new ArrayDeque<TreeNode>();
//        Stack<Integer> stack = new Stack<Integer>();
//        List<List<Integer>> res = new ArrayList<>();
//
//        q.offer(root);
//        while(!q.isEmpty()){
//            int size = q.size();
//            LinkedList<Integer> temp = new LinkedList<>();
//            for(int i = 0 ; i < size ; i++){
//                TreeNode n = q.poll();
//                if(level % 2 == 0){
//                    temp.add(n.val);
//                }else{
//                    temp.addFirst(n.val);
//                }
//                if(n.left != null)
//                    q.offer(n.left);
//                if(n.right != null)
//                    q.offer(n.right);
//            }
//            res.add(temp);
//            level ++;
//
//        }
//        return res;
//    }
//
//    class Interval {
//        public int start;
//        public int end;
//
//        public Interval() {}
//
//        public Interval(int _start, int _end) {
//            start = _start;
//            end = _end;
//        }
//    };
//
//    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
//        List<Interval> times = new ArrayList<Interval>();
//        for (List<Interval> list : schedule){
//            for (Interval i : list){
//                times.add(i);
//            }
//        }
//        List<Interval> res = new ArrayList<Interval>();
//        Collections.sort(times, (t1, t2) -> {
//            if(t1.start > t2.start)
//                return 1;
//            else if(t1.start < t2.start)
//                return -1;
//            else
//                return 0;
//        });
//        int maxEnd = times.get(0).end;
//        for (Interval time : times){
//            if (time.start > maxEnd){
//                Interval temp = new Interval();
//                temp.start = maxEnd;
//                temp.end = time.start;
//                res.add(temp);
//            }
//            if (time.end > maxEnd){
//                maxEnd = time.end;
//            }
//        }
//        return res;
//    }
//
//    public static int maxUncrossedLines(int[] A, int[] B) {
//        int[][] dp = new int[B.length + 1][A.length + 1];
//        for(int i = 0 ; i < A.length ; i ++){
//            dp[0][i] = 0;
//        }
//        for(int i = 0 ; i < B.length ; i ++){
//            dp[i][0] = 0;
//        }
//        for(int i = 1 ; i <= B.length ; i ++){
//            for(int j  = 1 ; j <= A.length ; j ++){
//                if(B[i - 1] == A[j - 1]){
//                    dp[i][j] = Math.max(dp[i - 1][j - 1] + 1, Math.max(dp[i - 1][j], dp[i][j - 1]));
//                }else{
//                    dp[i][j] = Math.max(dp[i - 1][j - 1], Math.max(dp[i - 1][j], dp[i][j - 1]));
//                }
//            }
//        }
//        return dp[B.length][A.length];
//    }

    Map<Character,List<Character>> map = new HashMap<Character, List<Character>>();

    public List<String> letterCombinations(String digits) {
        List<Character> list1 = Arrays.asList('a','b','c');
        List<Character> list2 = Arrays.asList('d','e','f');
        List<Character> list3 = Arrays.asList('g','h','i');
        List<Character> list4 = Arrays.asList('j','k','l');
        List<Character> list5 = Arrays.asList('m','n','o');
        List<Character> list6 = Arrays.asList('p','q','r','s');
        List<Character> list7 = Arrays.asList('t','u','v');
        List<Character> list8 = Arrays.asList('w','x','y','z');
        map.put('2',list1);
        map.put('3',list2);
        map.put('4',list3);
        map.put('5',list4);
        map.put('6',list5);
        map.put('7',list6);
        map.put('8',list7);
        map.put('9',list8);

        List<String> res = new ArrayList<>();

        dfs(digits, 0, res, new StringBuilder());

        return res;
    }

    private void dfs(String digits, int index, List<String> res, StringBuilder temp){
        List<Character> list = map.get(digits.charAt(index));

        for (Character c : list){
            temp.append(c);

            if (index == digits.length()){
                res.add(temp.toString());
            }else{
                dfs(digits, index + 1, res, temp);
            }
            temp.deleteCharAt(temp.length());
        }
    }



    public static void main(String[] args) {

    }
}
