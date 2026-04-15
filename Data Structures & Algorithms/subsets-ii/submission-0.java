class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        addSubset(0, new ArrayList<>(), ans, nums);
        return ans;       
    }

    public void addSubset(int i, List<Integer> list, List<List<Integer>> ans, int[] nums) {
        if(i==nums.length) {
            ans.add(new ArrayList<>(list));
            return;
        }

        // not take it 
        // here we need to skip to the next element which is not equal to current element
        /*
        for(int j=i+1; j<nums.length; j++){
            if(nums[j] != nums[i]) {
                addCombinations(j, list, ans, nums, k);
                break;
            }
        }
        */

        // Optimise not take with binary search (upper bound and strictly > nums[i]) since the array is SORTED
        int l = i, r = nums.length-1; // inclusive
        while(l<=r) {
            int mid = l + (r-l)/2;

            if(nums[mid] > nums[i]) {
                // check if there exists another valid element on left side
                r = mid-1;
            }
            else {
                l = mid +1;
            }
        }
        addSubset(l, list, ans, nums);
        

        // take it
        list.add(nums[i]);
        addSubset(i+1, list, ans, nums);
        list.remove(list.size()-1);
    }

}


// It looks like Combination Sum II
// Solution seeing half of the Striver's video
class Solution_Combination_Sum_2 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        //your code goes here
        Arrays.sort(candidates);
        List<List<Integer>> ans = new ArrayList<>();
        addCombinations(0, new ArrayList<>(), ans, candidates, target);
        return ans;
    }

    public void addCombinations(int i, List<Integer> list, List<List<Integer>> ans, int[] nums, int k) {
        if(k<0) { // optimisation
            // here after it is not possible
            return;
        }
        if(k==0) {
            ans.add(new ArrayList<>(list));
            return;
        }
        if(i==nums.length) {
            return;
        }

        // not take it 
        // here we need to skip to the next element which is not equal to current element
        /*
        for(int j=i+1; j<nums.length; j++){
            if(nums[j] != nums[i]) {
                addCombinations(j, list, ans, nums, k);
                break;
            }
        }
        */

        // Optimise not take with binary search (upper bound and strictly > nums[i]) since the array is SORTED
        int l = i, r = nums.length-1; // inclusive
        while(l<=r) {
            int mid = l + (r-l)/2;

            if(nums[mid] > nums[i]) {
                // check if there exists another valid element on left side
                r = mid-1;
            }
            else {
                l = mid +1;
            }
        }
        addCombinations(l, list, ans, nums, k);
        

        // take it
        list.add(nums[i]);
        addCombinations(i+1, list, ans, nums, k-nums[i]);
        list.remove(list.size()-1);
    }
}
