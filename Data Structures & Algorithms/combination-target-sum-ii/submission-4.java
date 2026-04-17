class Solution_my_own_sol_time_limit_exceeded {
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

// Solution seeing half of the Striver's video
class Solution_Striver {
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

// ================================================================================================
// GIVEN SOLUTIONS

// GIVEN SOLUTION 1 , brute force
public class Solution {
    private Set<List<Integer>> res;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        res = new HashSet<>();
        Arrays.sort(candidates);
        generateSubsets(candidates, target, 0, new ArrayList<>(), 0);
        return new ArrayList<>(res);
    }

    private void generateSubsets(int[] candidates, int target, int i, List<Integer> cur, int total) {
        if (total == target) {
            res.add(new ArrayList<>(cur));
            return;
        }
        if (total > target || i == candidates.length) {
            return;
        }

        cur.add(candidates[i]);
        generateSubsets(candidates, target, i + 1, cur, total + candidates[i]);
        cur.remove(cur.size() - 1);

        generateSubsets(candidates, target, i + 1, cur, total);
    }
}
