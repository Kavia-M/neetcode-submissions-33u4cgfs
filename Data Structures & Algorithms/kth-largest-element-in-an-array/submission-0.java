class Solution {
    public int findKthLargest(int[] nums, int k) {
        // kth largest means minheap
        PriorityQueue pq = new PriorityQueue<Integer>();
        for(int num : nums) {
            pq.offer(num);
            if(pq.size() > k)
                pq.poll();
        }
        // k largest elements are here as more than k, we remove the smallest elements
        // kth largest is the MINIMUM of k largest elements
        return (int) pq.peek();
    }
}
