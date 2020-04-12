package leetcode;

import java.util.Arrays;

public class MinStoneMoveCost {
    public static int mergeStones(int[] stones, int K) {
        int size = stones.length;
        if((size - 1)%(K - 1) != 0)
            return -1;

        int[] sum = new int[size + 1];
        for(int i = 0 ; i < size ; i ++){
            sum[i + 1] = sum[i] + stones[i];
        }
        int[][][] dp = new int[size][size][K + 1];
        for (int i = 0 ; i < dp.length ; i ++){
            for (int j = 0 ; j < dp[i].length ; j ++){
                for (int k = 0 ; k < dp[i][j].length ; k ++){
                    dp[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
        for(int i = 0 ; i < size ; ++i){
            dp[i][i][1] = 0;
        }

        for (int l = 2 ; l <= size ; ++l){
            for(int i = 0 ; i <= size - l ; ++i){
                int j = i + l - 1;
                for(int k = 2 ; k <= K ; ++k){
                    for(int m = i ; m < j ; m += K - 1){
                        dp[i][j][k] = Math.min(dp[i][j][k], dp[i][m][1] + dp[m + 1][j][k - 1]);
                    }
                }
                dp[i][j][1] = dp[i][j][K] + sum[j + 1] - sum[i];
            }
        }

        return dp[0][size - 1][1];
    }

    public static int connectSticks(int[] sticks) {
        Arrays.sort(sticks);
        int sum = 0;
        for(int i = 1 ; i < sticks.length ; i ++){
            for(int j = 0 ; j <= i ; j ++){
                sum += sticks[j];
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        int[] a = {3354,4316,3259,4904,4598,474,3166,6322,8080,9009};
        int K = 3;
        System.out.println(connectSticks(a));
    }
}
