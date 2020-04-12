package leetcode;

import pack1.aClass;

import java.util.HashMap;
import java.util.Map;

public class aClassSon extends aClass {

    public static int subarraysWithKDistinct(int[] A, int K) {
        Map<Integer, Integer> map = new HashMap<Integer,Integer>();
        int left = 0;
        int ans = 0;

        for(int i = 0 ; i < A.length ; i ++){
            map.put(A[i], map.getOrDefault(A[i], 0) + 1);

            do {
                int num = map.get(A[left]);
                if(num - 1 != 0){
                    map.put(A[left], num - 1);
                }else
                    map.remove(A[left]);
                left ++;
                ans ++;
            }while (map.size() > K);

            if(map.size() == K){
                ans ++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        int[] a = {1,2,1,3,4};
        System.out.println(subarraysWithKDistinct(a,3));
    }
}
