class Solution {
    public int findMin(int[] nums) {
        int n = nums.length;
        int l = 0, r = n-1;
        while(l<=r) {
            int c = l + (r - l) / 2;
            System.out.println(c);
            int next = nums[(c+1)%n];
            if(next < nums[c]) {
                return next;
            }

            if(Math.abs(nums[l]-nums[c]) > Math.abs(nums[r]-nums[c])) {
                // move left
                r = c -1;
            }
            else {
                l = c + 1;
            }

        }
        return nums[0];
    }
}
