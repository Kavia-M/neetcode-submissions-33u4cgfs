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
class Solution_Binary_Search {
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
            // before adding increase the size.
            n++;
            for(int i = stones.length-1; i > l; i--) {
                // for every elements greater than the l we make it copy of it's previous element
                stones[i] = stones[i-1];
            }
            stones[l] = diff;
        }
        if(n>0) return stones[n-1];
        return 0;
    }
}

// Bucket
class Solution {
    public int lastStoneWeight(int[] stones) {
        int maximum = Arrays.stream(stones).max().orElse(0); // orElse 0 for if array is empty. but given it wont be empty so we can use .getAsInt() also

        // bucket size is maximum + 1 stone value will become index and 0 index is kept as it is as when it is needed like if no stones exists at last we return 0
        int[] bucket = new int[maximum + 1];

        int first = maximum;
        int second = first - 1;

        for(int stone : stones) {
            bucket[stone]++;
        }

        while(first > 0) {
            while(bucket[first]%2 == 0) {
                // all even number of stones smash as pairs and no remains
                bucket[first] = 0; // this is no needed as we do not return to this index after this point
                first--;
                second = first - 1; // whenever we update 1st we update second also
                if(first==0) return 0;
            }

            // now we need correct 2nd, we not only have the stones given to us, we keep all the indices from 0 to maximum, so many are 0 (no stones given to us or smashed already)
            int j;
            for(j = second; j > 0; j--) { // not >=0
                if(bucket[j]!=0) break;
            }

            if(j==0) {
                // if loop fully ran till it became 0, then we could not find the 2nd highest, return the existing maximum
                return first;
            } 

            second = j;

            // smash
            bucket[first]--;
            bucket[second]--;
            bucket[first - second]++;

            // new stone = first - second
            // if first=9, second=2, new=7. so new is greater than 2nd highest, if it is greater than 2nd highest and 1st highest is already smashed fully
            // so new will be the 1st highest now. as new > 2nd highest > 3rd highest etc.,
            // if existing 2nd highest is greater, it will be 1st highest now, we do not guarantee than stones exist in this bucket. but in next iteration bucket[first]%2==0 will pass if the bucket[first] is 0.
            // if bucket[first]==0 after the below update, if (bucket[first] % 2 == 0) passes and first decreases.
            first = Math.max(first - second, second);
            second = first - 1;
        }
        return first; // first = 0 now
    }
}





