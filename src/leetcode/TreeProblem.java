package leetcode;

import java.util.ArrayList;
import java.util.List;

public class TreeProblem {
    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for(String str : strs){
            sb.append(str).append("\n");
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> res = new ArrayList<String>();
        if(s.length() == 0 || s == null)
            return res;
        String[] strs = s.split("\n");

        for(String st : strs){
            res.add(st);
        }
        return res;
    }

    public static void main(String[] args) {
//        TreeProblem treeProblem = new TreeProblem();
//        List<String> strs = new ArrayList<>();
//        strs.add("");
//        strs.add("");
//        System.out.println(treeProblem.encode(strs));
        double r1 = 0.5;
        double r2 = 0.5;
        while(true){
            r2 = r1 + r2 - r1* r2;
            System.out.println(r2);
            r2 *= 0.5;
        }
    }
}
