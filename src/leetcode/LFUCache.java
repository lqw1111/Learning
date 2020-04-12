package leetcode;

import java.util.HashMap;
import java.util.Map;

class LFUCache {
    class Node{
        int key;
        int val;
        int cnt;
        Node next;
        Node pre;
        public Node(int key, int val){
            this.key = key;
            this.val = val;
            cnt = 1;
        }

    }

    class DLList{
        Node head;
        Node tail;
        int len;
        public DLList(){
            head = new Node(0,0);
            tail = new Node(0,0);
            head.next = tail;
            tail.pre = head;
            len = 0;
        }

        public void addHead(Node node){
            Node next = head.next;
            node.next = next;
            next.pre = node;
            head.next = node;
            node.pre = head;
            map.put(node.key, node);
            len ++;
        }

        public void remove(Node node){
            Node pre = node.pre;
            Node next = node.next;
            pre.next = next;
            next.pre = pre;
            map.remove(node.key);
            len --;
        }

        public void removeTail(){
            Node node = tail.pre;
            remove(node);
        }
    }

    Map<Integer, DLList> freq = new HashMap<Integer, DLList>();
    Map<Integer, Node> map = new HashMap<Integer, Node>();
    int capacity;
    int maxFreq;
    int size;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.maxFreq = 0;
        this.size = 0;
    }

    public int get(int key) {
        if(map.get(key) == null)
            return -1;
        Node node = map.get(key);
        int preCnt = node.cnt;
        DLList preList = freq.get(preCnt);

        int curCnt = preCnt + 1;
        DLList curList = freq.getOrDefault(curCnt, new DLList());

        preList.remove(node);
        curList.addHead(node);
        node.cnt ++;
        maxFreq = Math.max(curCnt, maxFreq);
        freq.put(preCnt, preList);
        freq.put(curCnt, curList);

        return node.val;
    }

    public void put(int key, int value) {
        if(map.get(key) != null){
            Node node = map.get(key);
            node.val = value;
            map.put(key, node);
            get(key);
            return;
        }
        Node node = new Node(key, value);
        DLList curList = freq.getOrDefault(1, new DLList());
        curList.addHead(node);
        this.size ++;
        if(size > capacity){
            if(curList.len > 1){
                curList.removeTail();
            }else{
                for(int i = 2 ; i <= maxFreq ; i ++){
                    if(freq.get(i).len > 0){
                        freq.get(i).removeTail();
                        break;
                    }
                }
            }
            this.size --;
        }
        freq.put(node.cnt, curList);
    }

    public static void main(String[] args) {
        LFUCache lfuCache = new LFUCache(2);
        lfuCache.put(1,1);
        lfuCache.put(2,2);
        lfuCache.get(1);
        lfuCache.put(3,3);
        System.out.println(lfuCache.get(2));
        System.out.println(lfuCache.get(3));
        lfuCache.put(4,4);
        System.out.println(lfuCache.get(1));
        System.out.println(lfuCache.get(3));
        System.out.println(lfuCache.get(4));
        String s = "a";
    }
}




