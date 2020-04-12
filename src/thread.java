import java.util.*;
import java.util.stream.Collectors;

public class thread{

    public String a;
    protected String b;
    private String c;

    public static int divide(int A, int B) {
        if (A == 1 << 31 && B == -1) return (1 << 31) - 1;
        int a = Math.abs(A), b = Math.abs(B), res = 0, x = 0;
        while (a - b >= 0) {
            for (x = 0; a - (b << x << 1) >= 0; x++);
            res += 1 << x;
            a -= b << x;
        }
        return (A > 0) == (B > 0) ? res : -res;
    }

    public static int maxProfit(int[] prices, int fee) {
        int res = 0;
        int min = prices[0];
        for(int i = 1; i < prices.length ; i ++){
            if(min > prices[i]){
                min  = prices[i];
            }else{
                if(i + 1 < prices.length && (i + 1 == prices.length || prices[i + 1] < prices[i]) && prices[i] - min > fee){
                    res += prices[i] - min - fee;
                    min = prices[i + 1];
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] a = {1,3,2,8,4,9};
        maxProfit(a, 2);
        Map map = new HashMap();
    }
}
