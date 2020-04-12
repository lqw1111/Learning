package leetcode;

import java.util.Stack;

public class MaxStack {

    Stack<Integer> stack = new Stack<Integer>();
    Stack<Integer> maxStack = new Stack<Integer>();

    /** initialize your data structure here. */
    public MaxStack() {

    }

    public void push(int x) {
        if(maxStack.isEmpty() || maxStack.peek() <= x)
            maxStack.push(x);
        stack.push(x);
    }

    public int pop() {
        if(maxStack != null && maxStack.peek() == stack.peek())
            maxStack.pop();
        return stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int peekMax() {
        return maxStack.peek();
    }

    public int popMax() {
        Stack<Integer> temp = new Stack<Integer>();
        int max = maxStack.peek();
        while(stack.peek() != max){
            temp.push(stack.pop());
        }
        stack.pop();
        maxStack.pop();
        while(!temp.isEmpty()){
            stack.push(temp.pop());
        }
        return max;
    }

    public static void main(String[] args) {
        MaxStack maxStack = new MaxStack();
        maxStack.push(5);
        maxStack.push(1);
        maxStack.popMax();
        maxStack.peekMax();
    }
}