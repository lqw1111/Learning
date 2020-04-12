package leetcode;

import java.util.TreeMap;

class MyCalendar {

    TreeMap<Integer, Integer> map = new TreeMap<Integer,Integer>();

    public MyCalendar() {

    }

    public boolean book(int start, int end) {
        if(map.isEmpty()){
            map.put(start, end);
            return true;
        }
        Integer start1 = map.ceilingKey(start) == null ? Integer.MAX_VALUE : map.ceilingKey(start);
        Integer end2 = map.floorKey(start) == null ? Integer.MIN_VALUE : map.get(map.floorKey(start));
        if(start1 >= end && end2 <= start){
            map.put(start, end);
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        MyCalendar myCalendar = new MyCalendar();
        myCalendar.book(10,20);
        myCalendar.book(15,25);
        myCalendar.book(20,30);
    }
}
