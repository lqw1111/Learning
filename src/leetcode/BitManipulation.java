package leetcode;

import java.util.*;

public class BitManipulation {

    public static int singleNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < 32; ++i) {
            int sum = 0;
            for (int j = 0; j < nums.length ; ++j) {
                sum += (nums[j] >> i) & 1;
            }
            res |= (sum % 3) << i;
        }
        return res;
    }

    public static void main(String[] args) {
        List<Integer> a = new ArrayList<>(Arrays.asList(1,2));
        System.out.println(1 ^ 1);
        System.out.println(0 ^ 0);
    }
}
