// Inspired from last question solution, but convert while loop into recursion
// loop breaking condition 
// here we fix the kth element, when fixed the kth element
// we need to fix the kth element in reverse order 
// >= we need to use
public class Solution_Quick_Search_My_variant {
    private int[] nums;
    private int k;
    public int quick_search(int l, int r) {
        // NOTE : quick_search returns the pivot index
        int pivot = partition(l, r);

        // System.out.println(Arrays.toString(nums));
        // System.out.println(l + " " + r + " " + pivot);

        if(pivot == k) return pivot;

        if(pivot < k) {
            // pivot here gives the pivot place as pivot th largest element, if pivot < k we need more larger elements
            return quick_search(pivot + 1, r);
        }
        else { // the number of greater elements we find is more, we need exactly k, so we go to left side of current window and search
            return quick_search(l, pivot-1);
        }
    }
    public int findKthLargest(int[] nums, int k) {
        this.nums = nums;
        this.k = k-1; // since we do 0 based index
        int L = 0, R = nums.length - 1; // both L and R are inclusive
        // // since no element is fixed in current position now we make pivot = nums.length. this is not valid index now. 
        // // NOTE : if k==nums.lenght in given question this will work
        // int pivot = nums.length; 

        // pivot means pivot's element is fixed
        // if kth element is fixed we will have k largest elements (inclusing kth element) in starting
        // pivot is number of fixed elements
        
        // ONE SAFETY CHECK though not needed 
        if(k > nums.length) {
            // this uses the k given in question, not this.k
            if(nums.length!=0) return nums[0];
            else return 0;
        }

        int pivot = quick_search(L, R);
        return nums[pivot];
    }

    private int partition(int l, int r) {
        // THIS FUNCTION FIXES LAST ELEMENT IN GIVEN RANGE INTO IT's CORRECT POSITION
        int pivotIdx = r;
        int pivotDist = nums[pivotIdx]; // to be pivoted is the last element
        int i = l; // i is the index for larger elements that we will find inside array and swap it to position i
        for (int j = l; j < r; j++) { // we compare all elements before the last element
            if (nums[j] >= pivotDist) { // if the jth element >= last element, swap it with the index for larger elements
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++; // increment i so the next larger element goes to new incremented i. This is incremented one more time in the last before loop ends
            }
        }
        // since i is already incremented as we seen above, this is correct spot where we can place the LAST element (pivoted element)
        int temp = nums[i];
        nums[i] = nums[r];
        nums[r] = temp;
        return i; // this i is the position of PIVOTTED element
    }
}

// GIVEN SOLUTION 3. Quick Select
// From my solution, the partition can be fit into the quick_search itself
// we can also change it to asc order and take the n-k th element, if 1 based we do n-k+1. since it is 0 based index n-k is correct
public class Solution {
    private int[] nums;
    private int k;
    // MAKE QUICK SELECT return the element itself rather than index
    public int quick_search(int l, int r) {

        int pivotIdx = r;
        int pivotDist = nums[pivotIdx];
        int i = l;
        for (int j = l; j < r; j++) { 
            if (nums[j] <= pivotDist) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
            }
        }
    
        int temp = nums[i];
        nums[i] = nums[r];
        nums[r] = temp;
        // this i is the pivot
        
        // just for better readability
        int pivot = i;

        if(pivot == k) return nums[pivot];

        if(pivot < k) {
            return quick_search(pivot + 1, r);
        }
        else {
            return quick_search(l, pivot-1);
        }
    }
    public int findKthLargest(int[] nums, int k) {
        this.nums = nums;
        this.k = nums.length - k; // since we do 0 based index
        int L = 0, R = nums.length - 1; // both L and R are inclusive
        // // since no element is fixed in current position now we make pivot = nums.length. this is not valid index now. 
        // // NOTE : if k==nums.lenght in given question this will work
        // int pivot = nums.length; 

        // pivot means pivot's element is fixed
        // if kth element is fixed we will have k largest elements (inclusing kth element) in starting
        // pivot is number of fixed elements
        
        // ONE SAFETY CHECK though not needed 
        if(k > nums.length) {
            // this uses the k given in question, not this.k
            if(nums.length!=0) return nums[0];
            else return 0;
        }

        return quick_search(L, R);
    }
}


