class Solution {
    public int search(int[] nums, int target) {
        // we use the previous strategy 
        // to find which side of array is sorted
        // if the target lies in sorted part go there

        int n = nums.length;
        int l = 0, r = n-1;
        while(l<=r) {
            int c = l + (r-l) / 2;
            System.out.println(c);
            if(nums[c] ==  target) {
                return c;
            }
            if(nums[l] == target)
                return l;
            if(nums[r] == target)
                return r;
            if(nums[l] <= target && target < nums[c]) {
                // nums[l] < nums[c]
                // left side including c is sorted
                // we need not include c since we already checked it
                // check nums[l] <= target <= nums[c]
                
                // move to left window
                r = c - 1;
            }
            else 
                l = c+1;
        }
        return -1;
    }
}
