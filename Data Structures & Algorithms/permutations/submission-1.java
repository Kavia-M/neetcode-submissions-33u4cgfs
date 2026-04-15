class Solution {
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