package codingpractice;

class MinStack {
    private int[] stack;
    private int[] stackMin;

    public MinStack() {
        stack = new int[]{};
    }

    public void push(int val) {
        int[] newStack = new int[stack.length + 1];

        if (stack.length == 0) {
            stackMin = new int[]{val};
        } else {
            int[] tempStack = new int[stack.length + 1];

            for (int i = 0; i < stack.length; i++) {
                tempStack[i] = stackMin[i];
            }
            tempStack[stack.length] = Integer.min(val, tempStack[stack.length - 1]);

            stackMin = tempStack;
        }

        for (int i = 0; i < stack.length; i++) {
            newStack[i] = stack[i];
        }

        newStack[stack.length] = val;
        stack = newStack;
    }

    public void pop() {
        int[] newStack = new int[stack.length - 1];

        for (int i = 0; i < stack.length - 1; i++) {
            newStack[i] = stack[i];
        }

        stack = newStack;
    }

    public int top() {
        return stack[stack.length - 1];
    }

    public int getMin() {
        return stackMin[stack.length - 1];
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */