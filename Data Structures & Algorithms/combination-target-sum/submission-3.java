class Solution_my_own {
    private List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        backtrack(new ArrayList<Integer>(), nums, target, 0, 0);
        return ans;
    }

    public void backtrack(ArrayList<Integer> temp, int[] nums, int target, int sum, int start){
        if(sum == target) {
            ans.add(new ArrayList<>(temp));
            return;
        }
        if(sum > target) return;

        for(int i = start; i < nums.length; i++){

            temp.add(nums[i]);        // choose

            backtrack(temp, nums, target, sum+nums[i], i); // explore

            temp.remove(temp.size()-1); // BACKTRACK
        }
    }
}



// Inspired by Striver's subset problem
class Solution_STRIVER {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        //your code goes here
        List<List<Integer>> ans = new ArrayList<>();
        addCombinations(0, new ArrayList<>(), ans, candidates, target);
        return ans;
    }
    
    private void addCombinations(int ind, List<Integer> list, List<List<Integer>> ans, int[] candidates, int target) {

        if(target==0) {
            ans.add(new ArrayList<>(list)); // add new list copu
            return;
        }

        if(target < 0 || ind==candidates.length) return;

        // not choose it and move to next index
        // or
        // choose it and remain in same index 

        addCombinations(ind + 1, list, ans, candidates, target);
        
        list.add(candidates[ind]); // RETURNS BOOLEAN TRUE OR FALSE
        addCombinations(ind, list, ans, candidates, target - candidates[ind]);
        list.remove(list.size() - 1);
    }
}


/*
Key Idea:

Let m = minimum value in nums

Worst case → we always pick the smallest number

So target reduces like:
target → target - m → target - 2m → ... → 0

Maximum depth of recursion:
depth ≈ target / m


Recursion Tree Shape:

At each step we have 2 choices:
1. Include nums[i]  → stay at same index
2. Skip nums[i]     → move to next index

So,
branching factor ≈ 2
depth ≈ target / m


Total number of nodes:

nodes ≈ 2^(target / m)


Time Complexity:

O(2^(target / m))


But also consider:

Each valid combination is copied using:
new ArrayList(cur)

Cost of copying ≈ size of combination ≈ target / m


Final Time Complexity:

O(2^(target / m) * (target / m))


SPACE COMPLEXITY:
Maximum height of recursion tree (maximum recurrsion calls in stack at any time)
O(target / m)
*/


//  after sorted in ascending order, we do not call any recurrsive calls, either using it or skippig it if target-candidates[ind] becomes < 0
// because the elements after this in sorted array or even more greater than this.

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        
        Arrays.sort(candidates); // only additional O(n log n) time complexity

        List<List<Integer>> ans = new ArrayList<>();
        addCombinations(0, new ArrayList<>(), ans, candidates, target);
        return ans;
    }
    
    private void addCombinations(int ind, List<Integer> list, List<List<Integer>> ans, int[] candidates, int target) {

        if(target==0) {
            ans.add(new ArrayList<>(list)); // add new list copy
            return;
        }

        if(ind==candidates.length) return; // no need target < 0 since it is already checked before the recurrsive call happen

        // not choose it and move to next index
        // or
        // choose it and remain in same index 

        if(target - candidates[ind] < 0) return; // do not call any more recurrsive calls

        addCombinations(ind + 1, list, ans, candidates, target);
        
        list.add(candidates[ind]); // RETURNS BOOLEAN TRUE OR FALSE
        addCombinations(ind, list, ans, candidates, target - candidates[ind]);
        list.remove(list.size() - 1);
    }
}
