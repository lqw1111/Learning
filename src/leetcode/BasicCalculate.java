package leetcode;

import java.util.*;

public class BasicCalculate {

    public class TreeNode {
        int val;
        TreeNode left;

        TreeNode right;

        TreeNode(int x) { val = x; }

    }
    public static int calculate(String s) {
        if (s == null || s.length() == 0) return 0;
        Stack<Integer> nums = new Stack<>(); // the stack that stores numbers
        Stack<Character> ops = new Stack<>(); // the stack that stores operators (including parentheses)
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') continue;
            if (Character.isDigit(c)) {
                num = c - '0';
                // iteratively calculate each number
                while (i < s.length() - 1 && Character.isDigit(s.charAt(i+1))) {
                    num = num * 10 + (s.charAt(i+1) - '0');
                    i++;
                }
                nums.push(num);
                num = 0; // reset the number to 0 before next calculation
            } else if (c == '(') {
                ops.push(c);
            } else if (c == ')') {
                // do the math when we encounter a ')' until '('
                while (ops.peek() != '(') nums.push(operation(ops.pop(), nums.pop(), nums.pop()));
                ops.pop(); // get rid of '(' in the ops stack
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (!ops.isEmpty() && precedence(c, ops.peek())) nums.push(operation(ops.pop(), nums.pop(),nums.pop()));
                ops.push(c);
            }
        }
        while (!ops.isEmpty()) {
            nums.push(operation(ops.pop(), nums.pop(), nums.pop()));
        }
        return nums.pop();
    }

    private static int operation(char op, int b, int a) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b; // assume b is not 0
        }
        return 0;
    }
    // helper function to check precedence of current operator and the uppermost operator in the ops stack
    private static boolean precedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) return false;
        return true;
    }
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

    public int maximumMinimumPath(int[][] A) {
        int min = Integer.MAX_VALUE;
        if (A == null || A.length == 0)
            return 0;
        PriorityQueue<Node> q = new PriorityQueue<Node>((n1,n2) -> {
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
                q.offer(new Node(row - 1, col, A[row + 1][col]));
            }
            if (row < A.length && col - 1 >= 0 && visited[row][col - 1] != 1){
                q.offer(new Node(row, col - 1, A[row][col - 1]));
            }
        }
        return min;
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        for(String str : strs){
            char[] c = str.toCharArray();
            Arrays.sort(c);
            String sortedString = String.valueOf(c);
            if (map.containsKey(sortedString))
                map.get(sortedString).add(str);
            else{
                ArrayList<String> temp = new ArrayList<>();
                temp.add(str);
                map.put(sortedString, temp);
            }
        }
        for(String key : map.keySet()){
            res.add(map.get(key));
        }
        return res;
    }

    public static int uniquePathsIII(int[][] grid) {
        int x = -1;
        int y = -1;
        int n = 0;
        for(int i = 0 ; i < grid.length ; i ++){
            for(int j = 0 ; j < grid[0].length ; j ++){
                if(grid[i][j] == 0)
                    n ++;
                if(grid[i][j] == 1){
                    x = i;
                    y = j;
                }
            }
        }
        return dfs(grid, x, y, n + 1);
    }

    private static int dfs(int[][] grid, int i, int j, int n){
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == -1)
            return 0;
        if(grid[i][j] == 2 && n == 0)
            return 1;
        else if(grid[i][j] == 2 && n != 0)
            return 0;

        grid[i][j] = -1;
        int path = dfs(grid, i + 1 , j , n - 1) +
                dfs(grid, i - 1 , j , n -1) +
                dfs(grid, i , j + 1, n - 1) +
                dfs(grid, i , j - 1, n - 1);
        grid[i][j] = 0;
        return path;
    }

    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        List<List<String>> sessions = new ArrayList<>();
        for(int i = 0 ; i < username.length ; i++){
            List<String> temp = new ArrayList<String>();
            temp.add(username[i]);
            temp.add("" + timestamp[i]);
            temp.add(website[i]);
            sessions.add(temp);
        }
        Collections.sort(sessions, (s1, s2) -> {
            return Integer.valueOf(s1.get(1)) - Integer.valueOf(s2.get(1));
        });

        HashMap<String, ArrayList<String>> visited = new HashMap<String, ArrayList<String>>();
        for(List<String> session : sessions){
            visited.putIfAbsent(session.get(0), new ArrayList<>());
            visited.get(session.get(0)).add(session.get(2));
        }
        int max = 0;
        String maxSeq = "";
        HashMap<String, Integer> map = new HashMap<>();
        for (String name : visited.keySet()){
            List<String> websiteList = visited.get(name);
            if (websiteList.size() < 3)
                continue;
            HashSet<String> threeSeq = getSeq(websiteList);
            for (String seq : threeSeq){
                map.put(seq, map.getOrDefault(seq, 0)+1);
                if (map.get(seq) > max){
                    max = map.get(seq);
                    maxSeq = seq;
                } else if (map.get(seq) == max && seq.compareTo(maxSeq) < 0){
                    maxSeq = seq;
                }
            }
        }
        List<String> res = new ArrayList<>();
        for(String s : maxSeq.split(",")){
            res.add(s);
        }
        return res;
    }

    public HashSet<String> getSeq(List<String> list){
        int n = list.size();
        HashSet<String> res = new HashSet<>();
        for (int i = 0; i < n-2; i++){
            for (int j = i+1; j < n-1; j++){
                for (int k = j+1; k < n; k++){
                    res.add(list.get(i)+","+list.get(j)+","+list.get(k));
                }
            }
        }
        return res;
    }

    int length;
    public int longestUnivaluePath(TreeNode root) {
        int length = 0;
        dfs(root);
        return length;
    }

    private int dfs(TreeNode root){
        if(root == null)
            return 0;
        int left = dfs(root.left);
        int right = dfs(root.right);
        int pl = 0;
        int pr = 0;
        if(root.left != null && root.val == root.left.val)
            pl = left + 1;
        if(root.right != null && root.val == root.right.val)
            pr = right + 1;
        length = Math.max(length, pl + pr);
        return Math.max(pl, pr);
    }

    public static String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (log1, log2) -> {
            String[] split1 = log1.split(" ",2);
            String[] split2 = log2.split(" ",2);
            boolean isCharacter1 = Character.isDigit(split1[1].charAt(0));
            boolean isCharacter2 = Character.isDigit(split2[1].charAt(0));
            if(!isCharacter1 && !isCharacter2){
                int tem = split1[1].compareTo(split2[1]);
                if(tem != 0)
                    return tem;
                return split1[0].compareTo(split2[0]);
            }
            return isCharacter1 ? (isCharacter2 ? 0 : 1) : -1;
        });
        return logs;
    }



    public static void main(String[] args) {
        String[] a = {"dig1 1 2","let1 a bb"};
        reorderLogFiles(a);
        int[][] arr = {{1,0,0,0},{0,0,0,0},{0,0,2,-1}};
        System.out.println(uniquePathsIII(arr));
    }
}
