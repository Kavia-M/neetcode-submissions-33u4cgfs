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
// SAME as ours, TIME LIMIT EXCEEDED
public class Solution_given_brute_force_time_exceeded {
    private Set<List<Integer>> res;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        res = new HashSet<>();
        // sort it, so inner list inside set will have same order incase of duplicates
        // for comninations we always sort and use like this
        // [2, 4, 5, 2] target 7 means ans [2, 5] and [5, 2] but if we sorted, ans [2,5] and [2,5]
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

        // take it or not take it

        // take it
        cur.add(candidates[i]);
        // the candidates may have duplicates but ELEMENT IN AN INDEX can be taken only once. so pass i+1 to next iteration
        generateSubsets(candidates, target, i + 1, cur, total + candidates[i]);
        cur.remove(cur.size() - 1);

        // not take it, ensure we backtracked using above line
        generateSubsets(candidates, target, i + 1, cur, total);
    }
}

// -------------------------------------------------
// GIVEN backtracking

// same as striver's solution
public class Solution_Given_Backtracking {
    private List<List<Integer>> res;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        res = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(candidates, target, 0, new ArrayList<>(), 0);
        return res;
    }

    private void dfs(int[] candidates, int target, int i, List<Integer> cur, int total) {
        if (total == target) {
            res.add(new ArrayList<>(cur));
            return;
        }
        if (total > target || i == candidates.length) {
            return;
        }

        // take it as usual
        cur.add(candidates[i]);
        dfs(candidates, target, i + 1, cur, total + candidates[i]);
        cur.remove(cur.size() - 1);

        // not take it

        /*
        // this candidate is equal to next candidate means move to the next candidate
        while (i + 1 < candidates.length && candidates[i] == candidates[i + 1]) {
            i++;
        }
        
        // the above loop breaks when this candidate is NOT EQUAL to the next character
        // so we need to skip this character, all we need is next character which is i+1
        dfs(candidates, target, i + 1, cur, total);

        */

        // using striver's linear approach for not taking it
        // Skip duplicates: if not picking the current candidate, 
        // ensure the next candidate is different we call recurrsion within the loop and break the loop
        for(int j = i + 1; j < candidates.length; j++) {
            if(candidates[j] != candidates[i]) {
                // as soon as we find a 
                dfs(candidates, target, j, cur, total);
                break;
            }
        }
    }
}

// -----------------------------------------------------------------------
// here instead of passing the candidates array, we pass frequency map
// https://www.geeksforgeeks.org/java/collectors-groupingby-method-in-java-with-examples/
public class Solution_my_hashMAP {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // Collectors.groupingBy(Function.identity(), Collectors.counting())
        // Collections.groupingBy returns map HASHMAP by default, it takes a function for key and a function for value
        // Function.identity() is equivalent to n-> n     means use the element from stream as key
        // Collectors.counting() Counts how many times each key appears , it returns LONG

        HashMap<Integer, Integer> freq = Arrays.stream(candidates)
                                    .boxed()
                                    .collect(Collectors.groupingBy(
                                        n->n,
                                        HashMap::new, // or else it returns Map<> only
                                        // Collectors.counting()
                                        Collectors.summingInt(e -> 1) // Adds 1 for each occurrence → gives count as int
                                    ));
        
        List<List<Integer>> ans = new ArrayList<>();
        dfs(ans, new ArrayList<>(), freq, target);
        return ans;
    }

    private void dfs(List<List<Integer>> ans, List<Integer> list, HashMap<Integer, Integer> freq, int target) {
        if(target==0) {
            ans.add(new ArrayList<>(list));
            return;
        }

        if(target < 0 || freq.isEmpty()) {
            return;
        }

        // take it or not take it
        
        // take it from hashmap, take 1st element from hashmap. others will be taken in recursive calls

        /*
        if (!freq.isEmpty()) {
            // 1. Grab any key
            Integer key = freq.keySet().iterator().next();
    
          // 2. Decrement or Remove
         int count = freq.get(key);
         if (count <= 1) {
                freq.remove(key);
            } else {
                freq.put(key, count - 1);
            }
        }
        */

        Iterator<Map.Entry<Integer, Integer>> it = freq.entrySet().iterator();

        // it.next() gives you a handle (Entry) to a specific spot in the map.
        // entry.setValue() modifies the data at that spot.
        // it.remove() deletes that spot entirely and moves the iterator's internal "last returned" pointer to a safe state.

        if(it.hasNext()) {
            Map.Entry<Integer, Integer> entry = it.next();
            int key = entry.getKey();
            int count = entry.getValue() - 1;
            boolean isRemoved = false;
            if(count <= 0) {
                isRemoved = true;
                it.remove(); // Removes the element safely
            }
            else {
                entry.setValue(count);
            }

            list.add(key);
            dfs(ans, list, freq, target - key);
            list.remove(list.size()-1);

            // not take it
            // remove it from map so it annot be taken in further recurrsion
            if(!isRemoved) freq.remove(key); // An iterator's remove() method can only be called once per call to next().
            dfs(ans, list, freq, target); // pass the hashmap as it is

            // Undo Map change
            // Use merge to put the "1" back that we took away. 
            // If the key was removed, it recreates it with value 1.
            
            // DONOT USE THIS - freq.merge(key, 1, Integer::sum); becausewe remove it completely in not take one, so the value becomes always one and not original value
            freq.put(key, count+1);
        }
    }
}


public class Solution_hashMap_recurrsion_with_for_loop {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        HashMap<Integer, Integer> freq = Arrays.stream(candidates)
                                    .boxed()
                                    .collect(Collectors.groupingBy(
                                        n->n,
                                        HashMap::new, // or else it returns Map<> only
                                        // Collectors.counting()
                                        Collectors.summingInt(e -> 1) // Adds 1 for each occurrence → gives count as int
                                    ));
        
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> uniqueKeys = new ArrayList<>(freq.keySet());
        dfs(ans, new ArrayList<>(), freq, target, 0, uniqueKeys);
        return ans;
    }

    private void dfs(List<List<Integer>> ans, List<Integer> list, HashMap<Integer, Integer> freq, int target, int start, List<Integer> uniqueKeys) {
        if(target==0) {
            ans.add(new ArrayList<>(list));
            return;
        }

        if(target < 0) { // no need to check isEmpty because we are called it in loop only so it cannot be called on empty hashmap
            return;
        }

        
        // Iterate through unique numbers starting from 'start' to avoid duplicate combinations
        for(int i = start; i<uniqueKeys.size(); i++) {
            int key = uniqueKeys.get(i);
            int value = freq.get(key);

            // take it
            if(value > 0) {
                freq.put(key, value-1);
                list.add(key);
                // 2. Recurse: 'i' stays the same because we can use the same number again 
                // (if count allows), but the loop logic handles the "skip" naturally.
                dfs(ans, list, freq, target - key, i, uniqueKeys);

                // 3. Backtrack: Restore state
                list.remove(list.size() - 1);
                freq.put(key, value);
            }

            // not take it
            // simply do not do anything, it will go to next iteration
        }
    }
}

//---------------------------------------------------------------------------

public class Solution_GIVEN_HASHMAP {
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> curr = new ArrayList<>();
    List<Integer> uniqueKeys = new ArrayList<>();
    Map<Integer, Integer> freq = new HashMap<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        for(int num : candidates) {
            // if we are adding this element 1st time add it into uniqueKeys
            if(!freq.containsKey(num)) uniqueKeys.add(num);

            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        dfs(0, target);
        return ans;
    }

    // now the uniqueKeys is only our array.
    // think like we can reuse the uniqueKeys, so in recurrsion of take it we pass same index
    // not take it , we pass next index
    // but while using or reusing anything in uniqueKeys we check value>0
    private void dfs(int i, int target) {
        if(target==0) {
            ans.add(new ArrayList<>(curr));
            return;
        }

        if(target < 0 || i==uniqueKeys.size()) return;

        // take it
        int key = uniqueKeys.get(i);
        int value = freq.get(key);

        if(value > 0) {
            freq.put(key, value - 1);
            curr.add(key);

            // to reuse it next time if it still has more freq
            dfs(i, target - key);

            freq.put(key, freq.getOrDefault(key, 0) + 1); // increamen or restore old value using freq.put(key, value);
            curr.remove(curr.size()-1);
        }

        // not take it, SIMPLY move to next uniqueKey as we do not use any freq in this key
        dfs(i+1, target);
    }
}

// --------------------------------------------------------------------------

// Given solution BackTracking optimal

// SAME as STRIVER's backtracking, but if an element can make target less than 0, next elements are greater SINCE WE SORTED the array. so skip this and all next elements and return
public class Solution_Backtracking_optimal_my_solution {
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        dfs(0, new ArrayList<>(), candidates, target);
        return ans;
    }

    private void dfs(int i, List<Integer> list, int[] nums, int target) {
        if(target==0) {
            ans.add(new ArrayList<>(list));
            return;
        }

        if(i==nums.length) { // no need to check target < 0 because it is checked before recurrsion call itself
            return;
        }

        if(target - nums[i] < 0) return; // no need to TAKE it, no need to NOT TAKE it, skip from this and all elements after this

        // take it
        list.add(nums[i]);
        dfs(i+1, list, nums, target - nums[i]);
        list.remove(list.size()-1);

        // not take it
        while(i+1 < nums.length && nums[i]==nums[i+1])
            i++;

        dfs(i+1, list, nums, target);
    }
}

// -------------------------------------------------------

// GIVEN BACKTRACKING OPTIMAL
// use for loop

// TAKE it or not take it
// Take it is inside for loop, we backtrack it,
// not take it means move to next index. we do that with i+1

// for each in in for loop, it is in the scenario where none of it's previous elements are not taken in current recurrsion call
// the previous element is taken means it will go to next NEW iteration
// the previous element taken, next new iteration applied to i==ind only

public class Solution {
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        backtrack(0, new ArrayList<>(), candidates, target);
        return ans;
    }

    private void backtrack(int ind, List<Integer> list, int[] nums, int target) {
        if(target == 0) {
            ans.add(new ArrayList<>(list));
            return;
        }

        // check if current ind passed from another function is within the range
        // if(ind == nums.length) return; // no need to check as each recurrsion is called within for loop only, if this is true the below for loop will not execute and return simply

        for(int i = ind; i<nums.length; i++) {
            
            // i==ind means, the previous element is taken in PREVIOUS recurrsion, not this recurrsion.
            // for other elements in this recurrsion, the previous elements not taken case, so we should check if the PREVIOUS element is same value, if that was not taken this should not be taken as well
            if(i!=ind && nums[i] == nums[i-1]) {
                continue;
            }

            if(target - nums[i] < 0) break; // here break is same as return as nothing is there after for loop

            // take it and move to next recurrsion
            list.add(nums[i]);
            backtrack(i+1, list, nums, target - nums[i]);
            list.remove(list.size() -1);

            // not take it, simply increment i which happens in for loop header
        }
    }
}