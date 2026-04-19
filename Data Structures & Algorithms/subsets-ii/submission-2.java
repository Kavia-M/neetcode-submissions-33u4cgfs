// It looks like Combination Sum II
// Solution seeing half of the Striver's video

class Solution_from_combination_sum_2 {
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

// --------------------------------------------------------------------------

// Given solutions
// Brute force
// in python you can add only (hashable and immutable) tuples in set. list cannot be added in set
class Solution_bruteForce {
    Set<List<Integer>> ans = new HashSet<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        func(0, new ArrayList<>(), nums);
        return new ArrayList<>(ans);
    }

    private void func(int i, List<Integer> list, int[] nums) {
        if(i==nums.length) {
            ans.add(new ArrayList<>(list));
            return;
        }

        // take it or not take it

        list.add(nums[i]);
        func(i+1, list, nums);
        list.remove(list.size() - 1);

        func(i+1, list, nums);
    }
}
// time complexity. 2 power n subsets, each subset has atmost n elements. n* 2 power n.. each recurrsion call adds one element. n * 2 power n recurrr calls

// SOLUTION 2 - backtracking 1 is similar to combinations 2 sum and what I did in my solution. 

// Solution 3 - backtracking 2 For loop inside recurrsion which is same as backtracking optimal last solution in combination 2
class Solution {
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        backtrack(0, new ArrayList<>(), nums);
        return ans;
    }

    private void backtrack(int ind, List<Integer> list, int[] nums) {
        // no matter what we need to do add any subset to ans
        ans.add(new ArrayList<>(list));

        for(int i = ind; i<nums.length; i++) {
            if(i > ind && nums[i] == nums[i-1]) continue;

            list.add(nums[i]);
            backtrack(i+1, list, nums);
            list.remove(list.size()-1);
        }
    }
}


