class Solution {
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
