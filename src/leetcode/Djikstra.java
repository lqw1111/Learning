package leetcode;

import java.util.*;

public class Djikstra {
    public static int networkDelayTime(int[][] times, int N, int K) {
        int[][] graph = new int[N + 1][N + 1];
        for(int i = 0 ; i <= N ; i ++){
            Arrays.fill(graph[i], Integer.MAX_VALUE);
        }
        for(int[] time : times){
            int from = time[0];
            int to = time[1];
            int weight = time[2];
            graph[from][to] = weight;
        }

        int[] res = new int[N + 1];
        Arrays.fill(res, Integer.MAX_VALUE);
        res[K] = 0;

        PriorityQueue<Integer> q = new PriorityQueue<Integer>((i1, i2) -> {
            return res[i1] - res[i2];
        });

        q.offer(K);
        while(!q.isEmpty()){
            int curPoint = q.poll();

            for(int i = 0 ; i < graph[0].length ; i ++){
                if(graph[curPoint][i] != Integer.MAX_VALUE && res[curPoint] + graph[curPoint][i] < res[i]){
                    res[i] = res[curPoint] + graph[curPoint][i];
                    q.offer(i);
                }
            }
        }
        int count = 0;
        for (int i = 1; i <= N; i++) {
            if (res[i] == Integer.MAX_VALUE) {
                return -1;
            }
            count = Math.max(count, res[i]);
        }
        return count;
    }

    public static int networkDelayTime1(int[][] times, int N, int K) {
        int[] disTo = new int[N + 1];
        Arrays.fill(disTo, 1000);
        disTo[K] = 0;
        for(int i = 0 ; i < N ; i ++){
            for(int[]  time : times){
                int from = time[0];
                int to = time[1];
                int weight = time[2];
                disTo[to] = Math.min(disTo[to], disTo[from] + weight);
            }
        }
        int max = 0;
        for(int i = 1; i <= N ; i ++){
            if(disTo[i] == Integer.MAX_VALUE)
                return -1;
            max = Math.max(max, disTo[i]);
        }
        return max;
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preorder(sb, root);
        return sb.toString();
    }

    private void preorder(StringBuilder sb, TreeNode node){
        if(node == null)
            return;
        sb.append(node.val).append(",");
        if(node.left != null)
            preorder(sb, node.left);
        if(node.right != null)
            preorder(sb, node.right);

    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        data = data.substring(0, data.length() - 1);
        String[] tree = data.split(",");
        return helper(tree,0, tree.length - 1);
    }

    public TreeNode helper(String[] tree, int start, int end){
        if (start == end)
            return new TreeNode(Integer.valueOf(tree[start]));
        if(start < 0 || end >= tree.length || start > end)
            return null;
        TreeNode root = new TreeNode(Integer.valueOf(tree[start]));
        int index = start + 1;
        for(int i = start + 1 ; i <= end ; i ++){
            if(Integer.valueOf(tree[i]) > Integer.valueOf(tree[start])){
                index = i - 1;
                break;
            }
        }
        TreeNode left = helper(tree, start + 1, index);
        TreeNode right = helper(tree, index + 1, end);
        root.left = left;
        root.right = right;
        return root;
    }


    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(2);
        TreeNode t2 = new TreeNode(1);
//        TreeNode t3 = new TreeNode(3);
        t1.left = t2;
//        t1.right = t3;
        Djikstra djikstra = new Djikstra();

        djikstra.deserialize(djikstra.serialize(t1));

    }

}
