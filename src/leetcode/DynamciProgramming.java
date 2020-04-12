package leetcode;

import java.util.*;

public class DynamciProgramming {
    public static int numRollsToTarget(int d, int f, int target) {
        int kMod = (int)Math.pow(10,9) + 7;
        int[][] dp = new int[d + 1][target + 1];
        dp[0][0] = 1;

        for(int i = 1; i <= d ; i ++){
            for(int j = 1 ; j <= target ; j ++){
                int k = 1;
                while(j - k >= 0 && k <= f){
                    dp[i][j] += dp[i - 1][j - k];
                    k ++;
                }
            }
        }
        return dp[d][target] % kMod;
    }

    PriorityQueue<Integer> right = new PriorityQueue<Integer>();
    PriorityQueue<Integer> left = new PriorityQueue<Integer>((i1,i2) -> {
        if(i2 > i1)
            return 1;
        else if(i2 < i1)
            return -1;
        else
            return 0;
    });
    int size = 0;

    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] res = new double[nums.length - k + 1];

        for(int i = 0 ; i < k ; i++){
            add(nums[i]);
        }

        for(int i = 0 ; i < res.length ; i ++){
            res[i] = findMedian();
            if(i + k == nums.length)
                break;
            add(nums[i + k]);
            remove(nums[i]);
        }
        return res;
    }

    private void add(int num){
        if(left.isEmpty() || left.peek() > num){
            left.offer(num);
        }else{
            right.offer(num);
        }

        if(left.size() - right.size() > 1){
            right.offer(left.poll());
        }else if(left.size() < right.size()){
            left.offer(right.poll());
        }
        size ++;
    }

    private void remove(int num){
        if (num <= findMedian()) {
            left.remove(num);
        }
        else {
            right.remove(num);
        }

        if(left.size() - right.size() > 1){
            right.offer(left.poll());
        }else if(left.size() < right.size()){
            left.offer(right.poll());
        }
        size --;
    }

    private double findMedian(){
        if(size % 2 == 1){
            return left.peek();
        }else{
            return (double)(((double)left.peek() + (double)right.peek()) / 2);
        }
    }

//    public static String minWindow(String S, String T) {
//        int[][] dp = new int[S.length() + 1][T.length() + 1];
//        int minLen = Integer.MAX_VALUE;
//        int start = -1;
//        int n = T.length();
//        for (int i = 0 ; i < dp.length ; i ++){
//            for (int j = 0 ; j < dp[0].length ; j ++){
//                dp[i][j] = -1;
//            }
//        }
//
//        for(int i = 0 ; i <= S.length() ; i++){
//            dp[i][0] = i;
//        }
//
//        for(int i = 1 ; i <= S.length() ; i++){
//            for(int j = 1 ; j <= Math.min(T.length(), i) ; j ++){
//                dp[i][j] = (S.charAt(i - 1) == T.charAt(j - 1)) ? dp[i - 1][j - 1] : dp[i - 1][j];
//            }
//            if(dp[i][n] != -1 && i > n){
//                int len = i - dp[i][n];
//                if(len < minLen){
//                    minLen = len;
//                    start = dp[i][n];
//                }
//            }
//        }
//        return start != -1 ? S.substring(start, start + minLen) : "";
//    }

    public static String minWindow(String S, String T) {
        int[][] dp = new int[S.length() + 1][T.length() + 1];
        int len = Integer.MAX_VALUE;
        int index = -1;
        for(int i = 0 ; i < dp.length ; i ++){
            Arrays.fill(dp[i], -1);
        }

        for(int i = 0 ; i <= S.length() ; i ++){
            dp[i][0] = i;
        }

        for(int i = 1; i <= S.length() ; i++){
            for(int j = 1 ; j <=  Math.min(T.length(), i); j ++){
                if(S.charAt(i - 1) == T.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1];
                }else{
                    dp[i][j] = dp[i - 1][j];
                }
            }
            if(dp[i][T.length()] != -1 && i >= T.length()){
                int l = i - dp[i][T.length()];
                if(l < len){
                    index = dp[i][T.length()];
                    len = l;
                }
            }
        }
        return index == -1 ? "": S.substring(index, index + len);
    }

    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();
        if (s == null || s.length() == 0 || p == null || p.length() == 0) return list;
        int[] hash = new int[26]; //character hash

        for (char c : p.toCharArray()) {
            hash[c - 'a']++;
        }

        int left = 0, right = 0, count = p.length();
        while (right < s.length()) {

            if (hash[s.charAt(right++) - 'a']-- >= 1) count--;

            if (count == 0) list.add(left);

            if (right - left == p.length() && hash[s.charAt(left++) - 'a']++ >= 0) count++;
        }
        return list;
    }

    public static boolean isAnagram(String s, String t) {
        Map<Character, Integer> map = new HashMap<Character,Integer>();
        for(Character c : s.toCharArray()){
            map.put(c, map.getOrDefault(c,0) + 1);
        }

        for(Character c : t.toCharArray()){
            if(map.containsKey(c)){
                map.put(c, map.get(c) - 1);
                if(map.get(c) <= 0){
                    map.remove(c);
                }
            }
        }

        return map.size() == 0 ? true : false;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1.length > nums2.length){
            return findMedianSortedArrays(nums2, nums1);
        }
        int len = nums1.length + nums2.length;
        int cut1 = 0;
        int cut2 = 0;
        int l = 0;
        int r = nums1.length;

        while(r <= nums1.length){
            cut1 = (r - l) / 2 + l;
            cut2 = len / 2 - cut1;
            double L1 = cut1 == 0 ? Integer.MIN_VALUE : nums1[cut1 - 1];
            double L2 = cut2 == 0 ? Integer.MIN_VALUE : nums2[cut2 - 1];
            double R1 = cut1 == 0 ? Integer.MAX_VALUE : nums1[cut1];
            double R2 = cut2 == 0 ? Integer.MAX_VALUE : nums2[cut2];
            if(L1 > R2){
                r = cut1 - 1;
            }else if(L2 > R1){
                l = cut2 + 1;
            }else{
                if(len % 2 == 0){
                    double a1 = Math.max(L1,L2);
                    double a2 = Math.min(R1, R2);
                    return (a1 + a2) / 2;
                }else{
                    return Math.min(L2, R1);
                }
            }
        }
        return -1;
    }

    public String longestPalindrome(String s) {
        boolean[][] dp = new boolean[s.length() + 1][s.length() + 1];
        int len = 0;
        String res = "";
        dp[0][0] = true;

        for(int j = 1 ; j <= s.length() ; j ++){
            for(int i = 0 ; i <= j ; i ++){
                if(s.charAt(i) == s.charAt(j) && ((j - i <= 2) || dp[i + 1][j - 1])){
                    dp[i][j] = true;
                    if(j - i > len){
                        res = s.substring(i, j + 1);
                    }
                }
            }
        }
        return res;

    }

    public void rotate(int[] nums, int k) {
        List<Integer> list = new ArrayList<Integer>();
        for(int i = 0 ; i < nums.length * 2 ; i ++){
            list.add(nums[i % nums.length]);
        }
        list = list.subList(nums.length - k, nums.length - k + nums.length);
        for (int i = 0 ; i < nums.length ; i ++){
            nums[i] = list.get(i);
        }
    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> indegree = new HashMap<>();

        for(int i = 0 ; i < numCourses ; i ++){
            graph.put(i, new ArrayList<>());
            indegree.put(i,0);
        }

        for(int[] pre : prerequisites){
            int first = pre[1];
            int second = pre[0];
            graph.get(first).add(second);
            indegree.put(second, indegree.get(second) + 1);
        }

        Queue<Integer> q = new ArrayDeque<Integer>();
        for(Map.Entry<Integer,Integer> entry : indegree.entrySet()){
            if(entry.getValue() == 0){
                q.offer(entry.getKey());
            }
        }
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0 ; i < size ; i ++){
                Integer node = q.poll();
                for(Integer neigh : graph.get(node)){
                    indegree.put(neigh, indegree.get(neigh) - 1);
                    if(indegree.get(neigh) <= 0){
                        q.offer(neigh);
                    }
                }
            }
        }

        for(Map.Entry<Integer,Integer> entry : indegree.entrySet()){
            if(entry.getValue() > 0){
                return false;
            }
        }
        return true;
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> indegree = new HashMap<>();

        for(int i = 0 ; i < numCourses ; i ++){
            graph.put(i, new ArrayList<>());
            indegree.put(i, 0);
        }

        for(int[] pre : prerequisites){
            int first = pre[1];
            int then = pre[0];
            graph.get(first).add(then);
            indegree.put(then, indegree.get(then) + 1);
        }

        List<Integer> res = new ArrayList<>();
        Queue<Integer> q = new ArrayDeque<>();
        for(Map.Entry<Integer, Integer> entry : indegree.entrySet()){
            if(entry.getValue() == 0){
                q.offer(entry.getKey());
            }
        }
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0 ; i < size ; i ++){
                Integer cur = q.poll();
                res.add(cur);
                for(Integer nei : graph.get(cur)){
                    indegree.put(nei, indegree.get(nei) - 1);
                    if(indegree.get(nei) <= 0){
                        q.offer(nei);
                    }
                }
            }
        }
        int[] ans = new int[res.size()];
        return ans;
    }

    public char[][] updateBoard(char[][] board, int[] click) {
        if(board == null || board.length == 0)
            return board;
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        Queue<int[]> q = new LinkedList<int[]>();
        q.offer(click);

        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0 ; i < size ; i++){
                int[] cc = q.poll();
                int row = cc[0];
                int col = cc[1];
                int mineNumber = haveMine(cc, board);
                if(mineNumber != 0){
                    board[row][col] = (char)(mineNumber - '0');
                }else{
                    board[row][col] = 'B';
                    if(row + 1 < board.length && (board[row +1][col] == 'M' || board[row +1][col] == 'E') )q.offer(new int[]{row + 1, col});
                    if(row - 1 >= 0 && (board[row - 1][col] == 'M' || board[row - 1][col] == 'E')) q.offer(new int[]{row - 1, col});
                    if(col + 1 < board[0].length && (board[row][col + 1] == 'M' || board[row][col + 1] == 'E')) q.offer(new int[] {row, col + 1});
                    if(col - 1 >= 0 && (board[row][col - 1] == 'M' || board[row][col - 1] == 'E')) q.offer(new int[] {row, col - 1});
                    if(row + 1 < board.length && col + 1 < board[0].length && (board[row + 1][col + 1] == 'M' || board[row + 1][col + 1] == 'E')) q.offer(new int[]{row + 1, col + 1});
                    if(row - 1 >= 0 && col + 1 < board[0].length && (board[row - 1][col + 1] == 'M' || board[row - 1][col + 1] == 'E')) q.offer(new int[] {row - 1, col + 1});
                    if(row + 1 < board.length && col - 1 >= 0 && (board[row + 1][col - 1] == 'M' || board[row + 1][col - 1] == 'E')) q.offer(new int[] {row + 1, col - 1});
                    if(row - 1 >= 0 && col - 1 >= 0 && (board[row - 1][col - 1] == 'M' || board[row - 1][col - 1] == 'E')) q.offer(new int[]{row - 1, col - 1});
                }
            }
        }
        return board;
    }

    private int haveMine(int[] cc, char[][] board){
        int sum = 0;
        int row = cc[0];
        int col = cc[1];
        if (row + 1 < board.length && board[row + 1][col] == 'M') sum++;
        if (row - 1 >= 0 && board[row - 1][col] == 'M') sum ++;
        if (col + 1 < board[0].length && board[row][col + 1] == 'M') sum ++;
        if (col - 1 >= 0 && board[row][col - 1] == 'M') sum ++;
        if (row + 1 < board.length && col + 1 < board[0].length && board[row + 1][col + 1] == 'M') sum ++;
        if (row + 1 < board.length && col - 1 >= 0 && board[row + 1][col - 1] == 'M') sum ++;
        if (row - 1 >= 0 && col + 1 < board[0].length && board[row - 1][col + 1] == 'M') sum ++;
        if (row - 1 >= 0 && col - 1 >= 0 && board[row - 1][col - 1] == 'M') sum ++;
        return sum;
    }


    public static void main(String[] args) {

    }
}
