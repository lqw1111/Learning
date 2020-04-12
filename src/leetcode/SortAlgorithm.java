package leetcode;

import java.util.Arrays;

public class SortAlgorithm {

    public static void mergeSort(int[] a, int low, int high){
        int mid = (low + high) / 2;
        if (low < high) {
            mergeSort(a, low, mid);
            mergeSort(a, mid + 1, high);
            merge(a, low, mid, high);
        }
    }

    private static void merge(int[] a, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int left = low;
        int right = mid + 1;
        int k = 0;
        while(left <= mid && right <= high) {
            if (a[left] < a[right]) {
                temp[k ++] = a[left ++];
            } else {
                temp[k ++] = a[right ++];
            }
        }

        while(left <= mid) {
            temp[k ++] = a[left ++];
        }

        while (right <= high) {
            temp[k ++] = a[right ++];
        }

        for (int i = 0 ; i < temp.length ; i ++) {
            a[i + low] = temp[i];
        }
    }

    public static void main(String[] args) {
        int a[] = { 51, 46, 20, 18, 65, 97, 82, 30, 77, 50 };
        mergeSort(a, 0, a.length - 1);
        System.out.println("排序结果：" + Arrays.toString(a));
    }


}
