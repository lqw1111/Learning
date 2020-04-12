import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.*;

class BSTIterator {

    Stack<TreeNode> stack = new Stack<>();

    public BSTIterator(TreeNode root) {
        while(root != null){
            stack.push(root);
            root = root.left;
        }
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode n = stack.pop();
        if(n.right != null)
            stack.push(n.right);
        return n.val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        if(stack.size() == 0)
            return false;
        else
            return true;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }

}

class LRUCache {

    class Node {

        int key;
        int val;
        Node pre;
        Node next;

        public Node(int key, int val){
            this.key = key;
            this.val = val;

        }
    }

    Node head = null;
    Node end = null;
    int capacity;
    HashMap<Integer, Node> map = new HashMap<>();

    public void removeNode(Node n){
        Node preN = n.pre;
        Node nextN = n.next;
        if (head == n) head = nextN;
        if (end == n) end = preN;
        if (preN != null) preN.next = nextN;
        if (nextN != null) nextN.pre = preN;
    }

    public void setHead(Node n){
        if (head == null && end == null){
            head = n;
            end = n;
        } else if (head == end){
            head = n;
            n.pre = null;
            n.next = end;
            end.pre = head;
        } else {
            n.next = head;
            n.pre = null;
            head.pre = n;;
            head = n;
        }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if(!map.containsKey(key))
            return -1;
        Node cur = map.get(key);
        removeNode(cur);
        setHead(cur);
        return cur.val;
    }

    public void put(int key, int value) {
        if (!map.containsKey(key)){
            if (map.size() >= capacity){
                map.remove(end.key);
                removeNode(end);
            }
            Node n = new Node(key, value);
            map.put(key, n);
            setHead(n);
        } else {
            Node cur = map.get(key);
            cur.val = value;
            removeNode(cur);
            setHead(cur);
        }
    }
}

public class test {
    public static void main(String[] args)
    {
        Stack<Integer> stack = new Stack<>();
        ListNode head = new ListNode(1);
//        ListNode head1 = new ListNode(2);
//        ListNode head2 = new ListNode(3);
//        ListNode head3 = new ListNode(4);
//        head.next = head1;
//        head1.next = head2;
//        head2.next = head3;

        reorderList(head);

    }

    public static void reorderList(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        if(fast.next != null){
            fast = fast.next;
        }

        ListNode cur = slow.next;
        slow.next = null;
        while(cur != fast){
            ListNode next = cur.next;
            cur.next = null;
            next.next = cur;
            cur = next;
        }


        ListNode node = head;
        while(node != null && fast != null){
            ListNode tmp1 = node.next;
            ListNode tmp2 = fast.next;
            node.next = fast;
            fast.next = tmp1;
            node = tmp1;
            fast = tmp2;
        }
        System.out.println(head);
    }

//    public static String serialize(TreeNode root) {
//        StringBuilder sb = new StringBuilder();
//
//        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
//        queue.add(root);
//
//        while(!queue.isEmpty()){
//            TreeNode t = queue.poll();
//
//            if(t == null){
//                sb.append("null,");
//            }else{
//                sb.append(t.val).append(",");
//                queue.add(t.left);
//                queue.add(t.right);
//            }
//        }
//
//        sb.deleteCharAt(sb.length()-1);
//        return sb.toString();
//    }

    // Decodes your encoded data to tree.
//    public static TreeNode deserialize(String data) {
//        if(data == "null" || data.length() == 0) return null;
//        String[] tree = data.split(",");
//        TreeNode root = new TreeNode(Integer.valueOf(tree[0]));
//
//        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
//        List<List<Integer>> res = new LinkedList<>();
//        ((LinkedList<List<Integer>>) res).removeFirst();
//        queue.add(root);
//
//        int i = 1;
//
//        while(!queue.isEmpty()) {
//            TreeNode n = queue.poll();
//
//            if(n == null) continue;
//
//            if(!tree[i].equals("null")){
//                TreeNode t = new TreeNode(Integer.valueOf(tree[i]));
//                n.left = t;
//                queue.offer(n.left);
//            }else{
//                n.left = null;
//                queue.offer(n.left);
//            }
//
//            i ++;
//
//            if(!tree[i].equals("null")){
//                TreeNode t = new TreeNode(Integer.valueOf(tree[i]));
//                n.right = t;
//                queue.offer(n.right);
//            }else{
//                n.right = null;
//                queue.offer(n.right);
//            }
//
//            i ++;
//        }
//
//        return root;
//    }

    public static int snakesAndLadders(int[][] board) {
        if(board == null || board.length == 0) return 0;
        int n = board.length;
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[n * n + 1];
        int min = n * n;

        q.offer(1);
        int moves = 0;

        while(!q.isEmpty()){
            int size = q.size();

            for(int i = 0 ; i < size ; i ++){
                int step = q.poll();
                if(step == n * n){
                    min = Math.min(min, moves);
                }

                for(int j = 1 ; j <= 6 ; j ++){
                    int num = step + j;
                    if(num > n * n) break;
                    if(num == step) continue;
                    int row = n - (num - 1) / n - 1;
                    int col = (n - row) % 2 != 0 ? (num - 1) % n : n - (num - 1) % n - 1;
                    if(!visited[num]){
                        visited[num] = true;

                        if(board[row][col] == -1){
                            q.offer(num);
                        }else{
                            q.offer(board[row][col]);
                        }

                    }
                }
            }

            moves ++;
        }

        return (min == n * n) ? -1 : min;
    }

    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer> list = new ArrayList<>();
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        int[] res = new int[numCourses];
        for(int i = 0 ; i < numCourses ; i ++ ){
            graph.add(new ArrayList<Integer>());
        }

        for(int[] tuple : prerequisites){
            int course = tuple[0];
            int preRequest = tuple[1];
            graph.get(course).add(preRequest);
        }

        int[] visited = new int[numCourses];

        for(int i = 0 ; i < numCourses ; i ++ ){
            if(findCycle(i, graph, visited, list)) {
                return res;
            }
        }

        for (int i = 0 ; i < list.size() ; i ++){
            res[i] = list.get(i);
        }

        return res;
    }

    public static boolean findCycle(int cur, ArrayList<ArrayList<Integer>> graph, int[] visited, List<Integer> list){
        if(visited[cur] == 1) return true;
        if(visited[cur] == 2){
            return false;
        }

        visited[cur] = 1;
        for(int neighbour : graph.get(cur)){
            if(findCycle(neighbour, graph, visited, list))
                return true;
        }

        visited[cur] = 2;
        list.add(cur);
        return false;
    }

    public static String[] reorderLogFiles(String[] logs) {
        String[] letArr = new String[3];
        String[] digArr = new String[2];
        int letLength = 0;
        int digLength = 0;

        if(logs.length == 0)
            return new String[logs.length];

        for(int i =0 ;i < logs.length ; i++){
            if(logs[i].startsWith("let")){
                letArr[letLength ++] = logs[i];
            }else{
                digArr[digLength ++] = logs[i];
            }
        }

        Arrays.sort(letArr, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                int index1 = s1.indexOf(" ");
                String id1 = s1.substring(0, index1);
                String letter1 = s1.substring(index1+1);

                int index2 = s2.indexOf(" ");
                String id2 = s2.substring(0, index2);
                String letter2 = s2.substring(index2+1);
                int v1 = letter1.compareTo(letter2);
                if(v1 != 0) return v1;
                int v2 = id1.compareTo(id2);
                return v2;
            }
        });

        for(int i = 0 ; i < digLength ; i ++){
            letArr[letLength ++ ] = digArr[i];
        }

        return letArr;
    }



    public static int sum = 0;
    public static int sumNumbers(TreeNode root) {
        if(root == null)
            return sum;
        helper(root, 0);
        return sum;
    }

    public static void helper(TreeNode root, int cur){
        if(root.left == null && root.right == null){
            sum += cur * 10 + root.val;
            return;
        }

        if(root.left != null){
            helper(root.left, cur * 10 + root.val);
        }
        if(root.right != null){
            helper(root.right, cur * 10 + root.val);
        }

    }


    public static void wallsAndGates(int[][] rooms) {
        if(rooms == null || rooms.length == 0)
            return;

        for(int i = 0 ; i < rooms.length ; i++){
            for(int j = 0 ; j < rooms[0].length ; j ++){
                if(rooms[i][j] == 0){
                    dfs(rooms, i , j, 0);
                }
            }
        }
    }

    private static void dfs(int[][] rooms, int i , int j, int dis){
        if(i < 0 || j < 0 || i >= rooms.length || j >= rooms[0].length)
            return;
        if (rooms[i][j] < dis || rooms[i][j] < 0)
            return;

        rooms[i][j] = dis ++;
        dfs(rooms, i + 1, j, dis);
        dfs(rooms, i - 1, j, dis);
        dfs(rooms, i, j - 1, dis);
        dfs(rooms, i, j + 1, dis);

    }

    public static int rob(int[] nums) {
        int[] dp = new int[nums.length];
        if(nums.length == 0)
            return 0;
        if(nums.length == 1)
            return nums[0];
        if(nums.length == 2)
            return nums[0] >= nums[1] ? nums[0] : nums[1];
        dp[0]=nums[0];
        dp[1]=Math.max(nums[0],nums[1]);
        for(int i = 2 ; i < nums.length; i ++){
            dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
        }
        return dp[nums.length - 1];
    }

    public static int minDistance(String word1, String word2) {
        int l1 = word1.length();
        int l2 = word2.length();
        int[][] dp = new int[l1 + 1][l2 + 1];

        for (int i = 0 ; i <= l1 ; i ++){
            dp[i][0] = i;
        }

        for (int j = 0 ; j <= l2 ; j ++){
            dp[0][j] = j;
        }

        for (int i = 1 ; i <= l1 ; i ++){
            for (int j = 1 ; j <= l2 ; j ++){
                if (word1.charAt(i - 1) == word2.charAt(j - 1))
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
                else
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + 1, Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
            }
        }

        return dp[word1.length()][word2.length()];

    }



    public static int minCost(int[][] costs) {
        int n = costs.length;
        if(costs != null && n == 0) return 0;
        int[][] dp = costs;

        for(int i = 1 ; i < n ; i++){
            dp[i][0] = costs[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
            dp[i][1] = costs[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]);
            dp[i][2] = costs[i][2] + Math.min(dp[i - 1][1], dp[i - 1][0]);
        }

        return Math.min(dp[n - 1][0], Math.min(dp[n - 1][1], dp[n - 1][2]));
    }

    public int maxCoins(int[] nums) {
        int size = nums.length;
        int[] number = new int[size + 2];
        number[0] = 1;
        number[size + 1 ] = 1;
        for(int i  = 0 ; i < nums.length ; i ++){
            number[i + 1] = nums[i];
        }
        int[][] dp = new int[size + 2][size + 2];
        for (int l = 1 ; l <= size ; l ++){
            for (int i = 1 ; i <= size - l + 1 ; i ++){
                int j = i + l - 1;
                for (int k = i ; k <= j ; k ++){
                    dp[i][j] = Math.max(dp[i][j], dp[i][k - 1] + number[i - 1] * number[k] * number[j + 1] + dp[k + 1][j]);
                }
            }
        }
        return dp[1][size];
    }

    public static int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) return 1;
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) {
                int temp = nums[i];
                nums[i] = nums[nums[i] - 1];
                nums[nums[i] - 1] = temp;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }

    public static String getHint(String secret, String guess) {
        int[] table = new int[10];

        int total = 0;
        int bulls = 0;
        for (char c : secret.toCharArray()) {
            table[c - '0']++;
        }

        for (int i = 0; i < guess.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                bulls++;
            }
            if (table[guess.charAt(i) - '0']-- > 0) {
                total++;
            }
        }

        return bulls + "A" + (total - bulls) + "B";
    }

    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Integer> set = new TreeSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            Integer ceiling = set.ceiling(nums[i]);
            if (ceiling != null && ceiling <= t + nums[i]) return true;

            Integer floor = set.floor(nums[i]);
            if (floor != null && nums[i] <= t + floor) return true;

            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }

    public static String reverseWords(String s) {
        s = s.trim();
        String[] str = s.split(" ");
        if (str.length == 1 || str.length == 0) return s;

        String a = "";
        for (int i = str.length - 1; i >= 0; i--) {
            str[i] = str[i].trim();
            a = a + " " + str[i];
        }
        return a.trim();
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int len = nums1.length + nums2.length;
        int cut1 = 0;
        int cut2 = 0;
        int cutL = 0;
        int cutR = nums1.length;
        while (cut1 <= nums1.length) {
            cut1 = (cutR - cutL) / 2 + cutL;
            cut2 = len / 2 - cut1;
            double L1 = (cut1 == 0) ? Integer.MIN_VALUE : nums1[cut1 - 1];
            double L2 = (cut2 == 0) ? Integer.MIN_VALUE : nums2[cut2 - 1];
            double R1 = (cut1 == nums1.length) ? Integer.MAX_VALUE : nums1[cut1];
            double R2 = (cut2 == nums1.length) ? Integer.MAX_VALUE : nums2[cut2];
            if (L1 > R2) {
                cutR = cut1 - 1;
            } else if (L2 > R1) {
                cutL = cut1 + 1;
            } else {
                if (len % 2 == 0) {
                    L1 = L1 > L2 ? L1 : L2;
                    R1 = R1 < R2 ? R1 : R2;
                    return (L1 + R1) / 2;
                } else {
                    R1 = (R1 < R2) ? R1 : R2;
                    return R1;
                }
            }
        }
        return -1;
    }

    public static String multiply(String num1, String num2) {
        if (num1 == null || num2 == null || num1 == "0" || num2 == "0") {
            return "0";
        }
        int[] dig = new int[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >= 0; i--) {
            for (int j = num2.length() - 1; j >= 0; j--) {
                int product = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int p1 = i + j;
                int p2 = i + j + 1;
                int sum = product + dig[p2];
                dig[p1] += sum / 10;
                dig[p2] = sum % 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int d : dig) {
            if (!(d == 0 && sb.length() == 0)) {
                sb.append(d);
            }
        }
        return sb.toString();
    }

    public static boolean isIsomorphic(String s1, String s2) {
        int[] m = new int[512];
        for (int i = 0; i < s1.length(); i++) {
            if (m[s1.charAt(i)] != m[s2.charAt(i) + 256]) return false;
            m[s1.charAt(i)] = m[s2.charAt(i) + 256] = i + 1;
        }
        return true;
    }

    public static boolean wordPattern(String pattern, String str) {
        String[] strings = str.split(" ");
        if (strings.length != pattern.length()) return false;
        HashMap<Character, String> map = new HashMap<>();

        for (int i = 0; i < pattern.length(); i++) {
            if (!map.containsKey(pattern.charAt(i)) && !map.containsValue(strings[i])) {
                map.put(pattern.charAt(i), strings[i]);
            } else {
                if (!strings[i].equals(map.get(pattern.charAt(i)))) {
                    return false;
                }
            }
        }
        return true;
    }

    public static List<List<String>> groupStrings(String[] strings) {
        List<List<String>> result = new ArrayList<List<String>>();
        HashMap<String, ArrayList<String>> map
                = new HashMap<String, ArrayList<String>>();

        for (String s : strings) {
            char[] arr = s.toCharArray();
            if (arr.length > 0) {
                int diff = arr[0] - 'a';
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i] - diff < 'a') {
                        arr[i] = (char) (arr[i] - diff + 26);
                    } else {
                        arr[i] = (char) (arr[i] - diff);
                    }

                }
            }

            String ns = new String(arr);
            if (map.containsKey(ns)) {
                map.get(ns).add(s);
            } else {
                ArrayList<String> al = new ArrayList<String>();
                al.add(s);
                map.put(ns, al);
            }
        }

        for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
            Collections.sort(entry.getValue());
        }

        result.addAll(map.values());

        return result;
    }

    public static String convert(String s, int numRows) {
        if (numRows == 0 || numRows == 1) return s;
        StringBuilder[] sb = new StringBuilder[numRows];
        for (int i = 0; i < sb.length; i++) {
            sb[i] = new StringBuilder("");
        }
        for (int i = 0; i < s.length(); i++) {
            int index = i % (2 * numRows - 2);
            index = index < numRows ? index : 2 * numRows - 2 - index;
            sb[index].append(s.charAt(i));
        }

        for (int i = 1; i < sb.length; i++) {
            sb[0].append(sb[i]);
        }

        return sb[0].toString();
    }

    public static boolean isOneEditDistance(String s, String t) {

        int m = s.length();
        int n = t.length();
        int count = 0;

        if (Math.abs(m - n) > 1) return false;

        int i = 0;
        int j = 0;

        while (i < m && j < n) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            } else {
                count++;
                if (count > 1) return false;

                if (m > n) {
                    i++;
                } else if (m < n) {
                    j++;
                } else {
                    i++;
                    j++;
                }
            }
        }

        if (i < m || j < n) {
            count++;
        }

        if (count == 1)
            return true;

        return false;
    }

//    public int minPrice(int[] prices) {
//        int price = 0;
//        boolean existProduct = true;
//        Arrays.sort(prices);
//
//        while (existProduct) {
//            int count = 0;
//            for (int i = 0 ; i < prices.length ; i ++){
//                if (prices[i] > 0) {
//                    prices[i] --;
//                    count ++;
//                }
//            }
//            if (count == 0) {
//                existProduct = false;
//            } else {
//                price =
//            }
//        }
//
//    }




}
