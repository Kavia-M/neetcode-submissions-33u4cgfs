class Solution {
    // heap sort.
    /*
        why child is 2*i + 1
        means. i is index of parent node. for each node 2 children
        0 based index so i number of nodes before it. these nodes have
        2i cildren after these only this ith node's child comes
        so 2i+1

        https://www.geeksforgeeks.org/dsa/introduction-to-min-heap-data-structure/

        to get parent i-1 / 2. right is always even. left reverse logic as it is
        right even means i-2/2 == i-1/2 due to integer division i-1/2 becomes fraction and go to floor that is == i-2/2


    */
    public int[] sortArray(int[] nums) {
        /*

        ArrayList<Integer> arr = (ArrayList<Integer>) Arrays.stream(nums) // Creates an IntStream
                                  .boxed()          // Boxes 'int' primitives to 'Integer' objects
                                  .collect(Collectors.toList()); // Collects elements into a List
        buildHeap(arr);

        */

        // withiut using buildheap we can also use to insert
        ArrayList<Integer> arr = new ArrayList<>();
        for(int val : nums) {
            insert(arr, val);
        }
        System.out.println(arr.toString());
        List<Integer> ans = new ArrayList<>();
        while(!arr.isEmpty()) {
            ans.add(arr.get(0));
            delete(arr, 0);
        }
        return ans.stream()
                    .mapToInt(i -> i) // Maps each Integer to an int (unboxing)
                    .toArray();      // Collects the elements into an int array;
    }

    /*
        Heapify assumes children are already minheap each
        so if we use heapify for build heap, 1st apply heapify for children and make then heaps
        in a heap we have n is total nodes, binary tree means at each level 2 power l (l is level 0 for root)
        last level is already heap because only one element no children
        we no need to consider last level. each level can make total elements += multiplied by 2
        so we can divide n/2 => number of elemets above that level, 0 based index so n/2-1 is where we start to heapify
    */

    private void buildHeap(ArrayList<Integer> arr) {
        int n = arr.size();

        for(int i=n/2-1; i>=0; i--) {
            heapify(arr, i);
        }
    }

    private void heapify(ArrayList<Integer> arr, int i) {
        int n = arr.size();
        int smallest = i;
        int left = 2*i + 1;
        int right = 2*i + 2;

        if(left < n && arr.get(smallest) > arr.get(left)) 
            smallest = left;
        
        if(right < n && arr.get(smallest) > arr.get(right))
            smallest = right;

        if(smallest != i) {
            // smallest is left or right
            Collections.swap(arr, i, smallest);
            // only if we swapped it, we need to recursively heapify
            heapify(arr, smallest);
        }        
    }

    private void delete(ArrayList<Integer> arr, int i) {
        int n = arr.size();
        if(n==0) return;
        Collections.swap(arr, i, n-1); // last element
        arr.remove(n-1); 
        heapify(arr, i);
    }

    private void insert(ArrayList<Integer> arr, int value) {
        arr.add(value); // at last
        int n = arr.size();
        int i = n-1;

        // if i ==0 means it is root no need to check for parent
        // i == 0 means parent becomes negativ
        while(i > 0 && arr.get((i-1)/2) > arr.get(i)) {
            Collections.swap(arr, i, (i-1)/2);
            i = (i-1)/2;
        }
    }
}