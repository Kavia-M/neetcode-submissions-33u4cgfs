class Solution_my_own {
    private List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        ans.add(new ArrayList<>());
        addSubsets(new ArrayList<>(), nums, 0);
        return ans;
    }
    public void addSubsets(ArrayList<Integer> already, int[] nums, int i) {
        for(;i<nums.length;i++) {
            List<Integer> temp = new ArrayList<>(already);
            temp.add(nums[i]);
            ans.add(temp);
            // ans.add((new ArrayList<>(already)).add(nums[i]));
            already.add(nums[i]);
            addSubsets(already, nums, i+1);
            already.remove(already.size()-1);
        }
    }
}

// STRIVER's solution
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        //your code goes here
        List<List<Integer>> ans = new ArrayList<>();
        generateSubSet(0, new ArrayList<>(), ans, nums);
        return ans;
    }

    private void generateSubSet(int i, List<Integer> list, List<List<Integer>> ans, int[] nums) {
        // i is the curr index we need to process now
        if(i==nums.length) {
            // n-1 is last valid index
            ans.add(new ArrayList<>(list)); // see this list is stored in answer as address of list, so in backtrack we remove the elenments,. if we do ans.add(list) all things inside ans will be empty lists. So add the copy of the list
            return;
        }

        // take or not take
        // LIST is passed as list address in function
        // this work as call by reference. so if we do not remove it in backtracking it will affect the called function

        // not taking
        generateSubSet(i+1, list, ans, nums);

        // taking
        list.add(nums[i]);
        generateSubSet(i+1, list, ans, nums);
        list.remove(list.size()-1);
    }
}
