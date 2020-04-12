package leetcode;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
public class Codec {
    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            TreeNode cur = q.poll();
            if(cur != null){
                sb.append(cur.val).append(",");
                q.offer(cur.left);
                q.offer(cur.right);
            }else{
                sb.append("null").append(",");
            }
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        data = data.substring(0, data.length() - 1);
        String[] strs = data.split(",");
        TreeNode root = new TreeNode(Integer.valueOf(strs[0]));
        Queue<TreeNode> q = new ArrayDeque<TreeNode>();
        q.offer(root);
        int index = 1;
        while(!q.isEmpty()){
            TreeNode node = q.poll();

            String left = strs[index];
            index ++;
            String right = strs[index];
            index ++;

            if(!left.equals("null")){
                node.left = new TreeNode(Integer.valueOf(left));
                q.offer(node.left);
            }else{
                node.left = null;
            }

            if(!right.equals("null")){
                node.right = new TreeNode(Integer.valueOf(right));
                q.offer(node.right);
            }else{
                node.right = null;
            }
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(4);
        t1.left = t2;
        t1.right = t3;
        t3.left = t4;
        t3.right = t5;
        System.out.println(serialize(t1));
    }
}
