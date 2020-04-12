package leetcode;

public class Solution2 {
    public static void main(String[] args) {
        int[] a = {2,3,4,2,5,1,2};
        System.out.println(solution(a, 10));
    }

    public static int solution(int[] plants, int capacity){
        int left = 0;
        int right = plants.length - 1;
        int leftWater = capacity;
        int rightWater = capacity;
        int round = 1;

        while(left < right){
            if (canWater(plants, left, right, leftWater, rightWater)){
                leftWater = leftWater - plants[left];
                rightWater = rightWater - plants[right];
                left ++;
                right --;
            } else {
                round ++;
                leftWater = capacity;
                rightWater = capacity;
            }
        }
        if (left == right){
            if (leftWater + rightWater > plants[left])
                return round;
            else
                return round ++;
        }
        return round;
    }

    public static boolean canWater(int[] plants, int left, int right, int leftWater, int rightWater){
        if(plants[left] <= leftWater && plants[right] <= rightWater)
            return true;
        else
            return false;
    }

    public int solution2(int[] A, int[] B, int K){
        int sumA = 0;
        int sumB = 0;
        for (int a :
                A) {
            sumA += a;
        }
        for (int b :
                B) {
            sumB += b;
        }

        if (K == 0){
            return sumA > sumB ? sumA : sumB;
        }

        while(K != 0){
            if (sumA >= sumB){
                if (canReverse(A, B) >= 0){
                    int index = canReverse(A, B);
                    int temp = A[index];
                    A[index] = B[index];
                    B[index] = temp;
                    K --;
                } else {
                    break;
                }
            }else{
                if (canReverse(B, A) >= 0){
                    int index = canReverse(B, A);
                    int temp = A[index];
                    A[index] = B[index];
                    B[index] = temp;
                    K --;
                } else
                    break;
            }
        }

        return 0;

    }


    public int canReverse(int[] A, int[] B){
        int[] temp = new int[A.length];
        for (int i = 0 ; i < A.length ; i ++){
            temp[i] = A[i] - B[i];
        }
        int max = Integer.MIN_VALUE;
        int index = 0;
        for(int i = 0 ; i < temp.length ; i ++){
            if (temp[i] > max && temp[i] > 0){
                max = temp[i];
                index = i;
            }
        }
        if (max != Integer.MIN_VALUE) {
            return index;
        }else{
            return -1;
        }
    }
}
