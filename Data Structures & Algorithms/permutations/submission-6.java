class Solution_my_soln {
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        List<Integer> numbers = Arrays.stream(nums).boxed().collect(Collectors.toList());
        addPermutations(new ArrayList<>(), numbers);
        return ans;
    }

    private void addPermutations(List<Integer> list, List<Integer> nums) {
        if(nums.isEmpty()) {
            ans.add(new ArrayList<>(list));
            return;
        }
        // Iterator<String> it = list.iterator();
        // while (it.hasNext()) {
        // String element = it.next(); // Must call next() first
        // if (shouldRemove(element)) {
        // it.remove(); // Safely removes the element from list
        // }
        // }

        // for(int num : nums) {
        //     nums.remove(Integer.valueOf(num));
        //     list.add(num);
        //     addPermutations(list, nums);
        //     nums.add(num);
        //     list.remove(list.size() - 1);
        // }

        for(int i=0; i<nums.size(); i++) {
            int num = nums.get(i);
            nums.remove(i);
            list.add(num);
            addPermutations(list, nums);
            nums.add(i, num); // index and element
            list.remove(list.size() -1);
        }
    }
}

/*
 * WHY USE ITERATOR.REMOVE() INSTEAD OF LIST.REMOVE()?
 * * 1. Consistency: iterator.remove() is the only safe way to modify a collection 
 * during iteration. It updates the underlying list AND the iterator's state 
 * simultaneously so they stay in sync.
 * * 2. Fail-Fast: list.remove() changes the list's 'modCount' but not the 
 * iterator's 'expectedModCount'. This mismatch triggers a 
 * ConcurrentModificationException on the next iter.next() call.
 * * 3. Positioning: iterator.remove() correctly adjusts the internal cursor 
 * so no elements are skipped and pointers remain valid.
 */


// ---------------------------------------------------------------
// GIVEN SOLUTIONS
class Solution_recurrsion {
    // we Keep reducing the size, smaller sub arrays leaving 0th index element 
    // when it reaches length 0 return empty array [[]]
    public List<List<Integer>> permute(int[] nums) {
        //Arrays.asList(...) method (from java.util.Arrays) converts given elements into a fixed-size List
        if(nums.length == 0) {
            return Arrays.asList(new ArrayList<>());
        }

        // call permutations from index 1 to last
        int num = nums[0];
        List<List<Integer>> perms = permute(Arrays.copyOfRange(nums, 1, nums.length));
        List<List<Integer>> ans = new ArrayList<>();
        for(List<Integer> p : perms) {
            // insert the num BEFORE each number and at last,
            for(int i=0; i<=p.size(); i++) {
                // while inserting do not modify original array, make a copy
                // or we can add it, add a copy to ans and remove it
                
                /* 
                // not doing this since it looks like backtracking
                p.add(i, num);
                ans.add(new ArrayList<>(p));
                p.remove(i);
                */
                ArrayList<Integer> p_copy = new ArrayList<>(p);
                p_copy.add(i, num);
                ans.add(p_copy);

            }
        }
        return ans;
    }
}

// -------------------------------------------------------------------------------

// Iterative method
class Solution_iterative_mine {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>()); // [[]]

        for(int num : nums) {
            int size = ans.size();
            System.out.println(size);
            for(int i = 0; i<size; i++) {
                // since we need all elements in each permutation, remove the earlier smaller ones
                List<Integer> p = new ArrayList<>(ans.get(0));
                ans.remove(0);
                // insert at each position
                for(int j = 0; j<=p.size(); j++) {
                    List<Integer> p_copy = new ArrayList<>(p);
                    p_copy.add(j, num);
                    System.out.println(p_copy.toString());
                    ans.add(p_copy);
                }                
            }
        }
        return ans;
    }
}

// ------------------------------------------------
// GIVEN Iterative method
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());

        for(int num : nums) {
            // replace the old ans with this one
            List<List<Integer>> new_ans = new ArrayList<>();
            
            // no need to remove any element from previous ans
            for(List<Integer> p : ans) {
                // add at every position
                for(int i=0; i<=p.size(); i++) {
                    // create a copy and add in new_ans
                    List<Integer> p_copy = new ArrayList<>(p);
                    p_copy.add(i, num);
                    new_ans.add(p_copy);
                }
            }
            // REPLACE old ans with new ans
            // this new answer is not going to be modifled in next iteratiion, in next iteration we create another new ans and at last ans will point to latest new ans
            ans = new_ans;
        }
        return ans;
    }
}