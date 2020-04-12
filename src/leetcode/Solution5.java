package leetcode;

import java.util.HashMap;
import java.util.Map;

class Solution5 {
    public static int lengthOfLongestSubstring(String s) {
        int left = 0;
        int right = 0;
        int max = 0;
        Map<Character, Integer> map = new HashMap<Character,Integer>();
        for(int i = 0 ; i < s.length() ; i ++){
            right ++;
            char c = s.charAt(i);
            if(!map.containsKey(c)){
                max = Math.max(max, right - left);
            }else{
                left = map.get(c) + 1;
                map.clear();
            }
            map.put(c, i);
        }

        return max;
    }

    public static void main(String[] args) {

    }
}
