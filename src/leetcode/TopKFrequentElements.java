package leetcode;

import java.util.*;

public class TopKFrequentElements {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if(root == null)
            return res;
        res.add(root.val);
        dfs(root.left, res, true, false);
        dfs(root.right, res, false, true);
        return res;
    }

    private void dfs(TreeNode node, List<Integer> res, boolean findLeft, boolean findRight) {
        if (node == null)
            return;
        if (findLeft)
            res.add(node.val);
        if (!findLeft && !findRight && node.left == null && node.right == null)
            res.add(node.val);
        dfs(node.left, res, findLeft && node.left == null, findRight);
        dfs(node.right, res, findLeft, findRight && node.right == null);
        if (findRight)
            res.add(node.val);
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Integer> q = new PriorityQueue<Integer>(((o1, o2) -> {
            if (map.get(o1) < map.get(o2))
                return 1;
            else if (map.get(o1) > map.get(o2))
                return -1;
            else
                return 0;
        }));
        for (Integer num : nums){
            map.put(num, map.getOrDefault(num,0) + 1);
        }
        for (Integer num : map.keySet()){
            q.offer(num);
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0 ; i < k ; i ++){
            res.add(q.poll());
        }
        Random random = new Random();
        random.nextInt(1);
        ArrayList arrayList = new ArrayList();
        return res;
    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<String>(wordDict);
        HashMap<String, Boolean> memo = new HashMap<String, Boolean>();
        return helper(s, dict, memo);
    }

    private static boolean helper(String s, Set<String> dict, HashMap<String, Boolean> memo){
        if(memo.containsKey(s))
            return true;
        if(dict.contains(s)){
            memo.put(s, true);
            return true;
        }
        for(int i = 1 ; i < s.length() ; i ++){
            if(helper(s.substring(0,i), dict, memo) && dict.contains(s.substring(i))){
                memo.put(s, true);
                return true;
            }
        }
        memo.put(s, false);
        return false;
    }

    public static boolean find132pattern(int[] nums) {
        int[] min = new int[nums.length];
        Stack<Integer> stack = new Stack<Integer>();
        Integer m = Integer.MAX_VALUE;
        for(int i = 0 ; i < nums.length ; i++){
            if(nums[i] < m){
                m = nums[i];
            }
            min[i] = m;
        }
        for(int i = nums.length - 1; i >= 0 ; i--){
            if(stack.isEmpty()){
                stack.push(nums[i]);
                continue;
            }
            if(nums[i] == min[i])
                continue;
            Integer num = stack.peek();
            if(nums[i] > min[i] && num > nums[i])
                stack.push(nums[i]);
            else{
                while(nums[i] > min[i] && num < min[i]){
                    stack.pop();
                    num = stack.peek();
                }
                if(nums[i] > min[i] && num > min[i])
                    return true;
            }
        }
        return false;
    }

    public static int lengthLongestPath(String input) {
        Stack<Integer> stack = new Stack<>();
        Stack<String> stack1 = new Stack<>();
        int res = 0;
        stack.push(0);stack1.push("");

        for (String s : input.split("\n")){
            int level = s.lastIndexOf("\t") + 1;
            while (level + 1 < stack.size()){
                stack.pop();
                stack1.pop();
            }
            int len = stack.peek() + s.length() - level + 1;
            stack.push(len);
            stack1.push(s);
            if (s.contains("."))
                res = Math.max(res,len);
        }
        Character c = 'a';
        return res;
    }

    public static void main(String[] args) {
        String str = "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext";
        String str1 = "dir";
        System.out.println(str1.lastIndexOf("\t"));
        System.out.println(lengthLongestPath(str));
    }
}
