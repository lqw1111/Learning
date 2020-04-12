package leetcode;

import java.util.*;

public class DinnerPlates {

    int capacity;
    TreeSet<Integer> available;
    List<Stack<Integer>> stacks;

    public DinnerPlates(int capacity) {
        this.capacity = capacity;
        available = new TreeSet<Integer>();
        stacks = new ArrayList<Stack<Integer>>();
    }

    public void push(int val) {
        if(available.size() == 0){
            stacks.add(new Stack<Integer>());
            available.add(stacks.size() - 1);
        }
        stacks.get(available.first()).push(val);
        if(stacks.get(available.first()).size() >= capacity)
            available.pollFirst();
    }

    public int pop() {
        return popAtStack(stacks.size() - 1);
    }

    public int popAtStack(int index) {
        if (index < 0 || index >= stacks.size() || stacks.get(index).size() == 0)
            return -1;
        int val = stacks.get(index).pop();
        while(!stacks.isEmpty() && stacks.get(stacks.size() - 1).size() == 0){
            int last = stacks.size() - 1;
            stacks.remove(last);
            available.remove(last);
        }
        return val;
    }


}

