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
class Solution {
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