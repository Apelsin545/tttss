package codingpractice;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        MinStack stack = new MinStack();

        stack.push(-2);
        stack.push(0);
        stack.push(-3);

        System.out.println(stack.getMin());


    }

    public static boolean isValid(String s) {
        Stack<Character> bracketsStack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char sym = s.charAt(i);

            if (sym == '{' || sym == '[' || sym == '(') bracketsStack.push(sym);
            else {
                if (bracketsStack.size() == 0) return false;
                if (sym == ')') sym = '(';
                if (sym == '}') sym = '{';
                if (sym == ']') sym = '[';
                if (bracketsStack.pop() != sym) return false;
            }
        }

        if (bracketsStack.size() > 0) return false;
        return true;
    }
}
