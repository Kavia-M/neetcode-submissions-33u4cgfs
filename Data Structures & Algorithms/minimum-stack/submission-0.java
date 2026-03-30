class MinStack {
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
    public MinStack() {
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
