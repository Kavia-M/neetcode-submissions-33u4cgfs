class Solution {
    private Set<List<Integer>> ans = new HashSet<>();
    int target;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        this.target = target;
        Arrays.sort(candidates);
        addSubsets(new ArrayList<>(), candidates, 0, 0);
        return ans.stream().collect(Collectors.toList());
    }
    public void addSubsets(ArrayList<Integer> already, int[] nums, int i, int sum) {
        for(;i<nums.length;i++) {
            List<Integer> temp = new ArrayList<>(already);
            temp.add(nums[i]);
            sum+=nums[i];
            if(sum==target)
                ans.add(temp);
            // ans.add((new ArrayList<>(already)).add(nums[i]));
            already.add(nums[i]);
            addSubsets(already, nums, i+1, sum);
            already.remove(already.size()-1);
            sum-=nums[i];
        }
    }
}

class Solution_Previous_Question {
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