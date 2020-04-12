package leetcode;

import java.util.*;

class Node{
    int row;
    int col;
    int val;
    public Node(){
    }

    public Node(int row, int col, int val){
        this.row = row;
        this.col = col;
        this.val = val;
    }
}

public class MaxMinPath {


    public static int maximumMinimumPath(int[][] A) {
        int min = Integer.MAX_VALUE;
        if (A == null || A.length == 0)
            return 0;
        PriorityQueue<Node> q = new PriorityQueue<Node>((n1, n2) -> {
            return n2.val - n1.val;
        });
        int[][] visited = new int[A.length][A[0].length];
        int row = 0;
        int col = 0;
        Node root = new Node(row,col,A[row][col]);
        q.offer(root);

        while((row != A.length - 1 || col != A[0].length - 1) && !q.isEmpty()){
            Node node = q.poll();
            row = node.row;
            col = node.col;
            min = Math.min(min, node.val);
            visited[row][col] = 1;

            if (row + 1 < A.length && col < A[0].length && visited[row + 1][col] != 1){
                q.offer(new Node(row + 1, col, A[row + 1][col]));
            }
            if (row < A.length && col + 1 < A[0].length && visited[row][col + 1] != 1){
                q.offer(new Node(row, col + 1, A[row][col + 1]));
            }
            if (row - 1 >= 0 && col < A[0].length && visited[row - 1][col] != 1){
                q.offer(new Node(row - 1, col, A[row - 1][col]));
            }
            if (row < A.length && col - 1 >= 0 && visited[row][col - 1] != 1){
                q.offer(new Node(row, col - 1, A[row][col - 1]));
            }
        }
        return min;
    }

    public static void main(String[] args) {

        Set<List<List<Integer>>> set = new HashSet<>();
        List<List<Integer>> b = Arrays.asList(Arrays.asList(0,0));
        List<List<Integer>> c = Arrays.asList(Arrays.asList(0,0));
        set.add(b);
        set.add(c);
        System.out.println(set.size());
    }
}
