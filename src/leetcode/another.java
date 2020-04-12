package leetcode;

import pack1.aClass;

import java.util.*;

public class another {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map = new HashMap<>();
        List<List<String>> res = new ArrayList<>();
        for(String str : strs) {
            char[] s = str.toCharArray();
            Arrays.sort(s);
            String temp = new String(s);
            List<String> list = map.getOrDefault(temp, new ArrayList<>());
            list.add(str);
            map.put(temp, list);
        }
        for(String key : map.keySet()) {
            res.add(map.get(key));
        }
        return res;
    }

    public static void main(String[] args) {
        aClass a = new aClass();
        aClassSon aClassSon = new aClassSon();

        System.out.println();
    }
}
