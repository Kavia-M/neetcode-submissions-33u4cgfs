class Solution_my_own {
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

            // if difference is less means that side is sorted
            // the other side has the max value making the difference high
            // we are trying to find max value so the next value is min value
            if(Math.abs(nums[l]-nums[c]) > Math.abs(nums[r]-nums[c])) {
                // move left
                // in case of equal, the maximum will be in right side only else part
                r = c -1;
            }
            else {
                l = c + 1;
            }

        }
        return nums[0];
    }
}


