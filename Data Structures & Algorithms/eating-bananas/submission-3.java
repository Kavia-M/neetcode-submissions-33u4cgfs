class Solution {
    private long getH1(int[] piles, int k) {
        long h1 = 0L;
        for(int pile : piles) {
            h1 += Math.ceil((double)pile / k); // float divisiongives infinioty is k=0
        }
        System.out.println("h1 " + h1); // k = 0 means h1 = INT_MAX (infinity)
        return h1;
    }
    
    public int minEatingSpeed(int[] piles, int h) {
        /*
        let say we can complete the piles in h1 hrs
        h1 <= h
        we can say lower bound. max h1 that can be <= h
        but h1 and k are inversely proportional
        if k increases h1 decreases
        we need to find minimum k for max h1 <= h
        since our search space is for k, we do opposite to what we did in lower bound upper bound problem
        
        time taken for each pile is Math.ceil((double)piles[i]/k)
        
        if k is more than max(piles)
        range of k is 1 to max // eating 0 bananas we can never finish

        // Arrays.stream(nums).max().getAsInt();
        */
        
        int max = Arrays.stream(piles).max().orElse(0); // if empty array, we get 0
        int l = 1, r = max;  // l = 0 checked, works but not good, l=1 correct
        
        while(l<=r) {
            int k = l + (r - l) / 2;
            System.out.println(k);
            long h1 = getH1(piles, k);
            // h1 <= h
            if(h1 <= h) {
                // this is correct, see if larger h1 can be there
                // larger h1 means smaller k
                r = k - 1;
            }
            else {
                l = k + 1;
            }
        }
        // twist, we need to return l only as we see in upper bound problem
        return l;
    }
}



/*

class Solution {
    public int searchInsert(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while(l<=r) {
            int c = l + ((r-l) / 2);
            // we need to find l which,
            // nums[i] >= target (one such 1st one)
            // if it satisfied this, search if 1st one can be in left side
            // if not, clearly this window is not useful, move to right
            // we can dismiss the applicable mid, r = mid - 1, but eventually l = mid+1 will get it 
            //
            if(nums[c] >= target) {
                // go to left window
                r = c-1;
            }
            else {
                // dismiss this window and move right
                l = c+1;
            }
        }
        // return l as this is the start of the correct windwo nums[i] >= target
        return l;
    }
}
*/
