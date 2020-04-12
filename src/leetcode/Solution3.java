package leetcode;

import java.util.*;

public class Solution3 {
    public static void main(String[] args) {
        int i = 1;
        List<Integer> list = Arrays.asList(-1,0);
        System.out.println(countPairs(2, list, -1));
    }



    public static int countPairs(int numCount, List<Integer> ratingValues, int target)
    {
        // WRITE YOUR CODE HERE
        if(ratingValues.isEmpty()){
            return 0;
        }
        Collections.sort(ratingValues);
        int left = 0;
        int right = numCount - 1;
        int count = 0;
        Set<Integer> set = new HashSet<>();
        while(left < right){
            if(set.contains(ratingValues.get(left))){
                left ++;
            } else if(set.contains(ratingValues.get(right))){
                right --;
            } else{
                int sum = ratingValues.get(left) + ratingValues.get(right);
                if (sum > target){
                    right --;
                } else if(sum < target){
                    left ++;
                } else{
                    count ++;
                    set.add(ratingValues.get(left));
                    set.add(ratingValues.get(right));
                    left ++;
                    right --;
                }
            }
        }
        return  count ++;
    }

//    List<PairInt> criticalConnections(int numOfServers, int numOfConnections,
//                                      List<PairInt> connections)
//    {
//        // WRITE YOUR CODE HERE
//        List<PairInt> res = new ArrayList<>();
//        for(int i = 0 ; i < numOfConnections ; i ++){
//            connections.remove(i);
//            if(bfs(numOfServers, connections)){
//                res.add(connections.get(i))
//
//            }
//        }
//    }
//
//    public boolean bfs (int number, List<PairInt> connections){
//        Set<Integer> set = new HashSet();
//        set.add(connections.indexOf(0));
//        for(PairInt c : connections){
//            if(set.contains(c.first) || set.contains.(c.second)){
//                set.add(c.first);
//                set.add(c.second);
//            }
//        }
//        if(set.size() == number){
//            return true;
//        }
//        return false;
//    }
}
