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

    public static String stringShift(String s, int[][] shift) {
        if(s == null || s.length() == 0) return s;
        for(int[] sh : shift){
            int dir = sh[0];
            int amount = sh[1];
            String temp = s;
            if(dir == 0){
                //left
                String left = temp.substring(0, amount);
                String right = temp.substring(amount , temp.length());
                temp = right + left;
            }else{
                //right
                String left = temp.substring(0, temp.length() - amount);
                String right = temp.substring(temp.length() - amount, temp.length());
                temp = right + left;
            }
            s = temp;
        }
        return s;
    }

    public static void main(String[] args) {
        stringShift("abc",new int[][] {{0,1},{1,2}});
    }
}
