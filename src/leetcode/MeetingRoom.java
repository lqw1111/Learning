package leetcode;

import java.util.*;


public class MeetingRoom {

    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, (p1, p2) -> {
            if(p1[0] > p2[0])
                return 1;
            else if (p1[0] < p2[0])
                return -1;
            else
                return 0;
        });
        PriorityQueue<Integer> q = new PriorityQueue<>();
        q.offer(intervals[0][1]);

        for (int i = 1 ; i < intervals.length ; i ++){
            int minEndTime = q.poll();
            if(minEndTime < intervals[i][0]){
                q.offer(intervals[i][1]);
            } else {
                q.offer(intervals[i][1]);
                q.offer(minEndTime);
            }
        }
        return q.size();
    }

    private static boolean isPrime(int y){
        if(y < 2 && y % 2 == 0)
            return y == 2;
        for(int i = 3 ; i * i <= y ; i += 2){
            if(y % i == 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPrime(6));
        LinkedList<String> list = new LinkedList<>();
        TreeSet<String> treeSet = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return 0;
            }
        });
        HashMap<String,String> map = new HashMap<>();
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.removeLast();
    }
}
