package leetcode;

import java.util.*;


class MedianFinder {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /** initialize your data structure here. */
    PriorityQueue<Integer> right = new PriorityQueue<Integer>();
    PriorityQueue<Integer> left = new PriorityQueue((o1,o2) -> {
        if((Integer)o1 <= (Integer)o2){
            return 1;
        }else{
            return -1;
        }
    });
    public List<String> wordBreak(String s, List<String> wordDict) {
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();

        return helper(s, map, wordDict);
    }

    private List<String> helper(String s, HashMap<String, ArrayList<String>> map, List<String> wordDict){
        if(map.containsKey(s))
            return map.get(s);

        ArrayList<String> ans = new ArrayList<String>();

        if(wordDict.contains(s))
            ans.add(s);

        for(int i = 1 ; i < s.length() ; i ++){
            String left = s.substring(0,i);
            String right = s.substring(i);
            if(!wordDict.contains(right))
                continue;
            List<String> leftRes = helper(left, map, wordDict);

            for(String str : leftRes){
                ans.add(str + " " + right);
            }
        }

        map.put(s, ans);
        return ans;
    }

    public void addNum(int num) {
        if(left.isEmpty() || num <= left.peek()){
            left.add(num);
        } else{
            right.add(num);
        }

        if (left.size() < right.size()){
            left.add(right.poll());
        }else if (left.size() - right.size() == 2){
            right.add(left.poll());
        }
    }

    public double findMedian() {
        if(left.size() > right.size()){
            return left.peek();
        }else{
            return ((double)(left.peek() + (double)right.peek()) / 2);
        }
    }

    public static double[] medianSlidingWindow(int[] nums, int k) {
        double[] res = new double[nums.length - k];
        int[] window = new int[k];
        for(int i = 0 ; i < k ; i ++){
            window[i] = nums[i];
        }
        Arrays.sort(window);
        for(int i = 0 ; i < nums.length - k ; i ++){
            res[i] = (((double)window[k / 2] + (double)window[(k - 1) / 2]) / 2);
            remove(window, nums[i]);
            insert(window, nums[i + k]);
        }

        return res;
    }

    private static void remove(int[] window, int n){
        int index = Arrays.binarySearch(window, n);
        for(int i = index ; i < window.length - 1 ; i ++){
            window[i] = window[i + 1];
        }
    }

    private static void insert(int[] window, int n){
        int index = Arrays.binarySearch(window, n);
        if (index < 0) index = - index - 1;
        int i;
        for(i = window.length - 1 ; i > index ; i --){
            window[i] = window[i - 1];
        }
        window[i] = n;
    }

    public static int candy(int[] ratings) {
        int[] ans = new int[ratings.length];
        for(int i = 0 ; i < ans.length ; i ++){
            ans[i] = 1;
        }

        for(int i = 1 ; i < ratings.length - 1 ; i ++){
            if(ratings[i] > ratings[i + 1]){
                ans[i] = ans[i + 1] + 1;
            }
            if(ratings[i] > ratings[i - 1]){
                ans[i] = ans[i - 1] + 1;
            }
        }

        for(int i = ratings.length - 2 ; i >= 1 ; i --){
            if(ratings[i] > ratings[i + 1]){
                ans[i] = ans[i + 1] + 1;
            }
            if(ratings[i] > ratings[i - 1]){
                ans[i] = ans[i - 1] + 1;
            }
        }
        int res = 0;
        for(int num : ans){
            res += num;
        }

        return res;
    }

    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        int left = 0;
        int right = 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int res = 0;

        for(int i = 0 ; i < s.length() ; i ++){
            if(map.size() < k + 1){
                map.put(s.charAt(i), i);
                right ++;
            }
            if(map.size() == k + 1){
                int index = Collections.min(map.values());
                map.remove(s.charAt(index));
                left = index + 1;
            }
            res = Math.max(res, right - left);
        }

        return res;
    }

    public static int characterReplacement(String s, int k) {
        HashMap<Character, Integer> map = new HashMap<Character,Integer>();
        int left = 0;
        int right = 0;
        int ans = 0;
        int most = 0;

        for(int i = 0 ; i < s.length() ; i ++){
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
            right ++;
            most = Math.max(most, map.get(s.charAt(i)));

            if(right - left - most <= k){
                ans = Math.max(ans, right - left);
            }

            while(right - left - most > k){
                map.put(s.charAt(left), map.get(s.charAt(left)) - 1);
                most = Collections.max(map.values());
                left ++;
            }
        }

        return ans;
    }


    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        LinkedList<TreeNode> q = new LinkedList<TreeNode>();
        if(root == null)
            return "";
        q.add(root);

        while(!q.isEmpty()){
            TreeNode temp = q.poll();
            if(temp != null){
                sb.append(temp.val + ",");

                q.add(temp.left);

                q.add(temp.right);
            }else
                sb.append("null,");
        }

        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null || data.length() == 0)
            return null;

        data = data.substring(0, data.length() - 1);
        String[] tree = data.split(",");
        int index = 0;
        TreeNode head = new TreeNode(Integer.valueOf(tree[index]));
        index ++;

        Queue<TreeNode> q = new ArrayDeque<TreeNode>();
        q.add(head);

        while(!q.isEmpty()){
            TreeNode temp = q.poll();
            String left = tree[index];
            index ++;
            String right = tree[index];
            index ++;

            if(left.equals("null")){
                temp.left = null;
            } else {
                TreeNode leftNode = new TreeNode(Integer.valueOf(left));
                temp.left = leftNode;
                q.add(leftNode);
            }

            if(right.equals("null")){
                temp.right = null;
            }else{
                TreeNode rightNode = new TreeNode(Integer.valueOf(right));
                temp.right = rightNode;
                q.add(rightNode);
            }
        }
        return head;
    }

    public static void main(String[] args) {
//        MedianFinder finder = new MedianFinder();
//        TreeNode root1 = new TreeNode(1);
//        TreeNode root2 = new TreeNode(2);
//        TreeNode root3 = new TreeNode(3);
//        TreeNode root4 = new TreeNode(4);
//        TreeNode root5 = new TreeNode(5);
//        root1.left = root2;
//        root1.right = root3;
//        root2.left = null;
//        root2.right = null;
//        root3.left = root4;
//        root3.right = root5;
//        System.out.println(finder.serialize(root1));
    }
}