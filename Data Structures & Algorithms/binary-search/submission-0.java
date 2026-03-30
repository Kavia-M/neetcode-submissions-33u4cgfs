class Solution {
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while(l<=r) {
            int c = (l+r) / 2;
            if(nums[c] == target) {
                return c;
            }
            else if(nums[c] > target) {
                // target is smaller
                r = c-1;
            }
            else {
                // target is larger
                l = c+1;
            }
        }
        return -1;
    }
}
