package leetcode;

import java.util.*;

public class WordBreak {

    public static List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> res = new ArrayList<String>();
        if(words.length == 0 || words == null)
            return res;

        Set<String> dict = new HashSet<String>();
        for(String word : words){
            dict.add(word);
        }

        for(int i = 0 ; i < words.length ; i ++){
            dict.remove(words[i]);
            if(wordBreak(words[i], dict)){
                res.add(words[i]);
            }
            dict.add(words[i]);
        }
        return res;
    }

    private boolean canForm(String word, Set<String> dict)
    {
        boolean[] dp = new boolean[word.length() + 1];
        dp[0] = true;
        for(int i =1;i <= word.length() ; i++)
        {
            for(int j = 0;j< i; j++)
            {
                String sub = word.substring(j,i);
                if(dp[j] && dict.contains(sub))
                {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[word.length()];
    }

    public static boolean wordBreak(String s, Set<String> wordDict) {
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        for(int i = 1 ; i <= s.length() ; i ++){
            for(int j = 0 ; j < i ; j ++){
                String temp = s.substring(j , i);
                if(dp[j] && wordDict.contains(temp)){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[len];
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer>[] bucket = new List[nums.length + 1];
        Map<Integer, Integer> freqMap = new HashMap<Integer,Integer>();

        for(int num : nums){
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        List<Integer> res = new ArrayList<Integer>();

        for(int key : freqMap.keySet()){
            int freq = freqMap.get(key);
            if(bucket[freq] == null){
                bucket[freq] = new ArrayList<Integer>();
            }
            bucket[freq].add(key);
        }

        for(int i = bucket.length - 1; i >= 0 && res.size() < k ; i--){
            if(bucket[i] != null){
                res.addAll(bucket[i]);
            }
        }
        return res;
    }

    public static String getPermutation(int n, int k) {
        int[] fact = new int[n + 1];
        fact[0] = 1;
        for(int i = 1 ; i < fact.length ; i ++){
            fact[i] = i * fact[i - 1];
        }
        List<Integer> number = new ArrayList<Integer>();
        for(int i = 1 ; i <= n ; i ++){
            number.add(i);
        }
        StringBuilder sb = new StringBuilder();
        for(int i = n ; i >= 1 ; i--){
            int index = k / fact[i - 1];
            sb.append(Integer.toString(number.get(index)));
            number.remove(index);
            k = k % fact[i - 1];
        }
        return sb.toString();
    }

    public static int[] searchRange(int[] nums, int target) {
        if(nums == null || nums.length == 0)
            return new int[]{-1, -1};

        int[] res = new int[2];

        int left = findLeft(nums, target, 0, nums.length - 1);
        if(left == -1){
            return new int[]{-1, -1};
        }
        res[0] = left;
        res[1] = findRight(nums, target, 0, nums.length - 1);

        return res;
    }

    private static int findLeft(int[] nums, int target, int start, int end){

        while(start < end){
            int mid = (end + start) / 2;
            if(nums[mid] == target){
                if(nums[mid - 1] == target){
                    end = mid;
                }else{
                    return mid;
                }
            }else if(nums[mid] < target){
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }
        return -1;
    }

    private static int findRight(int[] nums, int target, int start, int end){

        while(start < end){
            int mid = (end + start) / 2;
            if(nums[mid] == target){
                if(nums[mid + 1] == target){
                    start = mid + 1;
                }else{
                    return mid;
                }
            }else if(nums[mid] < target){
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Integer a = -1;
        a.toString();
        System.out.println();
        Queue<TreeNode> q = new ArrayDeque<TreeNode>();

    }
}
