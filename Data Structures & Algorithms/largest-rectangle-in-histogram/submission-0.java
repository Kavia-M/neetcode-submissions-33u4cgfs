class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(left, -1);
        Arrays.fill(right, n);

        Deque<Integer> st_left = new ArrayDeque<>();
        Deque<Integer> st_right = new ArrayDeque<>();

        for(int i=0, j=n-1; i<n && j>=0; i++, j--) {
            // i is for right side min
            // j is for left side min
            if(st_right.isEmpty()) 
                st_right.push(i);
            
            if(st_left.isEmpty()) 
                st_left.push(j);

            while(!st_right.isEmpty() && heights[i] < heights[st_right.peek()]) {
                // current i is the nearest less than that of popped index bar
                int popped_index = st_right.pop();
                right[popped_index] = i;
            }
            st_right.push(i);

            while(!st_left.isEmpty() && heights[j] < heights[st_left.peek()]) {
                // current j is the nearest less than that of popped index bar
                int popped_index = st_left.pop();
                left[popped_index] = j;
            }
            st_left.push(j);
        }
        System.out.println(Arrays.toString(left));
        System.out.println(Arrays.toString(left));
        int area = 0;
        for(int k=0; k<n; k++) {
            int width = (right[k]-1) - (left[k]+1) + 1;
            area = Math.max(area, heights[k]*width);
        }
        return area;
    }
}

/*

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

*/
