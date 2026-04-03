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
class Solution {
    public int lastStoneWeight(int[] stones) {
        // smash means i will make the stone become 0
        while(stones.length > 1) {
            Arrays.sort(stones); // in place
            if(stones[stones.length-2] == 0) break;
            
            int x = stones[stones.length-1];
            int y = stones[stones.length-2];

            stones[stones.length-1] = 0;
            stones[stones.length-2] = x-y;
        }
        if(stones.length == 0) return 0;
        return stones[stones.length-1];
    }
}


