package leetcode;

import java.util.Stack;

public class Calculatro {
    public int calculate(String s) {
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        char sign = '+';
        int num = 0;

        for(int i = 0 ; i < s.length() ; i ++) {
            char c = s.charAt(i);
            if(Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }
            if (!Character.isDigit(c) && ' ' != c || i == s.length() - 1){
                if (sign == '-') {
                    stack.push(-num);
                }
                if (sign == '+') {
                    stack.push(num);
                }
                if (sign == '*') {
                    stack.push(num * stack.pop());
                }
                if (sign == '/') {
                    stack.push(stack.pop() / num);
                }
                sign = c;
                num = 0;
            }
        }
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }
}
