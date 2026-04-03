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

            /*

            4 possibilites
            1. left side is sorted and contains target
            2. ledt side is  sorted and not contains target
            3. left side is not sorted and contains target
            4. left side is not sorted and not contains target

            1 and 3 move to left window, for 4 also we can move to left window 
            eventially 4 will return -1
            */

            if(nums[l] <= target && target < nums[c]) {
                // nums[l] < nums[c]
                // left side including c is sorted
                // we need not include c since we already checked it
                // check nums[l] <= target <= nums[c]
                
                // move to left window
                // this means left side is sorted and has this number
                r = c - 1;
            }
            else if(nums[c] < target && target <= nums[r]) {
                // right side is sorted and contains target
                l = c + 1;
            }
            // else unsorted part will have the target
            else if(nums[l] < nums[c]){
                // just sorted but not containing
                l = c+1;
            }
            else {
                r=c-1;
            }

        }
        return -1;
    }
}
