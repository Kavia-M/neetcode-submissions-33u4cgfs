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
class Solution_recurrsion {
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

// ITERATIVE
/*
What actually happens

At each step:

Step 0 -> empty subset -> total 0
Step 1 → 1 subset → create 1 new → total 2
Step 2 → 2 subsets → create 2 new → total 4
Step 3 → 4 subsets → create 4 new → total 8

created new subsets exclusing set 0. at each step 2 power i-1

1+2+4+8+⋯+2 power n-1

total new creations = (2 power n ) - 1

at each of the new creation we copy the old array, that is O(n)

excluding copy it is O(2^n) only

*/
// THIS IS SAME AS take or not take, not take is already there, we add subsets with TAKE
class Solution_iteration {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();

        // add initial set that is empty set
        ans.add(new ArrayList<>());

        for(int num : nums) { // step 1 to n
            // VERY IMPORTANT: take the size 1st or else we keep on addign and size keep increasing and we get infinite loop
            int size = ans.size();

            /*
            // ConcurrentModificationException because modifies ans while looping over it
            for(List<Integer> subset : ans) {
                // subset has only the address
                subset.add(num); // this not returns a list, it make changes inplace
                ans.add(subset);
            }
            */

            for(int i = 0; i<size; i++) {
                List<Integer> subset = new ArrayList<>(ans.get(i)); // MAKE A COPY. because we can't modify inplace as it would affect already existing subsets. we need existing subsets for NOT TAKE scenario
                subset.add(num);
                ans.add(subset);
            }
        }
        return ans;   
    }
}


// ----------------------------------------------------------------------------------
// 1 << n is a fast, exact integer operation for computing powers of 2 using bit shifting, while Math.pow(2, n) uses floating-point arithmetic, is slower, and may introduce precision issues.
// Yes — each << or >> operation is O(1).
// "Shift instruction directly moves bits in hardware". 1<<2 or 1<<20 all same
// & can be used for logical AND also, but it  does not short circuit, it always evaluates both sides
// != has MORE PRECEDENCE than & so use ()
// boolean and int for &. incompatible, boolean boolean or int int should be used
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length; // just for simplicity 
        List<List<Integer>> ans = new ArrayList<>();

        // 1<<n, 1 in bit shifted n times means 2 power n
        // i<2^n or i<=(2^n)-1
        for(int i=0; i<(1<<n); i++) {
            
            // ALWAYS KEEP a LIST, even if we are going to add empty subset to ans
            List<Integer> subset = new ArrayList<>();
            
            // we have n bits to check, 0 to 2^n - 1. these numbers can be represented in n bits
            for(int j = 0; j<n; j++) {
                // for each number, we need to see which bit are set
                // to see if a bit is set, bitwise AND with a number having only that bit set. If ans is 0, then the required bit is NOT SET, anything other than 0 means set
                if((i & (1<<j)) != 0) {
                    subset.add(nums[j]); // j is 0 to n-1, that is the index of the element in the aray
                }
            }

            // add the subset to ans
            ans.add(subset);
        }
        return ans;
    }
}