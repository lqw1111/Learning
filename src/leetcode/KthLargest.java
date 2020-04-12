package leetcode;

import java.util.*;

class KthLargest {
    final PriorityQueue<Integer> q;
    final int k;

    public KthLargest(int k, int[] a) {
        this.k = k;
        q = new PriorityQueue<>(k);
        for (int n : a)
            add(n);
    }

    public int add(int n) {
        if (q.size() < k)
            q.offer(n);
        else if (q.peek() < n) {
            q.poll();
            q.offer(n);
        }
        return q.peek();
    }

    public static String longestDupSubstring(String S) {
        int n = S.length();

        int[] nums = new int[n];
        for(int i = 0 ; i < n ; i ++)
            nums[i] = (int)S.charAt(i) - (int)'a';
        int a = 26;

        long modulus = (long)Math.pow(2, 32);

        int left = 1;
        int right = n;
        int L;
        while(left <= right){
            L = left + (right - left) / 2;
            if(search(L, a, modulus, n, nums) != -1)
                left = L + 1;
            else
                right = L - 1;
        }
        int start = search(left - 1, a, modulus, n, nums);
        return S.substring(start, start + left - 1);
    }

    private static int search(int L, int a, long modulus, int n, int[] nums){
        long h = 0;
        for(int i = 0 ; i < L ; i ++) {
            h = (h * a + nums[i]) % modulus;
        }

        HashSet<Long> seen = new HashSet<>();

        seen.add(h);

        long aL = 1;
        for(int i = 1 ; i <= L ; i ++)
            aL = (aL * a) % modulus;

        for(int start = 1; start < n - L + 1 ; start ++){
            h = (h * a - nums[start - 1] * aL % modulus + modulus) % modulus;
            h = (h + nums[start + L - 1]) % modulus;
            if (seen.contains(h)) return start;
            seen.add(h);
        }
        return -1;
    }

    public static void main(String[] args) {
        longestDupSubstring("banana");
        StringBuilder sb = new StringBuilder();
        sb.charAt(1);
    }
}