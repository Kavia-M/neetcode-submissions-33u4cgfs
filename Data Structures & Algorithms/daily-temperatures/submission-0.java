class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        System.out.println(n);
        Deque<Integer> st = new ArrayDeque<>();
        int[] ans = new int[n];
        for(int i =0; i<n; i++) {
            // pop only if new element is greater than top 
            // keep index
            // if empty also push
            if(st.isEmpty()) 
                st.push(i);


            while(!st.isEmpty() && temperatures[i] > temperatures[st.peek()]) {
                int popped_index = st.pop();
                ans[popped_index] = i - popped_index;
            }

            st.push(i);
        }

        // remianing in stack answer is 0
        while(!st.isEmpty()) {
            ans[st.pop()] = 0;
        }
        return ans;
    }
}
