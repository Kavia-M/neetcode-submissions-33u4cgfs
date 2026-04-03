class MinStack_using_Pair {
    private int min_value;
    private class Pair {
        private int value;
        private int minSoFar;
        Pair(int value, int minSoFar) {
            this.value = value;
            this.minSoFar = minSoFar;
        }
        // only getter
        int getValue() {
            return this.value;
        }
        int getMinSoFar() {
            return this.minSoFar;
        }
    };
    private final int int_max = Integer.MAX_VALUE;
    Deque<Pair> st;
    public MinStack_using_Pair() {
        st = new ArrayDeque<>();
    }
    
    public void push(int val) {
        int minTillLast = st.isEmpty() ? int_max : st.peek().getMinSoFar();
        st.push(new Pair(val, Math.min(val, minTillLast)));
    }
    
    public void pop() {
        st.pop();
    }
    
    public int top() {
        return st.peek().getValue();
    }
    
    public int getMin() {
        return st.peek().getMinSoFar();
    }
}

class MinStack {
    private int min;

    Stack<Integer> st;
    public MinStack() {
        st = new Stack<>();
    }
    
    public void push(int val) {
        if(st.isEmpty()) {
            st.push(0);
            min = val;
        }
        else {
            st.push(val - min);
            // Math.min(min, val)
            if(val < min) min = val;
        }
    }
    
    public void pop() {
        if(!st.isEmpty()) {
            // if empty, do not pop . pop on empty returns null
            int pop = st.pop();

            // update min. only if popped one is -ve
            if(pop < 0) min = min - pop; // here we get old min
        }
        
    }
    
    public int top() { // given that it will be called on non empty
        int top = st.peek();
        if(top > 0) return top + min;
        else return min;
    }
    
    public int getMin() {
        return min;
    }
}

