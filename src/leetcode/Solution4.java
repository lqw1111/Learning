package leetcode;

import java.util.*;

public class Solution4 {
    public static String mostCommonWord(String paragraph, String[] banned) {
        paragraph += ".";
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        int max = 0;
        StringBuilder sb = new StringBuilder();
        String res = "";

        for(Character c : paragraph.toCharArray()){
            if(Character.isLetter(c)){
                sb.append(Character.toLowerCase(c));
            }else{
                String temp = sb.toString();
                if(!map.containsKey(temp)){
                    map.put(temp, 1);
                }else{
                    map.put(temp, map.get(temp) + 1);
                }
                if(map.get(temp) > max && !helper(banned, temp)){
                    max = map.get(temp);
                    res = temp;
                }
                sb = new StringBuilder();
            }
        }
        return res;
    }

    private static boolean helper(String[] banned, String temp){
        for(String ban: banned){
            if(temp.equals(ban)){
                return true;
            }
        }
        return false;
    }
    public static boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length - 1;
        int col = 0;
        while(row >= 0 && col < matrix[0].length){
            if(matrix[row][col] == target)
                return true;
            else if(matrix[row][col] > target){
                row -= 1;
            }else{
                col += 1;
            }
        }
        return false;
    }

    public int[][] kClosest(int[][] points, int K) {
        Arrays.sort(points, (point1, point2) -> {
            if(point1[0]*point1[0] + point1[1]*point1[1] == point2[0]*point2[0] + point2[1]*point2[1])
                return 0;
            return (point1[0]*point1[0] + point1[1]*point1[1] > point2[0]*point2[0] + point2[1]*point2[1]) ? 1 : -1;
        });
        int[][] res = new int[K][2];
        for (int i = 0 ; i < K ; i++){
            res[i] = points[i];
        }
        return res;
    }

    public static int orangesRotting(int[][] grid) {
        int count = 0;
        int fresh = 0;
        Queue<int[]> q = new ArrayDeque<>();

        for(int i = 0 ; i < grid.length ; i ++){
            for(int j = 0 ; j < grid[0].length ; j ++){
                if (grid[i][j] == 2){
                    int[] po = {i,j};
                    q.add(po);
                }else if(grid[i][j] == 1){
                    fresh ++;
                }
            }
        }

        while(!q.isEmpty()){
            int size = q.size();

            for(int i = 0 ; i < size ; i ++){
                int[] position = q.poll();
                int row = position[0];
                int col = position[1];
                ArrayList<int[]> list = new ArrayList<>();
                int[] a = {row + 1, col};
                int[] b = {row - 1, col};
                int[] c = {row, col + 1};
                int[] d = {row, col - 1};
                list.add(a);
                list.add(b);
                list.add(c);
                list.add(d);
                for (int[] p : list){
                    if (p[0] >= 0 && p[0] < grid.length && p[1] >= 0 && p[1] < grid[0].length && grid[p[0]][p[1]] == 1){
                        grid[p[0]][p[1]] = 2;
                        q.add(p);
                        fresh --;
                    }
                }
            }
            count ++;
        }
        return fresh == 0 ? count - 1 : -1;
    }

    public static void main(String[] args) {
//        int[][] a = {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19},{3, 6, 9, 16, 22},
//                {10, 13, 14, 17, 24},
//                {18, 21, 23, 26, 30}};
//        searchMatrix(a, 5);

//        int[][] a = {{2,1,1},{1,1,0},{0,1,1}};
        int[][] a = {{0,2}};
        System.out.println(orangesRotting(a));
    }
}
