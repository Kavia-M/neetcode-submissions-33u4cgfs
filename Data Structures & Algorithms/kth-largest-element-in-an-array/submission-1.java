class Solution_My_Solution {
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

// GIVEN SOLUTION 1 - SORTING
class Solution {
    public int findKthLargest(int[] nums, int k) {
        // 1st largest is last element in asc order which index is n-1
        // n-2 is 2nd largest
        // n-k is kth largest

        Arrays.sort(nums);
        
        // this is not needed as per given question, but for safety check
        if(k>nums.length) {
            // return 1st element
            if(nums.length>0) return nums[0];
            else return 0;
        }

        // this is default and correct return stmt
        return nums[nums.length - k];
    }
}
