class Solution {
    private Set<List<Integer>> ans = new HashSet<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        addToAns(new ArrayList<>(), candidates, 0, target, 0);
        return ans.stream().collect(Collectors.toList());
    }
    public void addToAns(ArrayList<Integer> nums, int[] candidates, int i, int target, int sum) {
        if(sum==target) {
            // System.out.println("YESSSSSSS");
            ans.add(new ArrayList<>(nums));
        }
        // System.out.println(nums);
        // System.out.println(i + " "  + sum + " " + target);
        
        for(;i<candidates.length;i++) {
            nums.add(candidates[i]);
            sum+=candidates[i];

            addToAns(nums, candidates, i+1, target, sum);
            nums.remove(nums.size()-1);
            sum-=candidates[i];
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