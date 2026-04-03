class Solution_My_Solution {
    public int lastStoneWeight(int[] stones) {
        List<Integer> list = Arrays.stream(stones).boxed().collect(Collectors.toList());
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        pq.addAll(list);
        while(pq.size()>1) {
            int x = pq.poll();
            int y = pq.poll();
            System.out.println(x + " " + y);
            if(x!=y) {
                // y is 2nd in maxheap, it means it is less than or equal to x
                pq.add(x-y);
            }
        }
        if(!pq.isEmpty()) return pq.poll();
        return 0;
    }
}

// 1. Sorting. 
class Solution_Sorting {
    public int lastStoneWeight(int[] stones) {
        // smash means i will make the stone become 0
        while(stones.length > 1) { // O(n)
            Arrays.sort(stones); // in place O(n log n)
            if(stones[stones.length-2] == 0) break;
            
            int x = stones[stones.length-1];
            int y = stones[stones.length-2];

            stones[stones.length-1] = 0;
            stones[stones.length-2] = x-y;
        }
        if(stones.length == 0) return 0;
        return stones[stones.length-1];
    }
} // time O(n2 log n)

// BINARY SEARCH
class Solution {
    public int lastStoneWeight(int[] stones) {
        // 1st itself we need to sort
        Arrays.sort(stones);
        int n = stones.length;

        while(n>1) { // min 2 elements guaranteed
            // remove last 2 elements, add it's difference (if difference > 0)
            int diff = stones[n - 1] - stones[n - 2];
            n -= 2;
            
            if(diff == 0) continue; // no need to do anything and also diff cannot be less than 0

            // add it back using binary search >= upper bound index
            // we will keep l and r where elements in this window satisfy this property (there can be other elements outside) but l will be the starting of the window
            // l and r be inclusive
            int l = 0, r = n-1;

            while(l<=r) { // inclusive r
                int mid = l + (r-l)/2;
                if(stones[mid] >= diff) {
                    // there could be other elements in left side, move to left
                    r = mid -1;
                }
                else {
                    // mid < diff means left of this also <, so discard left side and go to right side
                    l = mid + 1;
                }
            }
            // if l increased and moved out of initial r=n-1 means we need to add this element in the last of the existing array
            // if r decreased and r became < l means still l holds correct value;
            
            // ADD THIS to the array
            for(int i = stones.length-1; i > l; i--) {
                // for every elements greater than the l we make it copy of it's previous element
                stones[i] = stones[i-1];
            }
            stones[l] = diff;
            n+=1; // now we added one element into array
        }
        if(n>0) return stones[n-1];
        return 0;
    }
}



